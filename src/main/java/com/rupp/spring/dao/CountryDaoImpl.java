package com.rupp.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rupp.spring.domain.Country;
import com.rupp.spring.domain.ResponseList;

@Repository("countryDaoImpl")
public class CountryDaoImpl implements CountryDao {
    private static final Logger LOG = LoggerFactory.getLogger(CountryDaoImpl.class);
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CountryDaoImpl(DataSource dataSource) {
        //jdbcTemplate = new JdbcTemplate(DBCP2DataSourceUtils.getDataSource());
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public int count() {
        final String sql = "select count(*) from " + Country.TABLE;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public ResponseList<Country> getPage(int pagesize, String offset) {
        if (offset == null) {
            offset = "0";
        }
        final String sql = "select * from " + Country.TABLE + " WHERE deletedAt IS NULL limit " + pagesize + " OFFSET " + offset;
        List<Country> list = this.jdbcTemplate.query(sql, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int paramInt) throws SQLException {
                final Country domain = new Country();
                domain.setId(rs.getLong("id"));
                domain.setName(rs.getString("name"));
                domain.setDialingCode(rs.getString("dialingCode"));
                domain.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
                return domain;
            }
            
        });
        
        return populatePages(list, pagesize, String.valueOf(offset));
    }
    
    protected ResponseList<Country> populatePages(final Collection<Country> items, final int pageSize, final String cursorKey) {
        return populatePages(items, pageSize, cursorKey, null);
    }

    protected ResponseList<Country> populatePages(final Collection<Country> items, final int pageSize, final String cursorKey, final Integer totalCount) {

        if (items == null || items.isEmpty()) {
            return new ResponseList<Country>(items);
        }

        int total;
        if (totalCount == null) {
            total = count();
        } else {
            total = totalCount;
        }

        if ("0".equals(cursorKey) && items.size() < pageSize) {
            total = items.size();
        }

        // limit = data.size();
        LOG.debug(" total records count : {} : Integer.parseInt(cursorKey) + items.size() : {} ", total,
                Integer.parseInt(cursorKey) + items.size());
        String nextCursorKey = null;

        if (items.size() == pageSize && Integer.parseInt(cursorKey) + items.size() < total) {
            nextCursorKey = String.format("%s", Integer.parseInt(cursorKey) + items.size());
        }

        LOG.debug("next cursorKey {}", nextCursorKey);

        final ResponseList<Country> page = new ResponseList<Country>(items, nextCursorKey);
        page.withTotal(total).withLimit(items.size());

        // populate previous
        if (!"0".equals(cursorKey)) {
            final int previousCursor = Math.abs(Integer.parseInt(cursorKey) - pageSize);
            page.withReverseCursor(String.format("%s", previousCursor));
        }

        return page;
    }
    /**
     * Returns list of user_types from dummy database.
     * 
     * @return list of categories
     */
    public List<Country> list() {
        final String sql = "select * from " + Country.TABLE;
        List<Country> list = this.jdbcTemplate.query(sql, new RowMapper<Country>() {

            @Override
            public Country mapRow(ResultSet rs, int paramInt) throws SQLException {
                final Country domain = new Country();
                domain.setId(rs.getLong("id"));
                domain.setName(rs.getString("name"));
                domain.setDialingCode(rs.getString("dialingCode"));
                domain.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
                return domain;
            }
            
        });
        return list;
    }

    /**
     * @param id UserType id
     * @return UserType object for given id
     */
    public Country get(Long id) {
        final String sql = "select * from " + Country.TABLE + " where id = ?";
        
        try {
            //select for object
            final Country obj = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Country>() {

                @Override
                public Country mapRow(ResultSet rs, int paramInt) throws SQLException {
                    final Country domain = new Country();
                    domain.setId(rs.getLong("id"));
                    domain.setName(rs.getString("name"));
                    domain.setDialingCode(rs.getString("dialingCode"));
                    domain.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
                    return domain;
                }
            });
            return obj;
        } catch (EmptyResultDataAccessException e) {
            System.out.println("NOT FOUND " + id + " in Table" );
            return null;
        }
    }

    /**
     * Create new UserType in dummy database. Updates the id and insert new UserType in list.
     * 
     * @param UserType UserType object
     * @return UserType object with updated id
     */
    public Country create(Country country) {
        final String sql = "INSERT INTO " + Country.TABLE + " (name,dialingCode) VALUES (?,?)";
        jdbcTemplate.update(sql, new Object[] { country.getName(), country.getDialingCode() });
        return country;
    }

    /**
     * @param id the UserType id
     * @return id of deleted UserType object
     */
    public Long delete(Long id) {
        final String sql = "Delete from " + Country.TABLE + " where id =?";
        int result = jdbcTemplate.update(sql, new Object[] { id });
        return result == 1 ? id : null;
    }

    /**
     * Update the UserType object for given id in dummy database. If UserType not exists, returns null
     * 
     * @param UserType
     * @return UserType object with id
     */
    public Country update(Country country) {

        final String sql = "UPDATE " + Country.TABLE + " set name =? , dialingCode =?, deletedAt =? where id=?";
        int result = jdbcTemplate.update(sql, new Object[] { country.getName(), country.getDialingCode(), country.getDeletedAt() , country.getId()});
        return result == 1 ? country : null;

    }

}

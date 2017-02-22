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

import com.rupp.spring.domain.UserType;
import com.rupp.spring.domain.ResponseList;

@Repository("userTypeDaoImpl")
public class UserTypeDaoImpl implements UserTypeDao {
    private static final Logger LOG = LoggerFactory.getLogger(UserTypeDaoImpl.class);
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserTypeDaoImpl(DataSource dataSource) {
        //jdbcTemplate = new JdbcTemplate(DBCP2DataSourceUtils.getDataSource());
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public int count() {
        final String sql = "select count(*) from " + UserType.TABLE;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public ResponseList<UserType> getPage(int pagesize, String offset) {
        if (offset == null) {
            offset = "0";
        }
        final String sql = "select * from " + UserType.TABLE + " limit " + pagesize + " OFFSET " + offset;
        //List<UserType> list = this.jdbcTemplate.queryForList(sql,UserType.class);
        List<UserType> list = this.jdbcTemplate.query(sql, new RowMapper<UserType>() {

            @Override
            public UserType mapRow(ResultSet rs, int paramInt) throws SQLException {
                final UserType domain = new UserType();
                domain.setId(rs.getLong("id"));
                domain.setType(rs.getString("type"));
                domain.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
                return domain;
            }
            
        });
        
        return populatePages(list, pagesize, String.valueOf(offset));
    }
    
    protected ResponseList<UserType> populatePages(final Collection<UserType> items, final int pageSize, final String cursorKey) {
        return populatePages(items, pageSize, cursorKey, null);
    }

    protected ResponseList<UserType> populatePages(final Collection<UserType> items, final int pageSize, final String cursorKey, final Integer totalCount) {

        if (items == null || items.isEmpty()) {
            return new ResponseList<UserType>(items);
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

        final ResponseList<UserType> page = new ResponseList<UserType>(items, nextCursorKey);
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
    public List<UserType> list() {
        final String sql = "select * from " + UserType.TABLE;
        //List<UserType> list = this.jdbcTemplate.queryForList(sql,UserType.class);
        List<UserType> list = this.jdbcTemplate.query(sql, new RowMapper<UserType>() {

            @Override
            public UserType mapRow(ResultSet rs, int paramInt) throws SQLException {
                final UserType domain = new UserType();
                domain.setId(rs.getLong("id"));
                domain.setType(rs.getString("type"));
                domain.setCreatedAt(new Date(rs.getTimestamp("createdAt").getTime()));
                return domain;
            }
            
        });
        return list;
    }

    /**
     * Return UserType object for given id from dummy database. If UserType is not found for id, returns null.
     * 
     * @param id UserType id
     * @return UserType object for given id
     */
    public UserType get(Long id) {
        final String sql = "select * from " + UserType.TABLE + " where id = ?";
        
        try {
            //select for object
            final UserType obj = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<UserType>() {

                @Override
                public UserType mapRow(ResultSet rs, int paramInt) throws SQLException {
                    final UserType domain = new UserType();
                    domain.setId(rs.getLong("id"));
                    domain.setType(rs.getString("type"));
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
    public UserType create(UserType userType) {
        final String sql = "INSERT INTO " + UserType.TABLE + " (type) VALUES (?)";
        jdbcTemplate.update(sql, new Object[] { userType.getType() });
        return userType;
    }

    /**
     * @param id the UserType id
     * @return id of deleted UserType object
     */
    public Long delete(Long id) {
        final String sql = "Delete from " + UserType.TABLE + " where id =?";
        int result = jdbcTemplate.update(sql, new Object[] { id });
        return result == 1 ? id : null;
    }

    /**
     * Update the UserType object for given id in dummy database. If UserType not exists, returns null
     * 
     * @param UserType
     * @return UserType object with id
     */
    public UserType update(UserType userType) {

        final String sql = "UPDATE " + UserType.TABLE + " set type =? where id=?";
        int result = jdbcTemplate.update(sql, new Object[] { userType.getType() , userType.getId()});
        return result == 1 ? userType : null;

    }

}

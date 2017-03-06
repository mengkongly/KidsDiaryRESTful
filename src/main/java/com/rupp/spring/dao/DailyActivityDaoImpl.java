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

import com.rupp.spring.domain.DailyActivity;
import com.rupp.spring.domain.ResponseList;

@Repository("dailyActivityDaoImpl")
public class DailyActivityDaoImpl implements DailyActivityDao{
	private static final Logger LOG = LoggerFactory.getLogger(DailyActivityDaoImpl.class);    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public DailyActivityDaoImpl(DataSource dataSource) {        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public int count() {
        final String sql = "select count(*) from daily_activities";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    
    @Override
    public ResponseList<DailyActivity> getPage(int pagesize, String offset) {
        if (offset == null) {
            offset = "0";
        }
        final String sql = "select * from daily_activities  limit " + pagesize + " OFFSET " + offset;
        //List<DCategory> list = this.jdbcTemplate.queryForList(sql,DCategory.class);
        List<DailyActivity> list = this.jdbcTemplate.query(sql, new RowMapper<DailyActivity>() {

            @Override
            public DailyActivity mapRow(ResultSet rs, int paramInt) throws SQLException {
                final DailyActivity domain = new DailyActivity();
                domain.setId(rs.getLong("id"));
                domain.setParent(rs.getLong("parent"));
                domain.setChild(rs.getLong("child"));
                domain.setActivityDate(new Date(rs.getDate("activity_date").getTime()));
                domain.setApproved(rs.getBoolean("is_approved"));
                domain.setCreatedAt(new Date(rs.getDate("created_at").getTime()));
                domain.setDeletedAt(new Date(rs.getDate("deleted_at").getTime()));                
                return domain;
            }
            
        });
        
        return populatePages(list, pagesize, String.valueOf(offset));
    }
    
    protected ResponseList<DailyActivity> populatePages(final Collection<DailyActivity> items, final int pageSize, final String cursorKey) {
        return populatePages(items, pageSize, cursorKey, null);
    }

    protected ResponseList<DailyActivity> populatePages(final Collection<DailyActivity> items, final int pageSize, final String cursorKey, final Integer totalCount) {

        if (items == null || items.isEmpty()) {
            return new ResponseList<DailyActivity>(items);
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

        final ResponseList<DailyActivity> page = new ResponseList<DailyActivity>(items, nextCursorKey);
        page.withTotal(total).withLimit(items.size());

        // populate previous
        if (!"0".equals(cursorKey)) {
            final int previousCursor = Math.abs(Integer.parseInt(cursorKey) - pageSize);
            page.withReverseCursor(String.format("%s", previousCursor));
        }

        return page;
    }
    /**
     * Returns list of categories from dummy database.
     * 
     * @return list of categories
     */
    public List<DailyActivity> list() {
        final String sql = "select * from daily_activities";
        //List<DCategory> list = this.jdbcTemplate.queryForList(sql,DCategory.class);
        List<DailyActivity> list = this.jdbcTemplate.query(sql, new RowMapper<DailyActivity>() {

            @Override
            public DailyActivity mapRow(ResultSet rs, int paramInt) throws SQLException {
                final DailyActivity domain = new DailyActivity();
                domain.setId(rs.getLong("id"));
                domain.setParent(rs.getLong("parent"));
                domain.setChild(rs.getLong("child"));
                domain.setActivityDate(new Date(rs.getDate("activity_date").getTime()));
                domain.setApproved(rs.getBoolean("is_approved"));
                domain.setCreatedAt(new Date(rs.getDate("created_at").getTime()));
                domain.setDeletedAt(new Date(rs.getDate("deleted_at").getTime()));                
                return domain;
            }
            
        });
        return list;
    }

    /**
     * Return dCategory object for given id from dummy database. If dCategory is not found for id, returns null.
     * 
     * @param id
     *            dCategory id
     * @return dCategory object for given id
     */
    public DailyActivity get(Long id) {
        final String sql = "select * from daily_activities where id = ?";
        
        try {
            //select for object
            final DailyActivity obj = jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<DailyActivity>() {

                @Override
                public DailyActivity mapRow(ResultSet rs, int paramInt) throws SQLException {
                    final DailyActivity domain = new DailyActivity();
                    domain.setId(rs.getLong("id"));
                    domain.setParent(rs.getLong("parent"));
                    domain.setChild(rs.getLong("child"));
                    domain.setActivityDate(new Date(rs.getDate("activity_date").getTime()));
                    domain.setApproved(rs.getBoolean("is_approved"));
                    domain.setCreatedAt(new Date(rs.getDate("created_at").getTime()));
                    domain.setDeletedAt(new Date(rs.getDate("deleted_at").getTime()));                
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
     * Create new dCategory in dummy database. Updates the id and insert new dCategory in list.
     * 
     * @param dCategory
     *            DCategory object
     * @return dCategory object with updated id
     */
    public DailyActivity create(DailyActivity activity) {
        final String sql = "INSERT INTO daily_activies (parent,child,activity_date,is_approved,created_at,deleted_at) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, new Object[] { activity.getParent(),activity.getChild(),activity.getActivityDate(),activity.isApproved(),activity.getCreatedAt(),activity.getDeletedAt() });
        return activity;
    }

    /**
     * @param id
     *            the dCategory id
     * @return id of deleted dCategory object
     */
    public Long delete(Long id) {
        final String sql = "Delete from daily_activities where id =?";
        int result = jdbcTemplate.update(sql, new Object[] { id });
        return result == 1 ? id : null;
    }

    /**
     * Update the dCategory object for given id in dummy database. If dCategory not exists, returns null
     * 
     * @param id
     * @param dCategory
     * @return dCategory object with id
     */
    public DailyActivity update(DailyActivity activity) {

        final String sql = "UPDATE daily_activities set parent=?,child=?,activity_date=?,is_approved=?,created_at=?,deleted_at=? where id=?";
        int result = jdbcTemplate.update(sql, new Object[] { activity.getParent(),activity.getChild(),activity.getActivityDate(),activity.isApproved(),activity.getCreatedAt(),activity.getDeletedAt(),activity.getId()});
        return result == 1 ? activity : null;

    }
}

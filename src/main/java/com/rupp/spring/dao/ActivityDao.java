package com.rupp.spring.dao;


import java.util.List;

import com.rupp.spring.domain.Activity;
import com.rupp.spring.domain.ResponseList;

public interface ActivityDao {
	/**
     * Returns list of activities from dummy database.
     * 
     * @return list of activities
     */
    List<Activity> list();

    /**
     * Return Activity object for given id from dummy database. If Activity is not found for id, returns null.
     * 
     * @param id
     *            Activity id
     * @return Activity object for given id
     */
    Activity get(Long id);
    /**
     * Create new Activity in dummy database. Updates the id and insert new Activity in list.
     * 
     * @param Activity
     *            Activity object
     * @return activity object with updated id
     */
    Activity create(Activity activity);
    
    Long delete(Long id);
    
    Activity update(Activity activity);
    
    ResponseList<Activity> getPage(int pagesize, String cursorkey);
}

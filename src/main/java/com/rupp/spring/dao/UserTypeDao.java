package com.rupp.spring.dao;

import java.util.List;

import com.rupp.spring.domain.UserType;
import com.rupp.spring.domain.ResponseList;

public interface UserTypeDao {

    /**
     * Returns list of user-types from dummy database.
     * 
     * @return list of user-types
     */
    List<UserType> list();

    /**
     * Return UserType object for given id from dummy database. If userType is not found for id, returns null.
     * 
     * @param id userType id
     * @return UserType object for given id
     */
    UserType get(Long id);
    /**
     * Create new UserType in dummy database. Updates the id and insert new UserType in list.
     * 
     * @param UserType DCategory object
     * @return UserType object with updated id
     */
    UserType create(UserType userType);
    
    Long delete(Long id);
    
    UserType update(UserType userType);
    
    ResponseList<UserType> getPage(int pagesize, String cursorkey);
}

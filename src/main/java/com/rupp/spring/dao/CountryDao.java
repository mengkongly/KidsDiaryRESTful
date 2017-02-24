package com.rupp.spring.dao;

import java.util.List;

import com.rupp.spring.domain.Country;
import com.rupp.spring.domain.ResponseList;

public interface CountryDao {

    /**
     * @return
     */
    List<Country> list();

    /**
     * @param id
     * @return Country
     */
    Country get(Long id);
    
    /**
     * @param Country
     * @return Country
     */
    Country create(Country country);
    
    /**
     * @param id
     * @return
     */
    Long delete(Long id);
    
    /**
     * @param country
     * @return
     */
    Country update(Country country);
    
    /**
     * @param pagesize
     * @param cursorkey
     * @return
     */
    ResponseList<Country> getPage(int pagesize, String cursorkey);
}

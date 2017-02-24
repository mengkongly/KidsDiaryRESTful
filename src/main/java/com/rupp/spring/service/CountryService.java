package com.rupp.spring.service;

import java.util.List;

import com.rupp.spring.domain.Country;
import com.rupp.spring.domain.ResponseList;

public interface CountryService {
    List<Country> list();
    Country get(Long id);
    Country create(Country country);
    Long delete(Long id);
    Country update(Long id, Country userType);
    ResponseList<Country> getPage(int pagesize, String cursorkey);
}

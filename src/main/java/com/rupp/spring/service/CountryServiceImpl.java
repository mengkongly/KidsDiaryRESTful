package com.rupp.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupp.spring.dao.CountryDao;
import com.rupp.spring.domain.Country;
import com.rupp.spring.domain.ResponseList;

@Service("countryService")
public class CountryServiceImpl implements CountryService {
    private static final Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);
    
    @Autowired
    private CountryDao dao;
    
    @Override
    public List<Country> list() {
        return dao.list();
    }

    @Override
    public Country get(Long id) {
        return dao.get(id);
    }

    @Override
    public Country create(Country country) {
        return dao.create(country);
    }

    @Override
    public Long delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public Country update(Long id, Country country) {
        
        if (dao.get(id) == null) {
            return null;
        }
        country.setId(id);
        return dao.update(country);
    }
    
    public ResponseList<Country> getPage(int pagesize, String cursorkey) {
        logger.debug(" getPage limit : {} cursorkey : {}", pagesize, cursorkey);
        return dao.getPage(pagesize, cursorkey);
    }
}

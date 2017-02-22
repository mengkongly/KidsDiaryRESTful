package com.rupp.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rupp.spring.dao.UserTypeDao;
import com.rupp.spring.domain.UserType;
import com.rupp.spring.domain.ResponseList;

@Service("userTypeService")
public class UserTypeServiceImpl implements UserTypeService {
    private static final Logger logger = LoggerFactory.getLogger(UserTypeServiceImpl.class);
    
    @Autowired
    private UserTypeDao dao;
    
    @Override
    public List<UserType> list() {
        return dao.list();
    }

    @Override
    public UserType get(Long id) {
        return dao.get(id);
    }

    @Override
    public UserType create(UserType userType) {
        return dao.create(userType);
    }

    @Override
    public Long delete(Long id) {
        return dao.delete(id);
    }

    @Override
    public UserType update(Long id, UserType userType) {
        
        if (dao.get(id) == null) {
            return null;
        }
        userType.setId(id);
        return dao.update(userType);
    }
    
    public ResponseList<UserType> getPage(int pagesize, String cursorkey) {
        logger.debug(" getPage limit : {} cursorkey : {}", pagesize, cursorkey);
        return dao.getPage(pagesize, cursorkey);
    }
}

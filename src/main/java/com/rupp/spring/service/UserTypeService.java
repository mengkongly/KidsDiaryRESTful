package com.rupp.spring.service;

import java.util.List;

import com.rupp.spring.domain.UserType;
import com.rupp.spring.domain.ResponseList;

public interface UserTypeService {
    List<UserType> list();
    UserType get(Long id);
    UserType create(UserType userType);
    Long delete(Long id);
    UserType update(Long id, UserType userType);
    ResponseList<UserType> getPage(int pagesize, String cursorkey);
}

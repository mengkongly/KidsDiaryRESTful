package com.rupp.spring.service;

import java.util.List;

import com.rupp.spring.domain.DailyActivity;
import com.rupp.spring.domain.ResponseList;

public interface DailyActivityService {
	List<DailyActivity> list();
	DailyActivity get(Long id);
	DailyActivity create(DailyActivity activity);
    Long delete(Long id);
    DailyActivity update(Long id, DailyActivity activity);
    ResponseList<DailyActivity> getPage(int pagesize, String cursorkey);
}

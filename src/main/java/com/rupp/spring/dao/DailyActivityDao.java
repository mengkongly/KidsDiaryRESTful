package com.rupp.spring.dao;

import java.util.List;

import com.rupp.spring.domain.DailyActivity;
import com.rupp.spring.domain.ResponseList;

public interface DailyActivityDao {
	List<DailyActivity> list();
	
    DailyActivity get(Long id);

    DailyActivity create(DailyActivity dailyActivity);
    
    Long delete(Long id);
    
    DailyActivity update(DailyActivity dailyActivity);
    
    ResponseList<DailyActivity> getPage(int pagesize, String cursorkey);
}

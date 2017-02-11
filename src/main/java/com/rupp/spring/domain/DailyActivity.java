package com.rupp.spring.domain;

import java.util.Date;

/**
 * map to table daily_activities
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class DailyActivity extends AbstractEntity {
	
	public static final String TABLE = "daily_activities";
	
	private Long parent;
	private Long child;
	private Date activityDate;
	private boolean isApproved;
	
	public DailyActivity() {}
	public DailyActivity(Long id, Long parent, Long child, Date activityDate, boolean isApproved) {
		super.setId(id);
		setParent(parent);
		setChild(child);
		setActivityDate(activityDate);
		setApproved(isApproved);
	}
	
	public Long getParent() {
		return parent;
	}
	public void setParent(Long parent) {
		this.parent = parent;
	}
	
	public Long getChild() {
		return child;
	}
	public void setChild(Long child) {
		this.child = child;
	}
	
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
}

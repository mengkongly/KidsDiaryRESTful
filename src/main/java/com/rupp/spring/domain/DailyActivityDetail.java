package com.rupp.spring.domain;

/**
 * map to table daily_activity_details
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class DailyActivityDetail extends AbstractEntity {
	
	public static final String TABLE = "daily_activity_details";
	
	private Long activity;
	private Long dailyActivity;
	private int score;
	private boolean isApproved;
	private String note;
	
	public DailyActivityDetail() {}
	
	public DailyActivityDetail(Long id, Long activity, Long dailyActivity, int score, boolean isApproved, String note) {
		super.setId(id);
		setActivity(activity);
		setDailyActivity(dailyActivity);
		setScore(score);
		setApproved(isApproved);
		setNote(note);
	}
	
	public Long getActivity() {
		return activity;
	}
	public void setActivity(Long activity) {
		this.activity = activity;
	}
	
	public Long getDailyActivity() {
		return dailyActivity;
	}
	public void setDailyActivity(Long dailyActivity) {
		this.dailyActivity = dailyActivity;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}

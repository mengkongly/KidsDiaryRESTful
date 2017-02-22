package com.rupp.spring.domain;

/**
 * map to table user_types
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class UserType extends AbstractEntity {
	
	public static final String TABLE = "user_types";
	
	private String type;
	
	public UserType() {}
	public UserType(Long id, String type) {
		super.setId(id);
		setType(type);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

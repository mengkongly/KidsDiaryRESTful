package com.rupp.spring.domain;

/**
 * map to table user_types
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class UserType extends AbstractEntity {
	
	public static final String TABLE = "user_types";
	
	private String name;
	private String dialingCode;
	
	public UserType() {}
	public UserType(Long id, String name) {
		super.setId(id);
		setName(name);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

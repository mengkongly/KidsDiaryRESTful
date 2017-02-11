package com.rupp.spring.domain;

/**
 * map to table countries
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class Country extends AbstractEntity {
	
	public static final String TABLE = "countries";
	
	private String name;
	private String dialingCode;
	
	public Country() {}
	public Country(Long id, String name, String dialingCode) {
		super.setId(id);
		setName(name);
		setDialingCode(dialingCode);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDialingCode() {
		return dialingCode;
	}
	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}
}

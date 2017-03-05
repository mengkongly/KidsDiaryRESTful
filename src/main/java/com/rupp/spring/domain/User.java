package com.rupp.spring.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rupp.spring.utility.PasswordUtility;

/**
 * map to table users
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */
public class User extends AbstractEntity {
	
	public static final String TABLE = "users";
	
    private String username;
    private String password;
    private String accessToken;
    private Date loggedinDate;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String sex;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    
    private int country;
    private int userType;
    private boolean isActivated;
    
    public User() {}
    
    public User(Long id, String username, String password, String email, String phone, String firstName, String lastName, String sex, Date birthDate, int country, int userType, boolean isActivated) {
    	super.setId(id);
    	setUsername(username);
    	setPassword(password);
    	setAccessToken("");
    	setEmail(email);
    	setPhone(phone);
    	setFirstName(firstName);
    	setLastName(lastName);
    	setSex(sex);
    	setBirthDate(birthDate);
    	setCountry(country);
    	setUserType(userType);
    	setActivated(isActivated);
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = PasswordUtility.encrypt(password);
	}
	
	public void setEncryptPassword(String password) {
		this.password = password;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getLoggedinDate() {
		return loggedinDate;
	}

	public void setLoggedinDate(Date loggedinDate) {
		this.loggedinDate = loggedinDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullname() {
		return lastName + " " + firstName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}

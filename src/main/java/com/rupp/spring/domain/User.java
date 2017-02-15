package com.rupp.spring.domain;

import java.io.UnsupportedEncodingException;
import java.util.Date;
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
    private Date birthDate;
    private int country;
    private int userType;
    private boolean isActivated;
    
    public User() {}
    
    public User(Long id, String username, String password, String email, String phone, String firstName, String lastName, String sex, Date brthDate, int country, int userTYpe, boolean isActivated) throws UnsupportedEncodingException {
    	super.setId(id);
    	setUsername(username);
    	setPassword(password);
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
	
	public String getDecryptPassword() throws UnsupportedEncodingException {
		return PasswordUtility.decrypt(password);
	}

	public void setPassword(String password) throws UnsupportedEncodingException {
		this.password = PasswordUtility.encrypt(password);
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

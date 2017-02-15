package com.rupp.spring.utility;

import java.io.UnsupportedEncodingException;

/**
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */
public class PasswordUtility {

	public static final String SEPARATOR = "-";
	
	/**
	 * @param password
	 * @return String
	 */
	public static final String encrypt(String password) {
	     StringBuffer convert = new StringBuffer();
	     for (byte ch : password.getBytes()) {
	    	 convert.append(ch).append(PasswordUtility.SEPARATOR);
	     }
	     return convert.toString();
	}
	
	/**
	 * @param password
	 * @return String
	 */
	public static final String decrypt(String password) {
		StringBuffer convert = new StringBuffer();
		String[] parts = password.split(PasswordUtility.SEPARATOR);
		for (int i = 0; i < parts.length; i++) {
			convert.append((char)parts[i].charAt(i));
		}
		return convert.toString();
	}
	
	public static void main(String []args) {
		String test = PasswordUtility.encrypt("Hello");
		System.out.println(test);
		System.out.println(PasswordUtility.decrypt(test));
	}
}

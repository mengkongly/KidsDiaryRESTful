package com.rupp.spring.utility;

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
	     return convert.substring(0, convert.length() - 1);
	}
	
	/**
	 * @param password
	 * @return String
	 */
	public static final String decrypt(String password) {
		StringBuffer convert = new StringBuffer();
		String[] parts = password.split(PasswordUtility.SEPARATOR);
		for (String str : parts) {
			convert.append((char) Integer.parseInt(str));
		}
		return convert.toString();
	}
	
	public static void main(String []args) {
		String test = PasswordUtility.encrypt("Hello");
		System.out.println(test);
		System.out.println(PasswordUtility.decrypt(test));
	}
}

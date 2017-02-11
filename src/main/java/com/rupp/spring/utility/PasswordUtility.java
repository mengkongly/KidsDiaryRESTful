package com.rupp.spring.utility;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author Nguonchhay <a href='mailto:tnguonchhay@gmail.com'> Touch Nguonchhay </a>
 * @version $id$ - $Revision$
 * @date 2017
 */

public class PasswordUtility {

	/**
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String encrypt(String password) throws UnsupportedEncodingException {
	     byte[] encryptArray = Base64.getEncoder().encode(password.getBytes());
	     String encstr = new String(encryptArray,"UTF-8");  
	     return encstr;
	}
	
	/**
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String decrypt(String password) throws UnsupportedEncodingException {
	     byte[] decryptArray = Base64.getDecoder().decode(password.getBytes());
	     String decstr = new String(decryptArray,"UTF-8");  
	     return decstr;
	}
}

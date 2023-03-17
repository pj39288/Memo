package com.doongie.memo.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptService {

	//암호화 기능
	// static은 멤버변수 없이 사용할때 붙이는것
	// 즉 객체 생성을 안한단것
	public static String md5(String message) {
		
		String resultString = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			byte[] bytes= message.getBytes();
			md.update(bytes);
			
			byte[] digest = md.digest();
			
			// 16진수 형태의 문자열로 변환			
			for(int i = 0; i < digest.length; i++) {
				
				resultString += Integer.toHexString(digest[i] & 0xff);
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultString;
		
	}
}

package com.doongie.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doongie.memo.common.EncryptService;
import com.doongie.memo.user.dao.UserDAO;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		// 암호화하는중
		String encryptPassword = EncryptService.md5(password);
		
		// 암호화하고 DAO한테 전달하는 것
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
}

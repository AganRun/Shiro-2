package com.agan.shiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agan.shiro.dao.UserMapper;
import com.agan.shiro.entity.User;

@Service
public class UserService {

	@Autowired
	private UserMapper usermapper;
	
	public User getInfoByName(String username) {
		return usermapper.getInfoByUsername(username);
	}
}

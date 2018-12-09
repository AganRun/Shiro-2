package com.agan.shiro.dao;

import org.springframework.stereotype.Repository;

import com.agan.shiro.entity.User;

@Repository
public interface UserMapper {

	public User getInfoByUsername(String username);
}

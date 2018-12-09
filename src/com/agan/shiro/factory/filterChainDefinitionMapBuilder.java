package com.agan.shiro.factory;

import java.util.LinkedHashMap;

public class filterChainDefinitionMapBuilder {

	public LinkedHashMap<String, String> buildfilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		
		map.put("/login.jsp", "anon");
		map.put("/shiro/login", "anon");
		map.put("/shiro/logout", "logout");
		map.put("/admin.jsp", "authc,roles[admin]");
		map.put("/user.jsp", "authc,roles[user]");
		
		map.put("/list.jsp", "user");

		
		map.put("/**", "authc");
		
		
		return map;
	}
}

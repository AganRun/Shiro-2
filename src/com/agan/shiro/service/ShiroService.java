package com.agan.shiro.service;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

public class ShiroService {

	@RequiresRoles({"admin"})
	public void testMethod() {
		System.out.println("testShiroServiceMethod--->Date:" + new Date());
	}
	
	
}

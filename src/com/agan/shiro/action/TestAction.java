package com.agan.shiro.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agan.shiro.entity.User;
import com.agan.shiro.service.UserService;

@Controller
public class TestAction {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/Test")
	public String TestMethod() {
		User user = userService.getInfoByName("test");
		System.out.println(user.toString());
		return "list";
	}
}

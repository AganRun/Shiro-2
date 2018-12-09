package com.agan.shiro.handlers;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agan.shiro.service.ShiroService;

@Controller
@RequestMapping("/shiro")
public class ShiroHandler {
	
	
	@Autowired
	private ShiroService shiroService;
	
	@RequestMapping("/testShiroAnnotation")
	public String testShiroAnnotation(HttpSession session) {
		session.setAttribute("key", "value");
		shiroService.testMethod();
		return "redirect:/list.jsp";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if(!currentUser.isAuthenticated()) {
			//���û����������װΪUsernamePasswordTocken����
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			//remember(ǰ̨�Ƿ��ס��)
			token.setRememberMe(true);
			try {
				//ִ�е�¼
				currentUser.login(token);
			//������֤�쳣�ĸ���
			} catch (AuthenticationException e) {
				System.out.println("��¼ʧ��" + e.getMessage());
				return "login";
			}
		}
		
		
		return "redirect:/list.jsp";
	}
	
	
}

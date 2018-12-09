package com.agan.shiro.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.agan.shiro.dao.UserMapper;
import com.agan.shiro.entity.User;
import com.agan.shiro.service.UserService;
import com.sun.istack.internal.logging.Logger;

import jdk.nashorn.internal.runtime.options.LoggingOption.LoggerInfo;

public class ShiroRealm extends AuthenticatingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		  
		System.out.println("{ firstRealm } doGetAuthenticationInfo()");
		
		//1. ��AuthenticationToken ת��Ϊ UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
	
		//2. ��UserNamePasswordToken �л�ȡusername
		String username = upToken.getUsername();
		
		//3. �������ݿ�ķ����������ݿ��в�ѯ username ������û���¼
		System.out.println("�����ݿ��л�ȡ username: " + username + "����Ӧ����Ϣ----- ");
		
		
		//4. ���û������ڣ�������׳�UnknownAccountException
		if("unknown".equals(username)) {
			throw new UnknownAccountException("�û�������");
		}
		
		//5. �����û���Ϣ����������Ƿ��׳�����AuthenticationException
		if("monster".equals(username)) {
			throw new LockedAccountException("�û�������");
		}
		
		/**
		 * 
		 * 6. �����û��������������AuthenticationInfo ���󲢷���
		 * 	ͨ��ʹ�õ�ʵ����Ϊ: SimpleAuthenticationInfo
		 * 	������Ϣ�Ǵ����ݿ��л�ȡ��
		 */
		//	1). principal: ��֤��ʵ����Ϣ��������username, Ҳ���������ݱ��Ӧ���û���ʵ�������
		Object principal = username;
		
		//	2). credentials: ����
		Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";	//123456����ͨMD5���ܺ�
		if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		
		//	3). realmName: ��ǰ realm �����name�� ���ø���� getName() ��������
		String realmName = getName();
		
		//	4). ��ֵ
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		
		
		SimpleAuthenticationInfo info;//��ͨ new SimpleAuthenticationInfo(principal, credentials, realmName);
		info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		
		return info;
	}

	
	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		Object credentials = "123456";
		Object salt = ByteSource.Util.bytes("user");
		int hashInterations = 1024;
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashInterations);
		System.out.println(result);
	}
	

	
	

}

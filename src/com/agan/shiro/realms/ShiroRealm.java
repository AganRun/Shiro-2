package com.agan.shiro.realms;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class ShiroRealm extends AuthorizingRealm{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		  
		System.out.println("{ firstRealm } doGetAuthenticationInfo()");
		
		//1. 把AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
	
		//2. 从UserNamePasswordToken 中获取username
		String username = upToken.getUsername();
		
		//3. 调用数据库的方法，从数据库中查询 username 对象的用户记录
		System.out.println("从数据库中获取 username: " + username + "所对应的信息----- ");
		
		
		//4. 若用户不存在，则可以抛出UnknownAccountException
		if("unknown".equals(username)) {
			throw new UnknownAccountException("用户不存在");
		}
		
		//5. 根据用户信息的情况觉得是否抛出其他AuthenticationException
		if("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		
		/**
		 * 
		 * 6. 根据用户的情况，来构建AuthenticationInfo 对象并返回
		 * 	通常使用的实现类为: SimpleAuthenticationInfo
		 * 	以下信息是从数据库中获取的
		 */
		//	1). principal: 认证的实体信息，可以是username, 也可以是数据表对应的用户的实体类对象
		Object principal = username;
		
		//	2). credentials: 密码
		Object credentials = "fc1709d0a95a6be30bc5926fdb7f22f4";	//123456的普通MD5加密后
		if("admin".equals(username)){
			credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
		}else if("user".equals(username)) {
			credentials = "098d2c478e9c11555ce2823231e02ec1";
		}
		
		//	3). realmName: 当前 realm 对象的name， 调用父类的 getName() 方法即可
		String realmName = getName();
		
		//	4). 盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		
		
		
		SimpleAuthenticationInfo info;//普通 new SimpleAuthenticationInfo(principal, credentials, realmName);
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


	//完成授权需要实现的方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//1. 从PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		System.out.println("doGetAuthorizationInfo()-->principal-->" + principal);
		
		//2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if("admin".equals(principal)) {
			roles.add("admin");
		}
		
		//3. 创建 SimpleAuthorizationInfo, 并设置其roles属性
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		
		//4. 返回SimpleAuthorizationInfo 对象
		return info;
		
	}
	

	
	

}

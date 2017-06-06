package com.alone.web.utils;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alone.web.entity.AdminUser;
import com.alone.web.service.AdminUserService;

public class ShiroRealm extends AuthorizingRealm {

	private static Logger log = Logger.getLogger(ShiroRealm.class);
	@Autowired
	private AdminUserService adminUserService;
	
	public ShiroRealm() {
		super();
		//密码认证器
		HashedCredentialsMatcher  matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName(MyConst.ALGORITHMNAME);
		matcher.setHashIterations(MyConst.ITERATIONS);
		setCredentialsMatcher(matcher);
	}

	//权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//身份认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		log.info("shiro认证开始");
		String username = (String) token.getPrincipal();//获取用户名
		//根据用户名用户
		AdminUser user=adminUserService.findAdminUserByUsername(username);
		if(null != user){
			//对盐加密
			ByteSource salt = Crypto.cryptoSalt(username);
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), salt, getName());
			return authenticationInfo;
		}
		log.info("用户不存在");
		return null;
	}

}

package com.weasel.penetrate.manager.infrastructure.shiro;

import com.weasel.penetrate.manager.domain.User;
import com.weasel.penetrate.manager.infrastructure.exception.LockAccountException;
import com.weasel.penetrate.manager.infrastructure.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * @author Dylan
 * @time 2013-8-2
 */
public class ShiroRealm extends AuthorizingRealm{

	private final static Logger LOG = LoggerFactory.getLogger(ShiroRealm.class);
	
	public final static String REALM_NAME = "ShiroCasRealm";
	
	@Autowired
	private UserRepository userRepository;
	
	public ShiroRealm() {
		setName(REALM_NAME); // This name must match the name in the User
								// class's getPrincipals() method
		setCredentialsMatcher(new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME));
	}
	
	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		if(LOG.isTraceEnabled()){
			LOG.trace("开始认证 "+ username);
		}
		try {
			if(StringUtils.isBlank(username)){
				throw new AccountException("username is blank,can not handle this login");
			}
			User user = userRepository.getUserByName(username);
			checkUser(user, username);
			return new SimpleAuthenticationInfo(user.getName(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		} catch (Exception e) {
			throw translateAuthenticationException(e);
		}
	}
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		String username = (String)getAvailablePrincipal(principals);
		
		if(LOG.isTraceEnabled()){
			LOG.trace("开始授权 "+ username);
		}
		
		User user = userRepository.getUserByName(username);
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Set<String> rolesAsString = user.getRolesAsString();
		info.addRoles(rolesAsString);
		return info;
	}

	/**
	 * 异常转换
	 * @param e
	 * @return
	 */
	private AuthenticationException translateAuthenticationException(Exception e) {
		if (e instanceof AuthenticationException) {
			return (AuthenticationException) e;
		}
		if(e instanceof DisabledAccountException){
			return (DisabledAccountException)e;
		}
		if(e instanceof UnknownAccountException){
			return (UnknownAccountException)e;
		}
		return new AuthenticationException(e);
	}
	/**
	 * 检查用户
	 * @param user
	 * @param username
	 */
	private void checkUser(User user,String username){
		if(null == user){
			throw new UnknownAccountException(username + " can not find "+username+" from system");
		}
		if(user.isLocked()){
			throw new LockAccountException("the account is locked now");
		}
	}
	
}

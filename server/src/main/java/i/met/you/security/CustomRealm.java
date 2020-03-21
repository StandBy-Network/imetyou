package i.met.you.security;

import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import i.met.you.util.ApplicationLogger;




public class CustomRealm extends AuthorizingRealm {
	
	static Logger  LOGGER = ApplicationLogger.getLogger(CustomRealm.class);

	public CustomRealm() {
	
		setName("CustomRealm");
		setCredentialsMatcher(new CustomCredentialsMatcher());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		//UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	
		//String username = token.getUsername();

		return null;
	}

}

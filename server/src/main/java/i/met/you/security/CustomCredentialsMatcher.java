package i.met.you.security;

import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import i.met.you.util.ApplicationLogger;
import i.met.you.util.ApplicationUtil;

public class CustomCredentialsMatcher implements CredentialsMatcher {

	static Logger LOGGER = ApplicationLogger.getLogger(CustomRealm.class);

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

		if (token.getCredentials() == null) {
			// log.warn("Rejecting null token credentials for {}", token.getPrincipal());
			return false;
		}
		if (info.getCredentials() == null) {
			// log.warn("Rejecting null stored credentials for {}", info.getPrincipals());
			return false;
		}

		// final String host = token instanceof HostAuthenticationToken ?
		// ((HostAuthenticationToken) token).getHost() : null;

		final String passwordToken = String.valueOf((char[]) token.getCredentials());
		final String userPassword = info.getCredentials().toString();
		String encriptedPassword = ApplicationUtil.generatePassword(passwordToken);

		// itt most picit hátbaverjük
		if (passwordToken.length() < 200) {
			if (encriptedPassword.equals(userPassword)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

}

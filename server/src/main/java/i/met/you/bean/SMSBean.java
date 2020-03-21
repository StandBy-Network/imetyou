/**
 * 
 */
package i.met.you.bean;

/**
 * @author levi
 *
 */
public class SMSBean extends AbstractBaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4087058487805998881L;

	String authSid;

	String authToken;

	String fromNumber;

	String toNumber;

	String messageBody;

	public String getAuthSid() {
		return authSid;
	}

	public void setAuthSid(String authSid) {
		this.authSid = authSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

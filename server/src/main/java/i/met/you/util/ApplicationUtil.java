/**
 * 
 */
package i.met.you.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import i.met.you.ApplicationConstants;

/**
 * @author levi
 *
 */
public class ApplicationUtil {

	// TODO implment this function
	public static Object getCurrentUser() {
		return null;
	}

	public static String readRequestBody(HttpServletRequest request) throws IOException {
		BufferedReader reader = request.getReader();
		StringWriter sw = new StringWriter();
		char[] buff = new char[1024];
		int read = 0;
		while ((read = reader.read(buff)) > 0) {
			sw.write(buff, 0, read);
		}
		return sw.toString();
	}

	public static String getMessage(String message, Locale locale) {

		ResourceBundle bundle = ResourceBundle.getBundle("i18n/ApplicationResources", locale);
		String result = "";
		try {
			result = bundle.getString(message);
		} catch (Exception e) {
			result = "???" + message + "???";
		}

		return result;

	}

	// mobile device detection from http request
	public static Boolean isMobileDevcice(HttpServletRequest req) {
		Boolean returnValue = false;
		if (req != null) {
			if (req.getHeader("User-Agent").indexOf("Mobi") != -1) {
				returnValue = true;
			}
		}
		return returnValue;
	}

	// id generator method. can generate domain specific id and random id
	public static String generateId(String code) {
		String returnValue = "";
		if (code == null) {
			Date now = new Date();
			Long longNow = now.getTime();
			try {
				returnValue = Base64.getUrlEncoder()
						.encodeToString(String.valueOf(longNow).getBytes((StandardCharsets.UTF_8.toString())));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			returnValue = Base64.getUrlEncoder().encodeToString(code.getBytes());
		}
		return returnValue;
	}

	// decode base64 encoded id
	public static String decodeId(String id) {
		String returnValue = "";
		if (id != null) {
			try {
				returnValue = new String(Base64.getUrlDecoder().decode(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	// standard password generator method
	public static String generatePassword(String password) {
		String returnValue = "";
		if (password != null) {
			returnValue = DigestUtils.sha256Hex(password);
		}
		return returnValue;
	}

	// activation token generator
	public static String generateActivationToken(Integer length) {
		String returnValue = "";
		Random random = new Random();
		String CHARS = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
		StringBuilder token = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			token.append(CHARS.charAt(random.nextInt(CHARS.length())));
		}
		returnValue = token.toString();
		return returnValue;

	}

	public static Locale getLocale(HttpServletRequest req) {
		Locale returnValue = ApplicationConstants.DEFAULT_LOCALE;
		// TODO implement this method

		return returnValue;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}

/**
 * 
 */
package i.met.you.bean;

/**
 * @author levi
 *
 */
public class SessionParameterBean extends AbstractBaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 516565214007992377L;

	GeoLocationBean geoBean = new GeoLocationBean();

	String sessionLanguageCode = "";

	String lookfor = "";

	public GeoLocationBean getGeoBean() {
		return geoBean;
	}

	public void setGeoBean(GeoLocationBean geoBean) {
		this.geoBean = geoBean;
	}

	public String getSessionLanguageCode() {
		return sessionLanguageCode;
	}

	public void setSessionLanguageCode(String sessionLanguageCode) {
		this.sessionLanguageCode = sessionLanguageCode;
	}

	public String getLookfor() {
		return lookfor;
	}

	public void setLookfor(String lookfor) {
		this.lookfor = lookfor;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	

}

/**
 * 
 */
package i.met.you.bean;

/**
 * @author levi
 *
 */
public class GeoLocationBean extends AbstractBaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3992850637550919567L;

	Long latitude;

	Long longitude;

	Float latN = null;

	Float latS = null;

	Float longW = null;

	Float longE = null;

	String cityName = "";

	String address = "";

	String locationName = "";

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Float getLatN() {
		return latN;
	}

	public void setLatN(Float latN) {
		this.latN = latN;
	}

	public Float getLatS() {
		return latS;
	}

	public void setLatS(Float latS) {
		this.latS = latS;
	}

	public Float getLongW() {
		return longW;
	}

	public void setLongW(Float longW) {
		this.longW = longW;
	}

	public Float getLongE() {
		return longE;
	}

	public void setLongE(Float longE) {
		this.longE = longE;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package i.met.you.bean;

/**
 * @author levi
 *
 */
public class WebOption extends AbstractBaseBean implements Comparable<WebOption> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -677746259770151994L;
	
	public WebOption() {
		
	}
	
	public WebOption(String key,String value) {
		this.key=key;
		this.value= value;
	}

	private String key = "";

	private String value = "";

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int compareTo(WebOption o) {

		return this.getValue().compareTo(o.getValue());

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package i.met.you.bean;

/**
 * @author levi
 *
 */
public class Select2InputParams extends AbstractBaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2545533171946223524L;

	// Query parameters
	private String q = "";

	private Integer pageLimit = 0;

	private Integer page = 0;

	private String sortOrder = "";

	private Integer start = 0;

	private Integer max = 10;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public Integer getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(Integer pageLimit) {
		this.pageLimit = pageLimit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

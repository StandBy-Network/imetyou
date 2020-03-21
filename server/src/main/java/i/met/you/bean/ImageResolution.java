package i.met.you.bean;

import java.io.Serializable;

public class ImageResolution extends AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6647396354428976248L;
	private Integer width = 0;
	private Integer height = 0;

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}

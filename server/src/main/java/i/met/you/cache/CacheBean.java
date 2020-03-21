package i.met.you.cache;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Cache elemet leíró java osztály.
 * 
 * @author levente
 * @see com.qualeesys.vpc.cache.CacheServiceImpl
 *
 */
public class CacheBean implements Serializable {

	private static final long serialVersionUID = 2245152756046308087L;

	private String key = "";

	private Object value;

	private Long validity = null;

	/**
	 * Üres konstruktor
	 */
	public CacheBean() {
	}

	/**
	 * Kulcs étrék pár konstruktor
	 * 
	 * @param key   Szöveges érték ami a store-on belüli egyedi azonosítója lesz az
	 *              értéknek.
	 * @param value A kulcshoz tartozó érték.
	 */
	public CacheBean(String key, Object value) {
		this.setKey(key);
		this.setValue(value);

	}

	/**
	 * Kulcs, érték és érvényességi idővel példányosító metódus.
	 * 
	 * @param key      Szöveges érték ami a store-on belüli egyedi azonosítója lesz
	 *                 az értéknek.
	 * @param value    A kulcshoz tartozó érték.
	 * @param validity Long ként megadott érvényességi idő.Azt jelenti, hogy meddig
	 *                 maradjon a cache-ben. <br>
	 */
	public CacheBean(String key, Object value, Long validity) {
		this.setKey(key);
		this.setValue(value);
		this.setValidity(validity);
	}

	/**
	 * Visszaadja a példányon belüli kulcs változó értékét.
	 * 
	 * @return kulcs
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Beállítja a példányon belüli kulcs változó értékét.
	 * 
	 * @param key kulcs
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Visszaadja a példányon belüli értéket változó értékét.
	 * 
	 * @return érték
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Beállítja a példányon belüli értéket változó értékét.
	 * 
	 * @param value érték
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Visszaadja a példány érvényességi idejét.
	 * 
	 * @return érvényességi idő
	 */
	public Long getValidity() {
		return validity;
	}

	/**
	 * Beállítja a példány érvényességi idejét.
	 * 
	 * @param validity érvényességi idő.
	 */
	public void setValidity(Long validity) {
		this.validity = validity;
	}

	/**
	 * ReflectionToStringBuilder-el előállított toString értéket adja vissza.
	 */
	@JsonIgnore
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}

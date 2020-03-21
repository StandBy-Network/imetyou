package i.met.you.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Hash típusú memória cache megvalósítás a gyakran használt elemekre. Az osztály szolgáltatás típusú singleton. A példányosítását illetve a futó példányt a
 * <b>CacheServiceImpl.getInstance() </b> statikus metódussal lehet elérni.
 * 
 * @see com.qualeesys.vpc.cache.CacheBean
 */
public class CacheServiceImpl {

	private static volatile CacheServiceImpl instance = null;

	private Map<String, CacheBean> store = null;
	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock readLock = readWriteLock.readLock();
	private final Lock writeLock = readWriteLock.writeLock();

	/**
	 * Visszaadja az osztály futó példányát ha létezik ha nem létezik akkor példányosítja.
	 * 
	 * 
	 */
	public static CacheServiceImpl getInstance() {
		if (instance == null) {
			synchronized (CacheServiceImpl.class) {
				if (instance == null) {
					instance = new CacheServiceImpl();
				}
			}
		}
		return instance;
	}

	/**
	 * Az osztály példányosításakor létrehoz egy HashMap<String, CacheBean> típusú store-t.
	 * 
	 */
	private CacheServiceImpl() {
		this.setStore(new HashMap<String, CacheBean>());
	}

	/**
	 * Visszaadja a példányon belül lévő HashMap típusú store-t
	 * @return store =HashMap()
	 */
	public Map<String, CacheBean> getStore() {
		readLock.lock();
		try {
			return new HashMap<>(store);
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * Beállítja a példányon belül lévő HashMap típusú store-t
	 * 
	 * @param store
	 *            HashMap
	 */
	public void setStore(Map<String, CacheBean> store) {
		writeLock.lock();
		try {
			this.store = store;
		} finally {
			writeLock.unlock();
		}
	}

	/**
	 * Visszaadja a paraméterül megadott kulcshoz tartozó CacheBean-t.
	 * 
	 * @param key
	 *            Szöveges érték. Kulcs ami alapján kereünk a cache-ben.
	 * @return null ha nem találunk a kulcs alapján értéket. Egyébként a kulcshoz tartozó CacheBen típusú értéket adja vissza.
	 */
	public Object get(String key) {
		readLock.lock();
		try {
			return this.getStore().get(key);
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * Visszaadja a paraméterül megadott kulcshoz tartozó értéket.
	 * 
	 * @param key
	 *            Szöveges érték. Kulcs ami alapján kereünk a cache-ben.
	 * @return null ha nem találunk a kulcs alapján értéket. Egyébként a kulcshoz tartozó CacheBen.getValue() Object típusú értéket adja vissza.
	 */
	public Object getValue(String key) {
		readLock.lock();
		try {
			CacheBean bean = this.getStore().get(key);
			Object obj = null;
			if (bean != null) {
				obj = bean.getValue();
			}
			return obj;
		} finally {
			readLock.unlock();
		}
	}

	/**
	 * Törli a kulcsot és a hozzá tartozó CacheBean-t.
	 * 
	 * @param key
	 *            Szöveges érték. Kulcs ami alapján kereünk a cache-ben
	 */
	public void remove(String key) {
		writeLock.lock();
		try {
			this.getStore().remove(key);
		} finally {
			writeLock.unlock();
		}

	}

	/**
	 * Létrehoz egy új CacheBean típusú objektumot, melynek kulcs és érték-ét beállítja és a paraméterül kapott kulccsal felveszi a store-ba.
	 * 
	 * @param key
	 *            Szöveges érték ami a store-on belüli egyedi azonosítója lesz az értéknek.
	 * @param value
	 *            A kulcshoz tartozó érték.
	 */
	public void set(String key, Object value) {
		writeLock.lock();
		try {
			CacheBean cacheBean = new CacheBean(key, value);
			this.getStore().put(key, cacheBean);
		} finally {
			writeLock.unlock();
		}

	}

	/**
	 * Létrehoz egy új CacheBean típusú objektumot, melynek kulcs, érték és érvényességét beállítja, végül a paraméterül kapott kulccsal felveszi a store-ba.
	 * 
	 * @param key
	 *            Szöveges érték ami a store-on belüli egyedi azonosítója lesz az értéknek.
	 * @param value
	 *            A kulcshoz tartozó érték.
	 * @param validity
	 *            Long ként megadott érvényességi idő.Azt jelenti, hogy meddig maradjon a cache-ben. <br>
	 *            <b>Nincs implementálva!</b> <br>
	 *            Implementálás egy időzített task-ot jelent ami fél percenként ellenőrzi a cache-ben lévő adatok érvényességét és megsemmisíti azokat ha az
	 *            érvényességük lejárt.<br>
	 *            Illetve a cache-ben történő kereséskor ellenőrzi a lejáratot és ha lejárt akkor frissíti az értékét vagy törli.
	 */
	public void set(String key, Object value, Long validity) {
		writeLock.lock();
		try {
			CacheBean cacheBean = new CacheBean(key, value, validity);
			this.getStore().put(key, cacheBean);
		} finally {
			writeLock.unlock();
		}

	}

	public void initialize() {
		
		
		/**
		 * Figyelni kell, hogy a cache engedélyezve van-e! Egyenlőre constants-ba rakjuk aztán appconfig.properties-be
		 * A szerepek nem módosíthatóak így kb mehet cache-ből
		 * Felhasználókból csak a nem törölteket és nem inaktívokat kell cache-elni (username szerint biztosan)
		 * Felhasználói privát beállításokat kell cache-elni
		 * Globális beállításokat kell cache-elni
		 * Centrum szintű beállításokat kell cachelni
		 * Torzsadatokat mennyiségtől függően kéne cachelni
		 * 
		 */
		

		// INITILAIZE CACHE
		/*
		 * DocumentServiceImpl documentService = DocumentServiceImpl.getInstance(); UserViewServiceImpl userViewService = UserViewServiceImpl.getInstance();
		 * RoleViewServiceImpl roleViewService = RoleViewServiceImpl.getInstance(); ParameterViewServiceImpl parameterViewService =
		 * ParameterViewServiceImpl.getInstance();
		 * 
		 * 
		 * 
		 * List<DocumentImpl> schemaList = documentService.getAllSchema(); Iterator <DocumentImpl> it = schemaList.iterator(); DocumentImpl doc=null; while
		 * (it.hasNext()){ doc = it.next(); this.set(doc.getDocTypeName(), doc);
		 * 
		 * }
		 * 
		 * 
		 * List<UserViewImpl> userViewList = userViewService.findAll( "SELECT * FROM USER_VIEW", -1, -1); Iterator <UserViewImpl>userViewIt =
		 * userViewList.iterator(); UserViewImpl userView=null; while(userViewIt.hasNext()){ userView=userViewIt.next();
		 * this.set("USER_VIEW_BY_TSZ_"+userView.getTsz(), userView); }
		 * 
		 * 
		 * List<RoleViewImpl> roleViewList = roleViewService.findAll( "SELECT * FROM ROLE_VIEW", -1, -1); Iterator <RoleViewImpl>roleViewIt =
		 * roleViewList.iterator(); RoleViewImpl roleView=null; while(roleViewIt.hasNext()){ roleView=roleViewIt.next();
		 * this.set("ROLE_VIEW_BY_ROLE_NAME_"+roleView.getRoleName(), roleView); }
		 * 
		 */

	}

	public void reset() {
		writeLock.lock();
		try {
			this.setStore(new HashMap<String, CacheBean>());
		} finally {
			writeLock.unlock();
		}

	}

}

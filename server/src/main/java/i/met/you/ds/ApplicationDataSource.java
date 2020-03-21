/**
 * 
 */
package i.met.you.ds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.Logger;
import i.met.you.ApplicationConstants;
import i.met.you.util.ApplicationLogger;

/**
 * @author levi
 *
 */
public class ApplicationDataSource {

	private static final Logger logger = ApplicationLogger.getLogger(ApplicationDataSource.class);

	private BasicDataSource basicDataSource = null;

	private static volatile ApplicationDataSource instance = null;

	public ApplicationDataSource() {
	}

	public static ApplicationDataSource getInstance() {
		if (instance == null) {
			synchronized (ApplicationDataSource.class) {
				if (instance == null) {
					instance = new ApplicationDataSource();
					instance.setBasicDataSource(instance.getDataSource());
				}
			}
		}
		return instance;
	}

	public BasicDataSource getBasicDataSource() {
		return this.basicDataSource;
	}

	public void setBasicDataSource(BasicDataSource bds) {
		this.basicDataSource = bds;
	}

	public Connection getConnection() {
		ApplicationDataSource nds = ApplicationDataSource.getInstance();
		Properties properties = new Properties();
		try {
			File f = new File(ApplicationConstants.CONFIG_LOCATION + "/database.properties");
			FileInputStream fis = new FileInputStream(f);
			properties.load(fis);
			// properties.load(nds.getClass().getResourceAsStream(
			// "/META-INF/database.properties"));

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Properties props = new Properties();
		props.put("remarksReporting", "true");
		props.put("user", properties.getProperty("database.username"));
		props.put("password", properties.getProperty("database.password"));
		Connection dbConn = null;
		try {
			Class.forName(properties.getProperty("database.driverClassName"));
			String url = properties.getProperty("database.url");
			dbConn = DriverManager.getConnection(url, props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConn;
		// DatabaseMetaData dbmd = dbConn.getMetaData();
	}

	public BasicDataSource getDataSource() {
		ApplicationDataSource nds = ApplicationDataSource.getInstance();
		BasicDataSource bds = null;
		if (nds.getBasicDataSource() == null) {
			try {
				bds = new BasicDataSource();
				Properties properties = new Properties();
				// properties.load(nds.getClass().getResourceAsStream("/META-INF/database.properties"));
				File f = new File(ApplicationConstants.CONFIG_LOCATION + "/database.properties");
				FileInputStream fis = new FileInputStream(f);
				properties.load(fis);
				bds.setDriverClassName(properties.getProperty("database.driverClassName"));
				bds.setUrl(properties.getProperty("database.url"));
				bds.setUsername(properties.getProperty("database.username"));
				bds.setPassword(properties.getProperty("database.password"));
				bds.setValidationQueryTimeout(5);
				bds.setTestOnBorrow(true);
				bds.setTestOnReturn(true);
				bds.setTestWhileIdle(true);
				bds.setTimeBetweenEvictionRunsMillis(1800000);
				bds.setNumTestsPerEvictionRun(3);
				bds.setMinEvictableIdleTimeMillis(1800000);
				bds.setValidationQuery("SELECT 1");
				nds.setBasicDataSource(bds);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return bds;

	}

	public static void main(String[] args) {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

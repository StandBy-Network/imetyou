package i.met.you.migration;

import org.apache.logging.log4j.Logger;
import org.flywaydb.core.Flyway;

import i.met.you.ds.ApplicationDataSource;
import i.met.you.util.ApplicationLogger;



public class DbMigration {
	
	static Logger  LOGGER = ApplicationLogger.getLogger(DbMigration.class);
	
	public static void migrate() {
		try {
			System.out.println("db migration started");
			ApplicationDataSource ds = ApplicationDataSource.getInstance();
			Flyway flyway = Flyway.configure().dataSource(ds.getBasicDataSource()).load();
			flyway.migrate();
			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		DbMigration.migrate();
	}
}

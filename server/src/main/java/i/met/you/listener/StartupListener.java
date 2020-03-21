package i.met.you.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;

import i.met.you.migration.DbMigration;
import i.met.you.util.ApplicationLogger;

public class StartupListener implements ServletContextListener {

	static Logger LOGGER = ApplicationLogger.getLogger(StartupListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// update database
		DbMigration.migrate();

		System.out.println("Most indultam!s");

	}

	public static void setupContext(ServletContext context) {

	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {

	}
}

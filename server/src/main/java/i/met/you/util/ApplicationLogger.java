package i.met.you.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApplicationLogger {
	
	
	
	public static Logger getLogger(Class<?> clazz) {
		return (Logger) LogManager.getLogger(clazz);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

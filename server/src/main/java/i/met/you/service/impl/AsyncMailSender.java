/**
 * 
 */
package i.met.you.service.impl;

import javax.mail.Message;
import javax.mail.Transport;

import org.apache.logging.log4j.Logger;

import i.met.you.util.ApplicationLogger;

/**
 * @author levi
 *
 */
public class AsyncMailSender implements Runnable {

	private static final Logger LOGGER = ApplicationLogger.getLogger(AsyncMailSender.class);

	Message msg = null;

	private Thread runner;

	public void init(Message msg) {

		LOGGER.info("AsyncMailSender.init");
		this.msg = msg;
		runner = null;
		runner = new Thread(this);
		runner.start();
	}

	@Override
	public void run() {
		LOGGER.info("AsyncMailSender.run");

		try {
			if (this.msg != null) {
				Transport.send(msg);
			}
		} catch (Exception e) {
			LOGGER.error("AsyncMailSender.error :" + e.getMessage());
		}

	}

	public void destroy() {
	}

}

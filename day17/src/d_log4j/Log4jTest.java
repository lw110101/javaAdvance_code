package d_log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class Log4jTest {

	@Test
	public void testLog4j() {
		Log log = LogFactory.getLog(Log4jTest.class);
		log.debug("����");
		log.info("��Ϣ");
		log.warn("����");
		log.error("����");
	}
}

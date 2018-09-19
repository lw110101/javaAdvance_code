package d_log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class Log4jTest {

	@Test
	public void testLog4j() {
		Log log = LogFactory.getLog(Log4jTest.class);
		log.debug("调试");
		log.info("信息");
		log.warn("警告");
		log.error("错误");
	}
}

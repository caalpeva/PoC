package team.boolbee.poc.spring.beans.performers;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Instrumentalist2 extends Instrumentalist implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		tuneInstrument();
	}
	
	@Override
	public void destroy() throws Exception {
		cleanInstrument();
	}
}
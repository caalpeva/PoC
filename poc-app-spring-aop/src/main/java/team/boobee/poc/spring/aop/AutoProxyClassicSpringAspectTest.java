package team.boobee.poc.spring.aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.TalentCompetition;

public class AutoProxyClassicSpringAspectTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("context-aop-autoproxy.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
	}

}

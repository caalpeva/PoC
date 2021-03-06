package team.boobee.poc.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import team.boolbee.poc.spring.beans.TalentCompetition;

public class AspectJTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-aop-aspectj.xml");
		TalentCompetition competition = (TalentCompetition) context.getBean("springIdol");
		competition.run();
	}
}
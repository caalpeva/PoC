package team.boolbee.poc.spring.jms;

import team.boolbee.poc.spring.jms.model.Person;

public interface HrDepartmentGateway {
	public void sendPersonInfo(Person person);
}
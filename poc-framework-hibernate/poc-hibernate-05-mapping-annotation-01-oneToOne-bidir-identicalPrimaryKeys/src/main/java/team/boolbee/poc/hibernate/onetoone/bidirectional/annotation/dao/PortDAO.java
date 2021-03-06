package team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.dao;

import java.util.List;

import org.hibernate.Session;

import team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.model.Port;
import team.boolbee.poc.hibernate.onetoone.bidirectional.annotation.model.Service;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class PortDAO implements PortDAOInterface {

	public Port selectById(Long id) {
	    Session session = HibernateSession.getSession();
	    Port port = (Port) session.get(Port.class, id);
	    session.close();
	    return port;
	}

	@SuppressWarnings("unchecked")
	public List<Port> selectAll() {
		Session session = HibernateSession.getSession();
	    List<Port> ports = session.createQuery("from Port").list();
	    session.close();
	    return ports;
	}
	
	public void insert(Port port) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	 
	    session.persist(port);
	    
	    session.getTransaction().commit();	         
	    session.close();
	}
	
	public void insert(Port port, Long serviceId) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	    
	    Service service = (Service) session.get(Service.class, serviceId);
	    port.setService(service);
	    session.persist(port);
	    
	    session.getTransaction().commit();	         
	    session.close();
	}

	public void update(Port port) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	 
	    session.merge(port);
	    
	    session.getTransaction().commit();		 
	    session.close();
	}
	

	public void delete(Port port) {
		Session session = HibernateSession.getSession();
	    session.beginTransaction();
	    
	    session.delete(port);
	 
	    session.getTransaction().commit();
	    session.close();
	}
}
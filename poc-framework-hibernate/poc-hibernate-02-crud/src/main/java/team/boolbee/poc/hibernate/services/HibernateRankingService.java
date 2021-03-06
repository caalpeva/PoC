package team.boolbee.poc.hibernate.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import team.boolbee.poc.hibernate.model.Person;
import team.boolbee.poc.hibernate.model.Ranking;
import team.boolbee.poc.hibernate.model.Skill;
import team.boolbee.poc.hibernate.utils.HibernateSession;

public class HibernateRankingService implements RankingService {

	public int getRankingFor(String subject, String skill) {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		int average = getRankingFor(session, subject, skill);

		tx.commit();
		session.close();

		return average;
	}

	public void addRanking(String subject, String observer, String skill, int ranking) {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		addRanking(session, subject, observer, skill, ranking);

		tx.commit();
		session.close();
	}

	public void updateRanking(String subject, String observer, String skill, int rank) {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		Ranking ranking = findRanking(session, subject, observer, skill);
		if (ranking == null) {
			addRanking(subject, observer, skill, rank);
		} else {
			ranking.setRanking(rank);
		}

		tx.commit();
		session.close();
	}

	public void removeRanking(String subject, String observer, String skill) {
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		removeRanking(session, subject, observer, skill);

		tx.commit();
		session.close();
	}

	public Map<String, Integer> findRankingsFor(String subject) {
		Map<String, Integer> results;
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		results = findRankingsFor(session, subject);

		tx.commit();
		session.close();

		return results;
	}

	public Person findBestPersonFor(String skillType) {
		Person person = null;
		Session session = HibernateSession.getSession();
		Transaction tx = session.beginTransaction();

		person = findBestPersonFor(session, skillType);

		tx.commit();
		session.close();

		return person;
	}

	private Person savePerson(Session session, String name) {
		Person person = findPerson(session, name);
		if (person == null) {
			person = new Person();
			person.setName(name);
			session.save(person);
		}

		return person;
	}

	private Person findPerson(Session session, String name) {
		Query query = session.createQuery("from Person p where p.name=:name");
		query.setParameter("name", name);
		Person person = (Person) query.uniqueResult();
		return person;
	}

	private Skill saveSkill(Session session, String skillName) {
		Skill skill = findSkill(session, skillName);
		if (skill == null) {
			skill = new Skill();
			skill.setName(skillName);
			session.save(skill);
		}
		return skill;
	}

	private Skill findSkill(Session session, String name) {
		Query query = session.createQuery("from Skill s where s.name=:name");
		query.setParameter("name", name);
		Skill skill = (Skill) query.uniqueResult();
		return skill;
	}

	@SuppressWarnings("unchecked")
	private int getRankingFor(Session session, String subject, String skill) {
		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:name "
				+ "and r.skill.name=:skill");
		query.setString("name", subject);
		query.setString("skill", skill);
		int sum = 0;
		int count = 0;
		for (Ranking r : (List<Ranking>) query.list()) {
			count++;
			sum += r.getRanking();
			System.out.println(r);
		}
		return count == 0 ? 0 : sum / count;
	}

	private void addRanking(Session session, String subjectName, String observerName, String killName, int rank) {
		Person subject = savePerson(session, subjectName);
		Person observer = savePerson(session, observerName);
		Skill skill = saveSkill(session, killName);

		Ranking ranking = new Ranking();
		ranking.setSubject(subject);
		ranking.setObserver(observer);
		ranking.setSkill(skill);
		ranking.setRanking(rank);
		session.save(ranking);
	}

	private Ranking findRanking(Session session, String subject, String observer, String skill) {
		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:subject "
				+ "and r.observer.name=:observer "
				+ "and r.skill.name=:skill");
		query.setString("subject", subject);
		query.setString("observer", observer);
		query.setString("skill", skill);
		return (Ranking) query.uniqueResult();
	}

	private void removeRanking(Session session, String subject, String observer, String skillType) {
		Ranking ranking = findRanking(session, subject, observer, skillType);
		if (ranking != null) {
			session.delete(ranking);
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, Integer> findRankingsFor(Session session, String subject) {
		Map<String, Integer> results = new HashMap<String, Integer>();

		Query query = session.createQuery("from Ranking r "
				+ "where r.subject.name=:subject "
				+ "order by r.skill.name");
		query.setParameter("subject", subject);
		
		List<Ranking> rankings = (List<Ranking>) query.list();
		String lastSkillName = "";
		int sum = 0;
		int count = 0;
		for (Ranking r : rankings) {
			if (!lastSkillName.equals(r.getSkill().getName())) {
				sum = 0;
				count = 0;
				lastSkillName = r.getSkill().getName();
			}
			sum += r.getRanking();
			count++;
			results.put(lastSkillName, sum / count);
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	private Person findBestPersonFor(Session session, String skill) {
		Query query = session.createQuery("select r.subject.name, avg(r.ranking) "
				+ "from Ranking r "
				+ "where r.skill.name=:skill "
				+ "group by r.subject.name "
				+ "order by avg(r.ranking) desc");
		query.setParameter("skill", skill);
		List<Object[]> result = query.list();
		if (result.size() > 0) {
			return findPerson(session, (String) result.get(0)[0]);
		}
		
		return null;
	}
}
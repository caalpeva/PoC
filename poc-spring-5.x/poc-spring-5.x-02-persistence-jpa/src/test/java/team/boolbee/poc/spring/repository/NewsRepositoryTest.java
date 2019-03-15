package team.boolbee.poc.spring.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;

@SpringJUnitConfig(TestContext.class)
@ContextConfiguration("/spring-service.xml")
@Transactional
public class NewsRepositoryTest {

	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	// @Autowired
	// private ApplicationContext applicationContext;

	@Autowired
	private NewsRepository newsRepository;

	@Test
	public void saveAndUpdateNews() {
		// assertNotNull(applicationContext);
		// NewsRepository newsRepository = (NewsRepository) applicationContext.getBean("newsRepository", NewsRepository.class);
		
		long count = newsRepository.count();

		News news = new News();
		news.setTitle("Las primeras reacciones a 'Dumbo' son muy positivas");
		news.setDate(new Date());
		news.setDetail("La saga de pel�culas de acci�n real de Disney estrenar� su nueva aventura el pr�ximo 29 de marzo.");
		news.setStatus(Status.ACTIVE);
		System.out.println(news.toString());

		newsRepository.save(news);

		assertNotEquals(0, news.getId());
		System.out.println(news.toString());

		assertTrue(newsRepository.existsById(news.getId()));

		assertEquals(count + 1, newsRepository.count());
	
		Optional<News> optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		Date date = null;
		try {
			date = format.parse("2019-03-12");
		} catch (ParseException e) {
			fail(e);
		}

		news = optional.get();
		news.setDate(date);
		news.setStatus(Status.INACTIVE);
		newsRepository.save(news);

		optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		assertEquals(date, optional.get().getDate());
	}

	@Test
	public void checkNews() {
		populateData();
		List<News> newsList = (List<News>) newsRepository.findAll();
		assertEquals(newsRepository.count(), newsList.size());

		for (News news: newsList) {
			System.out.println(news);
		}
		
		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(2);
		ids.add(4);
		newsList = (List<News>) newsRepository.findAllById(ids);
		assertEquals(ids.size(), newsList.size());
	}

	@Test()
	//@Transactional
	public void deleteNews() {
		News news = new News();
		news.setTitle("Las primeras reacciones a 'Dumbo' son muy positivas");
		news.setDate(new Date());
		news.setDetail("La saga de pel�culas de acci�n real de Disney estrenar� su nueva aventura el pr�ximo 29 de marzo.");
		news.setStatus(Status.ACTIVE);
		System.out.println(news.toString());

		newsRepository.save(news);
		
		long count = newsRepository.count();
		
		Optional<News> optional = newsRepository.findById(news.getId());
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}
		
		newsRepository.delete(optional.get());
		//newsRepository.deleteById(optional.get().getId());
		assertEquals(--count, newsRepository.count());
		
		//newsRepository.deleteAll();
		newsRepository.deleteAllInBatch(); // M�todo de borrado m�s eficiente
		assertEquals(0, newsRepository.count());
	}
	
	private void populateData() {
		createNews("'Aladdin': Will Smith homenajea al Genio de Robin Williams",
				"Disney parece estar decidido a reinventar todos y cada uno de los cl�sicos animados "
				+ "que dieron al estudio la popularidad que tiene hoy en d�a.",
				new Date());
		createNews("La pel�cula de 'Dragones y Mazmorras' est� en busca de su protagonista",
				"La nueva pel�cula de Dragones y mazmorras es uno de los proyectos m�s interesantes de los pr�ximos a�os.",
				new Date());
		createNews("Oscar Isaac confirma que 'Star Wars: Episodio 9' ser� el final a las 9 pel�culas de los Skywalker",
				"Star Wars: Episodio IX es una de las pel�culas m�s esperadas de 2019.",
				new Date());
	}

	private void createNews(String title, String detail, Date date) {
		News news = new News();
		news.setTitle(title);
		news.setDate(date);
		news.setDetail(detail);

		newsRepository.save(news);
	}
}
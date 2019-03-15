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

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.model.MovieDetail;
import team.boolbee.poc.spring.model.Status;

@SpringJUnitConfig(TestContext.class)
@ContextConfiguration("/spring-datasource-hibernate.xml")
@TestMethodOrder(OrderAnnotation.class) // @Order(1) //@Order(2)
public class MovieRepositoryTest {
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	//private MovieDetailRepository movieDetailRepository;

	@Test
	@Order(1)
	public void createMovie() {
		long count = movieRepository.count();

		MovieDetail movieDetail = new MovieDetail();
		movieDetail.setActors("Jean Reno, Hovik Keuchkerian, Susana Abaitua, Enrique San Francisco, Arturo Valls.");
		movieDetail.setDirector("Gerardo Olivares");
		movieDetail.setSynopsis("Synopsis");
		movieDetail.setTrailer("https://www.youtube.com/embed/KBstfRGTssM");
		
		Movie movie = new Movie();
		movie.setTitle("4 Latas");
		movie.setClassification("A");
		movie.setDuration(104);
		movie.setType(FilmType.ADVENTURE);
		movie.setFilename("4-latas.jpg");
		movie.setReleaseDate(new Date());
		movie.setDetail(movieDetail);
		movie.setStatus(Status.INACTIVE);
		//movieDetailRepository.save(movieDetail);
		System.out.println(movie.toString());
		
		movieRepository.save(movie);

		assertNotEquals(0, movie.getId());
		System.out.println(movie.toString());

		assertTrue(movieRepository.existsById(movie.getId()));

		assertEquals(count + 1, movieRepository.count());
	}

	@Test
	@Order(2)
	// @DirtiesContext
	public void updateMovies() {
		Optional<Movie> optional = movieRepository.findById(1);
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		Date date = null;
		try {
			date = format.parse("2019-03-01");
		} catch (ParseException e) {
			fail(e);
		}

		Movie movie = optional.get();
		movie.setReleaseDate(date);
		movie.setStatus(Status.ACTIVE);
		movie.getDetail().setActors("OTROS");
		movieRepository.save(movie);

		optional = movieRepository.findById(1);
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		assertEquals(date, optional.get().getReleaseDate());
	}

	@Test
	@Order(3)
	public void checkMovies() {
		populateData();
		List<Movie> newsList = (List<Movie>) movieRepository.findAll();
		assertEquals(movieRepository.count(), newsList.size());

		for (Movie news : newsList) {
			System.out.println(news);
		}

		List<Integer> ids = new LinkedList<Integer>();
		ids.add(1);
		ids.add(2);
		newsList = (List<Movie>) movieRepository.findAllById(ids);
		assertEquals(ids.size(), newsList.size());
	}

	@Test()
	@Order(4)
//	@DirtiesContext
	public void deleteMovies() {
		long count = movieRepository.count();

		Optional<Movie> optional = movieRepository.findById(2);
		if (!optional.isPresent()) {
			fail("Debe exitir un elemento");
		}

		movieRepository.delete(optional.get());
		// newsRepository.deleteById(optional.get().getId());
		assertEquals(--count, movieRepository.count());

		// newsRepository.deleteAll();
		movieRepository.deleteAllInBatch(); // M�todo de borrado m�s eficiente
		assertEquals(0, movieRepository.count());
	}

	private void populateData() {
		MovieDetail movieDetail = new MovieDetail();
		movieDetail.setActors("Clint Eastwood, Bradley Cooper, Dianne Wiest, Michael Pe�a, Taissa Farmiga.");
		movieDetail.setDirector("Clint Eastwood");
		movieDetail.setSynopsis("Synopsis");
		movieDetail.setTrailer("https://www.youtube.com/embed/7x_m3SsDWnI");
		
		Movie movie = new Movie();
		movie.setTitle("Mula");
		movie.setClassification("B");
		movie.setDuration(116);
		movie.setType(FilmType.DRAMA);
		movie.setFilename("mula.jpg");
		try {
			movie.setReleaseDate(format.parse("2019-03-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		movie.setDetail(movieDetail);
		movie.setStatus(Status.ACTIVE);
		System.out.println(movie.toString());

		//movieDetailRepository.save(movieDetail);
		movieRepository.save(movie);
		
		movieDetail = new MovieDetail();
		movieDetail.setActors("Michael B. Jordan, Sylvester Stallone, Tessa Thompson, Dolph Lundgren.");
		movieDetail.setDirector("Steven Caple Jr.");
		movieDetail.setSynopsis("Synopsis");
		movieDetail.setTrailer("https://www.youtube.com/embed/QmMqMR2bbGg");
		
		movie = new Movie();
		movie.setTitle("Creed II");
		movie.setClassification("C");
		movie.setDuration(130);
		movie.setType(FilmType.DRAMA);
		movie.setFilename("creed2.jpg");
		try {
			movie.setReleaseDate(format.parse("2018-11-21"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		movie.setDetail(movieDetail);
		movie.setStatus(Status.INACTIVE);
		System.out.println(movie.toString());

		//movieDetailRepository.save(movieDetail);
		movieRepository.save(movie);
	}
}

package team.boolbee.poc.spring.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import team.boolbee.poc.spring.model.FilmType;
import team.boolbee.poc.spring.model.Movie;
import team.boolbee.poc.spring.model.Status;
import team.boolbee.poc.spring.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}
	
	@Override
	public List<Movie> findAllActives() {
		return movieRepository.findByStatusOrderByTitle(Status.ACTIVE);
	}

	@Override
	public List<Movie> findAllByShowtimeDate(Date date) {
		List<Integer> ids = movieRepository.findMovieIdsByShowtimeDate(date);
		return movieRepository.findAllById(ids);
	}

	@Override
	public Movie findById(int movieId) {
		Optional<Movie> optional = movieRepository.findById(movieId);
		return optional.isPresent()
				? optional.get()
				: null;
	}

	@Override
	public void save(Movie movie) {
		movieRepository.save(movie);
	}

	@Override
	public List<FilmType> getMovieTypes() {
		return Arrays.asList(FilmType.values());
	}

	@Override
	public void delete(int movieId) {
		movieRepository.deleteById(movieId);
	}
}
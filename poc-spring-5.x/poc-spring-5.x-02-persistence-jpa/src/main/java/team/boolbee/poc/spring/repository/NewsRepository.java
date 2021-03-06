package team.boolbee.poc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.boolbee.poc.spring.model.News;
import team.boolbee.poc.spring.model.Status;

@Repository
//public interface NewsRepository extends CrudRepository<News, Integer> {
//public interface NewsRepository extends PagingAndSortingRepository<News, Integer> {
public interface NewsRepository extends JpaRepository<News, Integer> {
	public List<News> findTop10ByStatusOrderByDateDesc(Status status);
}
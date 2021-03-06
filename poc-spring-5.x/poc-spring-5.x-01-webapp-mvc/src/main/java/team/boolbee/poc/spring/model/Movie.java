package team.boolbee.poc.spring.model;

import java.util.Date;

public class Movie {
	private int id;
	private String title;
	private int duration = 100;
	private String classification = "B";
	private FilmType type;
	private String filename = "cinema.png"; // imagen por default	
	private Date releaseDate;	
	private Status status= Status.ACTIVE;
	private MovieDetail detail;
	
	public Movie() {
		detail = new MovieDetail();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public FilmType getType() {
		return type;
	}
	public void setType(FilmType type) {
		this.type = type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public MovieDetail getDetail() {
		return detail;
	}
	public void setDetail(MovieDetail detail) {
		this.detail = detail;
	}
	
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", duration=" + duration + ", classification=" + classification
				+ ", type=" + type + ", filename=" + filename + ", releaseDate=" + releaseDate + ", status=" + status
				+ ", detail=" + detail + "]";
	}
}
package team.boolbee.poc.hibernate.onetoone.unidirectional.xml.model;

/**
 * Represents a service or program
 * @author Eugenia Pérez Martínez
 * @email eugenia_perez@cuatrovientos.org
 */
public class Service {
	
	private Long id;
	private String name;
	private String path;

	/**
	 * default constructor
	 */
	public Service () {
	}

	/**
	 * @param id
	 * @param name
	 * @param path
	 * @param port
	 */
	public Service(Long id, String name, String path) {
		this.id = id;
		this.name = name;
		this.path = path;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Service [id=" + id + ", name=" + name + ", path=" + path + "]";
	}
}

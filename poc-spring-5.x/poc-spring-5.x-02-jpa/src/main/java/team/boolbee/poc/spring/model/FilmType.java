package team.boolbee.poc.spring.model;

public enum FilmType {
	ACTION ("Acci�n"),
	ADVENTURE ("Aventuras"),
	ANIMATION ("Animaci�n"),
	CARTOON ("Dibujos"),
	COMEDY ("Comedia"),
	FANTASY ("Fantas�a"),
	DRAMA ("Drama"),
	HORROR ("Terror"),
	MARTIAL_ARTS ("Artes Marciales"),
	MUSICAL ("Musical"),
	ROMANCE ("Romance"),
	SCIENCE_FICTION ("Ciencia Ficci�n"),
	SILENT ("Mudo"),
	THRILLER ("Suspense"),
	WAR ("Guerra"),
	WESTERN ("Oeste");
	
	private String label;
	
	private FilmType(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return name();
	}
}
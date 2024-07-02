package ar.com.cac.db;

public class Actor {
	
	private int idActor;
	private String nombreActor;
	private String generoActor;
	private int edadActor;
	private int nominacionesOscar;
	
	public Actor() {}
	
	public Actor(int idActor, String nombreActor, String generoActor, int edadActor, int nominacionesOscar) {
	
		this.idActor = idActor;
		this.nombreActor = nombreActor;
		this.generoActor = generoActor;
		this.edadActor = edadActor;
		this.nominacionesOscar = nominacionesOscar;
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}

	public String getNombreActor() {
		return nombreActor;
	}

	public void setNombreActor(String nombreActor) {
		this.nombreActor = nombreActor;
	}

	public String getGeneroActor() {
		return generoActor;
	}

	public void setGeneroActor(String generoActor) {
		this.generoActor = generoActor;
	}

	public int getEdadActor() {
		return edadActor;
	}

	public void setEdadActor(int edadActor) {
		this.edadActor = edadActor;
	}

	public int getNominacionesOscar() {
		return nominacionesOscar;
	}

	public void setNominacionesOscar(int nominacionesOscar) {
		this.nominacionesOscar = nominacionesOscar;
	}
	
	
}

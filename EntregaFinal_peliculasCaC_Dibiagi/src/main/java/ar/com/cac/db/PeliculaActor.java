package ar.com.cac.db;

public class PeliculaActor {

	private int idPeliculaActor;
	private int idPelicula;
	private int idActor;
	
	public PeliculaActor() {}
	
	public PeliculaActor(int idPeliculaActor, int idPelicula, int idActor) {
		
		this.idPeliculaActor = idPeliculaActor;
		this.idPelicula = idPelicula;
		this.idActor = idActor;
	}

	public int getIdPeliculaActor() {
		return idPeliculaActor;
	}

	public void setIdPeliculaActor(int idPeliculaActor) {
		this.idPeliculaActor = idPeliculaActor;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdActor() {
		return idActor;
	}

	public void setIdActor(int idActor) {
		this.idActor = idActor;
	}
	
	
	
}

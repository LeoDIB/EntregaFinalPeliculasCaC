package ar.com.cac.db;

public class PeliculaDirector {

	private int idPeliculaDirector;
	private int idPelicula;
	private int idDirector;
	
	public PeliculaDirector() {}
	
	public PeliculaDirector(int idPeliculaDirector, int idPelicula, int idDirector) {
		
		this.idPeliculaDirector = idPeliculaDirector;
		this.idPelicula = idPelicula;
		this.idDirector = idDirector;
	}

	public int getIdPeliculaDirector() {
		return idPeliculaDirector;
	}

	public void setIdPeliculaDirector(int idPeliculaDirector) {
		this.idPeliculaDirector = idPeliculaDirector;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}
	
	
}

package ar.com.cac.db;

public class Pelicula {

	private int idPelicula;
	private String tituloPelicula;
	private String duracionPelicula;
	private String sinopsisPelicula;
	private String imagenPelicula;
	
	public Pelicula() {}
	
	public Pelicula(int idPelicula, String tituloPelicula, String duracionPelicula, String sinopsisPelicula, String imagenPelicula) {
	
		this.idPelicula = idPelicula;
		this.tituloPelicula = tituloPelicula;
		this.duracionPelicula = duracionPelicula;
		this.sinopsisPelicula = sinopsisPelicula;
		this.imagenPelicula = imagenPelicula;
	}

	public String getImagenPelicula() {
		return imagenPelicula;
	}

	public void setImagenPelicula(String imagenPelicula) {
		this.imagenPelicula = imagenPelicula;
	}

	public int getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(int idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	public String getDuracionPelicula() {
		return duracionPelicula;
	}

	public void setDuracionPelicula(String duracionPelicula) {
		this.duracionPelicula = duracionPelicula;
	}

	public String getSinopsisPelicula() {
		return sinopsisPelicula;
	}

	public void setSinopsisPelicula(String sinopsisPelicula) {
		this.sinopsisPelicula = sinopsisPelicula;
	}
		
	
}

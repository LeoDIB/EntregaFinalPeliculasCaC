package ar.com.cac.db;

public class Director {

	private int idDirector;
	private String nombreDirector;
	private String generoDirector;
	private int edadDirector;
	private int nominacionesOscar;
	
	public Director() {}
	
	public Director(int idDirector, String nombreDirector, String generoDirector, int edadDirector,
			int nominacionesOscar) {
		
		this.idDirector = idDirector;
		this.nombreDirector = nombreDirector;
		this.generoDirector = generoDirector;
		this.edadDirector = edadDirector;
		this.nominacionesOscar = nominacionesOscar;
	}

	public int getIdDirector() {
		return idDirector;
	}

	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}

	public String getNombreDirector() {
		return nombreDirector;
	}

	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}

	public String getGeneroDirector() {
		return generoDirector;
	}

	public void setGeneroDirector(String generoDirector) {
		this.generoDirector = generoDirector;
	}

	public int getEdadDirector() {
		return edadDirector;
	}

	public void setEdadDirector(int edadDirector) {
		this.edadDirector = edadDirector;
	}

	public int getNominacionesOscar() {
		return nominacionesOscar;
	}

	public void setNominacionesOscar(int nominacionesOscar) {
		this.nominacionesOscar = nominacionesOscar;
	}
	
	
	
}

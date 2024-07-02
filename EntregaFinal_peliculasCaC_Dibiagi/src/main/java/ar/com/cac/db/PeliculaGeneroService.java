package ar.com.cac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaGeneroService {

private Conexion conexion;
	
	public PeliculaGeneroService() {
		
		this.conexion = new Conexion();
		
	}
	/*Método para obtener los Generos de una pelicula en particular mediante el idPelicula*/
public List<Genero> getGenerosByIdPelicula(int idPelicula) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "SELECT g.* FROM genero g JOIN peliculaGenero pg ON g.idGenero = pg.idGenero WHERE pg.idPelicula = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPelicula);
		
		ResultSet rs = ps.executeQuery();
		
		List<Genero> generos = new ArrayList<>();
		
		while(rs.next()) 
		{
			
			Genero genero = new Genero(
					
					rs.getInt("idGenero"),
					rs.getString("nombreGenero")
					);
			generos.add(genero);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return generos;
	}

/*Método para obtener las peliculas de un genero en particular, mediante su idGenero*/

public List<Pelicula> getPeliculasByIdGenero(int idGenero) throws SQLException,ClassNotFoundException {
	
	Connection con = conexion.getConnection();
	
	String sql = "SELECT p.* FROM peliculas p JOIN peliculaGenero pg ON p.idPelicula = pg.idPelicula WHERE pg.idGenero = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, idGenero);
	
	ResultSet rs = ps.executeQuery();
	
	List<Pelicula> peliculas = new ArrayList<>();
	
	while(rs.next()) 
	{
		
		 Pelicula pelicula = new Pelicula(
				
				rs.getInt("idPelicula"),
				rs.getString("tituloPelicula"),
				rs.getString("duracionPelicula"),
				rs.getString("sinopsisPelicula"),
				rs.getString("imagenPelicula")
				);
		 peliculas.add(pelicula);
	}
	
		rs.close();
		ps.close();
		con.close();
	 return peliculas;
}

public void addPeliculaGenero (PeliculaGenero peliculaGenero) throws SQLException,ClassNotFoundException  {
	
	String sql = "insert into peliculaGenero (idPelicula,idGenero)"
			+ "VALUES(?,?)";
	
	Connection con = conexion.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, peliculaGenero.getIdPelicula());
	ps.setInt(2, peliculaGenero.getIdGenero());
	
	ps.executeUpdate();
	ps.close();
	con.close();
}

	
}

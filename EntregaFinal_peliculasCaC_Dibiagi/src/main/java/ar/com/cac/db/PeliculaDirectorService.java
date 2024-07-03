package ar.com.cac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDirectorService {
private Conexion conexion;
	
	public PeliculaDirectorService() {
		
		this.conexion = new Conexion();
		
	}
	
	// Método para obtener todos los elementos de la tabla peliculaDirector
public List<PeliculaDirector> getAllPeliculaDirector() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from peliculaDirector";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<PeliculaDirector> peliculaDirectores = new ArrayList<>();
		
		while(rs.next()) 
		{
			
			PeliculaDirector peliculaDirector = new PeliculaDirector(
					rs.getInt("idPeliculaDirector"),
					rs.getInt("idPelicula"),
					rs.getInt("idDirector")					);
			
			peliculaDirectores.add(peliculaDirector);
		}
		
		rs.close();
		ps.close();
		con.close();
		return peliculaDirectores;
	}
	
	/*Método para obtener los directores de una pelicula en particular mediante el idPelicula*/
public List<Director> getDirectoresByIdPelicula(int idPelicula) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "SELECT d.* FROM director d JOIN peliculaDirector pd ON d.idDirector = pd.idDirector WHERE pd.idPelicula = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPelicula);
		
		ResultSet rs = ps.executeQuery();
		
		List<Director> directores = new ArrayList<>();
		
		while(rs.next()) 
		{
			
			Director director = new Director(
					
					rs.getInt("idDirector"),
					rs.getString("nombreDirector"),
					rs.getString("generoDirector"),
					rs.getInt("edadDirector"),
					rs.getInt("nominacionesOscar")
					);
			directores.add(director);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return directores;
	}

/*Método para obtener las peliculas donde trabaja un director en particular, mediante su idDirector*/

public List<Pelicula> getPeliculasByIdDirector(int idDirector) throws SQLException,ClassNotFoundException {
	
	Connection con = conexion.getConnection();
	
	String sql = "SELECT p.* FROM peliculas p JOIN peliculaDirector pd ON p.idPelicula = pd.idPelicula WHERE pd.idDirector = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, idDirector);
	
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

public void addPeliculaDirector (PeliculaDirector peliculaDirector) throws SQLException,ClassNotFoundException  {
	
	String sql = "insert into peliculaDirector (idPelicula,idDirector)"
			+ "VALUES(?,?)";
	
	Connection con = conexion.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, peliculaDirector.getIdPelicula());
	ps.setInt(2, peliculaDirector.getIdDirector());
	
	ps.executeUpdate();
	ps.close();
	con.close();
}

public void updatePeliculaDirector (PeliculaDirector peliculaDirector) throws SQLException,ClassNotFoundException  {
	
	String sql = "UPDATE peliculaDirector SET idPelicula = ?, idDirector = ? "
			+ "WHERE idPeliculaDirector = ?";
	
	Connection con = conexion.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, peliculaDirector.getIdPelicula());
	ps.setInt(2, peliculaDirector.getIdDirector());
	ps.setInt(3, peliculaDirector.getIdPeliculaDirector());

	ps.executeUpdate();
	ps.close();
	con.close();
}

public void deletePeliculaDirector(int idPeliculaDirector) throws SQLException, ClassNotFoundException{
	
	Connection con = conexion.getConnection();
	
	String sql = "delete * from peliculaDirector where idPeliculaDirector=?";
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, idPeliculaDirector);
	
	ps.executeUpdate();
	ps.close();
	con.close();
}
	


}

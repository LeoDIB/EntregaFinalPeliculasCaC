package ar.com.cac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaActorService {
	
private Conexion conexion;
	
	public PeliculaActorService() {
		
		this.conexion = new Conexion();
		
	}
	
public List<PeliculaActor> getAllPeliculaActor() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from peliculaReparto";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		List<PeliculaActor> peliculaActores = new ArrayList<>();
		
		while(rs.next()) 
		{
			
			PeliculaActor peliculaActor = new PeliculaActor(
					rs.getInt("idPeliculaReparto"),
					rs.getInt("idPelicula"),
					rs.getInt("idActor")					);
			
			peliculaActores.add(peliculaActor);
		}
		
		rs.close();
		ps.close();
		con.close();
		return peliculaActores;
	}
	
	
	/*Método para obtener el reparto de una pelicula en particular mediante el idPelicula*/
public List<Actor> getRepartoByIdPelicula(int idPelicula) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "SELECT a.* FROM actor a JOIN peliculaReparto pr ON a.idActor = pr.idActor WHERE pr.idPelicula = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, idPelicula);
		
		ResultSet rs = ps.executeQuery();
		
		List<Actor> actores = new ArrayList<>();
		
		while(rs.next()) 
		{
			
			Actor actor = new Actor(
					
					rs.getInt("idActor"),
					rs.getString("nombreActor"),
					rs.getString("generoActor"),
					rs.getInt("edadActor"),
					rs.getInt("nominacionesOscar")
					);
			actores.add(actor);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return actores;
	}

/*Método para obtener las peliculas donde trabaja un actor en particular, mediante su idActor*/

public List<Pelicula> getPeliculasByIdActor(int idActor) throws SQLException,ClassNotFoundException {
	
	Connection con = conexion.getConnection();
	
	String sql = "SELECT p.* FROM peliculas p JOIN peliculaReparto pr ON p.idPelicula = pr.idPelicula WHERE pr.idActor = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, idActor);
	
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

public void addPeliculaActor (PeliculaActor peliculaActor) throws SQLException,ClassNotFoundException  {
	
	String sql = "insert into peliculaReparto (idPelicula,idActor)"
			+ "VALUES(?,?)";
	
	Connection con = conexion.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, peliculaActor.getIdPelicula());
	ps.setInt(2, peliculaActor.getIdActor());
	
	ps.executeUpdate();
	ps.close();
	con.close();
}

public void updatePeliculaReparto (PeliculaActor peliculaActor) throws SQLException,ClassNotFoundException  {
	
	String sql = "UPDATE peliculaReparto SET idPelicula = ?, idActor = ? "
			+ "WHERE idPeliculaReparto = ?";
	Connection con = conexion.getConnection();
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, peliculaActor.getIdPelicula());
	ps.setInt(2, peliculaActor.getIdActor());
	ps.setInt(3, peliculaActor.getIdPeliculaActor());

	ps.executeUpdate();
	ps.close();
	con.close();
}

public void deletePeliculaReparto(int idPeliculaActor) throws SQLException, ClassNotFoundException{
	
	Connection con = conexion.getConnection();
	
	String sql = "delete * from peliculaReparto where idPeliculaReparto=?";
	
	PreparedStatement ps = con.prepareStatement(sql);
	
	ps.setInt(1, idPeliculaActor);
	
	ps.executeUpdate();
	ps.close();
	con.close();
}
	


}



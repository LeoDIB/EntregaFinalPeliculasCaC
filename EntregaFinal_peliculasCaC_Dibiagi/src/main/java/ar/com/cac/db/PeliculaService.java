package ar.com.cac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaService {

	private Conexion conexion;
	
	public PeliculaService() {
		
		this.conexion = new Conexion();
		
	}
	
	public List<Pelicula> getAllPeliculas() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from peliculas";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	
	public Pelicula getPeliculaById(int id) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "select * from peliculas where idPelicula=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Pelicula pelicula = null;
		
		while(rs.next()) 
		{
			
			 pelicula = new Pelicula(
					
					rs.getInt("idPelicula"),
					rs.getString("tituloPelicula"),
					rs.getString("duracionPelicula"),
					rs.getString("sinopsisPelicula"),
					rs.getString("imagenPelicula")
					);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return pelicula;
	}
	
	public void addPelicula (Pelicula pelicula) throws SQLException,ClassNotFoundException  {
		
		String sql = "insert into peliculas (tituloPelicula,duracionPelicula,sinopsisPelicula,imagenPelicula)"
				+ "VALUES(?,?,?,?)";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, pelicula.getTituloPelicula());
		ps.setString(2, pelicula.getDuracionPelicula());
		ps.setString(3, pelicula.getSinopsisPelicula());
		ps.setString(4, pelicula.getImagenPelicula());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	public void updatePelicula (Pelicula pelicula) throws SQLException,ClassNotFoundException  {
		
		String sql = "UPDATE peliculas SET tituloPelicula = ?, duracionPelicula = ?, sinopsisPelicula = ?, imagenPelicula = ? "
				+ "WHERE idPelicula = ?";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, pelicula.getTituloPelicula());
		ps.setString(2, pelicula.getDuracionPelicula());
		ps.setString(3, pelicula.getSinopsisPelicula());
		ps.setString(4, pelicula.getImagenPelicula());
		ps.setInt(5, pelicula.getIdPelicula());
		
		ps.executeUpdate();
		ps.close();
		con.close();
}
	
	public void deletePelicula(int id) throws SQLException, ClassNotFoundException{
		
		Connection con = conexion.getConnection();
		
		String sql = "DELETE FROM peliculas WHERE idPelicula=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	
}
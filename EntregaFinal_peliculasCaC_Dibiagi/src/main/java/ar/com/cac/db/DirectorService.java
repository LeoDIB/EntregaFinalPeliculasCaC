package ar.com.cac.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorService {

	private Conexion conexion;
	
	public DirectorService() {
		
		this.conexion = new Conexion();
		
	}
	
	public List<Director> getAllDirectores() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from director";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	
	public Director getDirectorById(int id) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "select * from director where idDirector=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Director director = null;
		
		while(rs.next()) 
		{
			
			 director = new Director(
					
						rs.getInt("idDirector"),
						rs.getString("nombreDirector"),
						rs.getString("generoDirector"),
						rs.getInt("edadDirector"),
						rs.getInt("nominacionesOscar")
						);
				
		}
		
			rs.close();
			ps.close();
			con.close();
		 return director;
	}
	
	public void addDirector (Director director) throws SQLException,ClassNotFoundException  {
		
		String sql = "insert into peliculas (nombreDirector,generoDirector,edadDirector,nominacionesOscar)"
				+ "VALUES(?,?,?,?)";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, director.getNombreDirector());
		ps.setString(2, director.getGeneroDirector());
		ps.setInt(3, director.getEdadDirector());
		ps.setInt(4, director.getNominacionesOscar());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	public void updateDirector (Director director) throws SQLException,ClassNotFoundException  {
		
		String sql = "update director set nombreDirector =?, generoDirector =?, edadDirector =?, nominacionesOscar =?"
				+ "where idActor =?";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, director.getNombreDirector());
		ps.setString(2, director.getGeneroDirector());
		ps.setInt(3, director.getEdadDirector());
		ps.setInt(4, director.getNominacionesOscar());
		ps.setInt(5, director.getIdDirector());
		
		ps.executeUpdate();
		ps.close();
		con.close();
}
	
	public void deleteDirector(int id) throws SQLException, ClassNotFoundException{
		
		Connection con = conexion.getConnection();
		
		String sql = "delete * from director where idDirector=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	
}
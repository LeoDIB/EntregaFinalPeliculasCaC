package ar.com.cac.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorService {
	private Conexion conexion;
	
	public ActorService() {
		
		this.conexion = new Conexion();
		
	}
	
	public List<Actor> getAllActores() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from actor";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	
	public Actor getActorById(int id) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "select * from actor where idActor=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Actor actor = null;
		
		while(rs.next()) 
		{
			
			 actor = new Actor(
					
						rs.getInt("idActor"),
						rs.getString("nombreActor"),
						rs.getString("generoActor"),
						rs.getInt("edadActor"),
						rs.getInt("nominacionesOscar")
		
						);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return actor;
	}
	
	public void addActor (Actor actor) throws SQLException,ClassNotFoundException  {
		
		String sql = "insert into actor (nombreActor,generoActor,edadActor,nominacionesOscar)"
				+ "VALUES(?,?,?,?)";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, actor.getNombreActor());
		ps.setString(2, actor.getGeneroActor());
		ps.setInt(3, actor.getEdadActor());
		ps.setInt(4, actor.getNominacionesOscar());
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	public void updateActor (Actor actor) throws SQLException,ClassNotFoundException  {
		
		String sql = "UPDATE actor SET nombreActor = ?, generoActor = ?, edadActor = ?, nominacionesOscar = ?"
				+ "WHERE idActor = ?";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, actor.getNombreActor());
		ps.setString(2, actor.getGeneroActor());
		ps.setInt(3, actor.getEdadActor());
		ps.setInt(4, actor.getNominacionesOscar());
		ps.setInt(5, actor.getIdActor());
		
		ps.executeUpdate();
		ps.close();
		con.close();
}
	
	public void deleteActor(int id) throws SQLException, ClassNotFoundException{
		
		Connection con = conexion.getConnection();
		
		String sql = "delete * from actor where idActor=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	
}

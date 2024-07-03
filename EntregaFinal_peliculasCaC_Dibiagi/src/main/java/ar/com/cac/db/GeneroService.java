package ar.com.cac.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneroService {

	private Conexion conexion;
	
	public GeneroService() {
		
		this.conexion = new Conexion();
		
	}
	
	public List<Genero> getAllGeneros() throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
						
		String sql = "select * from genero";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
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
	
	public Genero getGeneroById(int id) throws SQLException,ClassNotFoundException {
		
		Connection con = conexion.getConnection();
		
		String sql = "select * from genero where idGenero=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		Genero genero = null;
		
		while(rs.next()) 
		{
			
			 genero = new Genero(
					
					rs.getInt("idGenero"),
					rs.getString("nombreGenero")
		
					);
		}
		
			rs.close();
			ps.close();
			con.close();
		 return genero;
	}
	
	public void addGenero (Genero genero) throws SQLException,ClassNotFoundException  {
		
		String sql = "insert into genero (nombreGenero)"
				+ "VALUES(?)";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, genero.getNombreGenero());
	
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}

	public void updateGenero (Genero genero) throws SQLException,ClassNotFoundException  {
		
		String sql = "UPDATE genero SET nombreGenero = ? "
				+ "WHERE idGenero = ?";
		
		Connection con = conexion.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, genero.getNombreGenero());
		ps.setInt(2, genero.getIdGenero());
		
		ps.executeUpdate();
		ps.close();
		con.close();
}
	
	public void deleteGenero(int id) throws SQLException, ClassNotFoundException{
		
		Connection con = conexion.getConnection();
		
		String sql = "delete * from genero where idGenero=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ps.executeUpdate();
		ps.close();
		con.close();
	}
	
	
}
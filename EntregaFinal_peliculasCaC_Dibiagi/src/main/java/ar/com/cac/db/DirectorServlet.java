package ar.com.cac.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/director/*")
public class DirectorServlet extends HttpServlet{
	
	private DirectorService directorService;

	private ObjectMapper objectMapper;

		@Override 
		public void init() throws ServletException {
			
			directorService = new DirectorService();
			objectMapper = new ObjectMapper();
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
			
			String pathInfo = req.getPathInfo();
			try {
				
				if(pathInfo==null||pathInfo.equals("/")) 
				{
					List<Director> directores = directorService.getAllDirectores();
					String json = objectMapper.writeValueAsString(directores);
					resp.setContentType("application/json");
					resp.getWriter().write(json);
				} 
				else 
				{
					String[] pathParts = pathInfo.split("/");
					
					int id= Integer.parseInt(pathParts[1]);
					Director director = directorService.getDirectorById(id);
					
					if(director!=null) {
						
						String json = objectMapper.writeValueAsString(director);
						resp.setContentType("application/json");
						resp.getWriter().write(json);
						
					}else {
						resp.sendError(HttpServletResponse.SC_NOT_FOUND);
					}
				}
				
			}
			catch(SQLException|ClassNotFoundException e) {
				
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
		}
		
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
			
			try {
			Director director = objectMapper.readValue(req.getReader(), Director.class);
			directorService.addDirector(director);
			resp.setStatus(HttpServletResponse.SC_CREATED);
			}
			catch(SQLException|ClassNotFoundException e) {
				
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
}

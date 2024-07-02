package ar.com.cac.db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/actor/*")
public class ActorServlet extends HttpServlet{
	
	private ActorService actorService;

	private ObjectMapper objectMapper;

		@Override 
		public void init() throws ServletException {
			
			actorService = new ActorService();
			objectMapper = new ObjectMapper();
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
			
			String pathInfo = req.getPathInfo();
			try {
				
				if(pathInfo==null||pathInfo.equals("/")) 
				{
					List<Actor> actores = actorService.getAllActores();
					String json = objectMapper.writeValueAsString(actores);
					resp.setContentType("application/json");
					resp.getWriter().write(json);
				} 
				else 
				{
					String[] pathParts = pathInfo.split("/");
					
					int id= Integer.parseInt(pathParts[1]);
					Actor actor = actorService.getActorById(id);
					
					if(actor!=null) {
						
						String json = objectMapper.writeValueAsString(actor);
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
			Actor actor = objectMapper.readValue(req.getReader(), Actor.class);
			actorService.addActor(actor);
			resp.setStatus(HttpServletResponse.SC_CREATED);
			}
			catch(SQLException|ClassNotFoundException e) {
				
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
		
}

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


@WebServlet("/genero/*")

public class GeneroServlet extends HttpServlet {

private GeneroService generoService;

private ObjectMapper objectMapper;

	@Override 
	public void init() throws ServletException {
		
		generoService = new GeneroService();
		objectMapper = new ObjectMapper();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		
		String pathInfo = req.getPathInfo();
		try {
			
			if(pathInfo==null||pathInfo.equals("/")) 
			{
				List<Genero> genero = generoService.getAllGeneros();
				String json = objectMapper.writeValueAsString(genero);
				resp.setContentType("application/json");
				resp.getWriter().write(json);
			} 
			else 
			{
				String[] pathParts = pathInfo.split("/");
				
				int id= Integer.parseInt(pathParts[1]);
				Genero genero = generoService.getGeneroById(id);
				
				if(genero!=null) {
					
					String json = objectMapper.writeValueAsString(genero);
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
		Genero genero = objectMapper.readValue(req.getReader(), Genero.class);
		generoService.addGenero(genero);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		}
		catch(SQLException|ClassNotFoundException e) {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

protected void doPut(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
	
	try {
	Genero genero = objectMapper.readValue(req.getReader(), Genero.class);
	generoService.updateGenero(genero);
	resp.setStatus(HttpServletResponse.SC_OK);
	}
	catch(SQLException|ClassNotFoundException e) {
		
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
}

@Override
protected void doDelete(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{

String pathInfo = req.getPathInfo();
try {
		
	if(pathInfo!=null&&pathInfo.split("/").length>1) 
	{		
		int id= Integer.parseInt(pathInfo.split("/")[1]);
		generoService.deleteGenero(id);
		resp.setStatus(HttpServletResponse.SC_OK);
	}
	else 
	{
		resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}
} catch(SQLException|ClassNotFoundException e) {
	
	resp.sendError(HttpServletResponse.SC_NOT_FOUND);
}
}
	
		
}

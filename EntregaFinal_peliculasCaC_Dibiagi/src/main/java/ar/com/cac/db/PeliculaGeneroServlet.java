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

@WebServlet("/peliculaGenero/*")
public class PeliculaGeneroServlet extends HttpServlet {

    private PeliculaGeneroService peliculaGeneroService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        peliculaGeneroService = new PeliculaGeneroService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
            	List<PeliculaGenero> peliculas = peliculaGeneroService.getAllPeliculaGenero();
				String json = objectMapper.writeValueAsString(peliculas);
				resp.setContentType("application/json");
				resp.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 3 && pathParts[2].equals("genero")) {
                	
                    int idPelicula = Integer.parseInt(pathParts[1]);
                    
                    List<Genero> generos = peliculaGeneroService.getGenerosByIdPelicula(idPelicula);
                    
                    String json = objectMapper.writeValueAsString(generos);
                    
                    resp.setContentType("application/json");
                    
                    resp.getWriter().write(json);
                    
                } else if (pathParts.length == 3 && pathParts[2].equals("peliculas")) {
                	
                    int idGenero = Integer.parseInt(pathParts[1]);
                    
                    List<Pelicula> peliculas = peliculaGeneroService.getPeliculasByIdGenero(idGenero);
                    
                    if (peliculas != null) {
                    	
                        String json = objectMapper.writeValueAsString(peliculas);
                        resp.setContentType("application/json");
                        resp.getWriter().write(json);
                        
                    } else {
                        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                    
                } else {
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid path");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
    
protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		
		try {
		PeliculaGenero peliculaGenero = objectMapper.readValue(req.getReader(), PeliculaGenero.class);
		peliculaGeneroService.addPeliculaGenero(peliculaGenero);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		}
		catch(SQLException|ClassNotFoundException e) {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

@Override
protected void doPut(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
	
	try {
	PeliculaGenero peliculaGenero = objectMapper.readValue(req.getReader(), PeliculaGenero.class);
	peliculaGeneroService.updatePeliculaGenero(peliculaGenero);
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
		peliculaGeneroService.deletePeliculaGenero(id);
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
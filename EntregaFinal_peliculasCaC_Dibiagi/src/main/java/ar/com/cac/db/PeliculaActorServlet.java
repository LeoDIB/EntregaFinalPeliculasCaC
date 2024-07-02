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

@WebServlet("/peliculaActor/*")
public class PeliculaActorServlet extends HttpServlet {

    private PeliculaActorService peliculaActorService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        peliculaActorService = new PeliculaActorService();
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
            	List<PeliculaActor> peliculas = peliculaActorService.getAllPeliculaActor();
				String json = objectMapper.writeValueAsString(peliculas);
				resp.setContentType("application/json");
				resp.getWriter().write(json);
            } else {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length == 3 && pathParts[2].equals("reparto")) {
                	
                    int idPelicula = Integer.parseInt(pathParts[1]);
                    
                    List<Actor> actores = peliculaActorService.getRepartoByIdPelicula(idPelicula);
                    
                    String json = objectMapper.writeValueAsString(actores);
                    
                    resp.setContentType("application/json");
                    
                    resp.getWriter().write(json);
                    
                } else if (pathParts.length == 3 && pathParts[2].equals("peliculas")) {
                	
                    int idActor = Integer.parseInt(pathParts[1]);
                    
                    List<Pelicula> peliculas = peliculaActorService.getPeliculasByIdActor(idActor);
                    
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
		PeliculaActor peliculaActor = objectMapper.readValue(req.getReader(), PeliculaActor.class);
		peliculaActorService.addPeliculaActor(peliculaActor);
		resp.setStatus(HttpServletResponse.SC_CREATED);
		}
		catch(SQLException|ClassNotFoundException e) {
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}

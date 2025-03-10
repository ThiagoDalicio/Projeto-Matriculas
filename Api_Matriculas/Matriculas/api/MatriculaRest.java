package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Matricula;
import service.MatriculaService;
import java.util.List;

@Path("/matricula")
public class MatriculaRest {

    private MatriculaService service = new MatriculaService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarMatriculas() {
        try {
            List<Matricula> lista = service.listarTodas();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar matrículas: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarMatricula(Matricula matricula) {
        try {
            service.inserir(matricula.getNomeCompleto());
            return Response.status(Response.Status.CREATED).entity(matricula).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir matrícula: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            Matricula matricula = service.buscarPorId(id);
            if (matricula != null) {
                return Response.ok(matricula).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Matrícula não encontrada.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar matrícula: " + e.getMessage()).build();
        }
    }
}

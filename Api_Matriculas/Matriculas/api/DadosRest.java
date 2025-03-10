package api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Dados;
import service.DadosService;
import java.util.List;

@Path("/dados")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DadosRest {

    private final DadosService service = new DadosService();

    @GET
    public Response listarDados() {
        try {
            List<Dados> lista = service.listarTodos(); // ✅ Agora este método existe e está funcionando
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar os dados: " + e.getMessage()).build();
        }
    }

    @POST
    public Response inserirDados(Dados dados) {
        try {
            service.inserir(
                dados.getNome(), 
                dados.getEndereco(), 
                dados.getCep(), 
                dados.getIdade(), 
                dados.getGenero()
            );
            return Response.status(Response.Status.CREATED).entity(dados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao inserir dados: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") long id) {
        try {
            Dados dados = service.buscarPorId(id);
            if (dados != null) {
                return Response.ok(dados).build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Dados não encontrados.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar dados: " + e.getMessage()).build();
        }
    }
}



package br.com.fiap.resource;

import br.com.fiap.business.ExameBusiness;
import br.com.fiap.model.Exame;
import br.com.fiap.view.ExameView;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/exames")
public class ExameResource {

    private ExameBusiness service = new ExameBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarExames() {
        try {
            List<ExameView> lista = service.listarTodos();
            return Response.ok(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar exames: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorId(@PathParam("id") Long id) {
        try {
            ExameView ev = service.buscarPorId(id);
            if (ev == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(ev).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao buscar exame: " + e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarExame(Exame exame) {
        try {
            service.salvarExame(exame);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar exame: " + e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizarExame(Exame exame) {
        try {
            service.atualizarExame(exame);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar exame: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarExame(@PathParam("id") Long id) {
        try {
            service.deletarExame(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar exame: " + e.getMessage()).build();
        }
    }

    @OPTIONS
    public Response options() {
        return Response.noContent().build();
    }
}

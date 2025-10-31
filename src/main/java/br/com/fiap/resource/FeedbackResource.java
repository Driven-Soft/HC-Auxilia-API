package br.com.fiap.resource;

import br.com.fiap.business.FeedbackBusiness;
import br.com.fiap.model.Feedback;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/feedbacks")
public class FeedbackResource {

    private FeedbackBusiness service = new FeedbackBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarFeedback(Feedback feedback) {
        try {
            boolean ok = service.registrarFeedback(feedback);
            if (ok) {
                return Response.status(Response.Status.CREATED).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Dados do feedback inválidos").build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar feedback: " + e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarFeedbacks() {
        try {
            List<Feedback> lista = service.listarTodos();
            return Response.ok(lista).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar feedbacks: " + e.getMessage()).build();
        }
    }

    @OPTIONS
    public Response options() {
        // Allow CORS preflight to succeed. Quarkus' CORS config should handle headers,
        // this returns a simple 204/No Content so the browser preflight won't get a 405.
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarFeedback(@PathParam("id") Long id) {
        try {
            service.deletarFeedback(id);
            return Response.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao deletar feedback: " + e.getMessage()).build();
        }
    }
}

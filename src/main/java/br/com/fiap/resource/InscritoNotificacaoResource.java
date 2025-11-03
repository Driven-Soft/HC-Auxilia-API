package br.com.fiap.resource;

import br.com.fiap.business.InscritoNotificacaoBusiness;
import br.com.fiap.model.InscritoNotificacao;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/contato/notificacoes")
public class InscritoNotificacaoResource {

    private InscritoNotificacaoBusiness service = new InscritoNotificacaoBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response salvarInscrito(InscritoNotificacao inscrito) {
        try {
            service.salvarInscrito(inscrito);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao salvar inscrição: " + e.getMessage()).build();
        }
    }

}

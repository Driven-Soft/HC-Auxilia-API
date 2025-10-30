package br.com.fiap.business;

import br.com.fiap.model.InscritoNotificacao;
import br.com.fiap.repository.InscritoNotificacaoRepository;

import java.util.List;

public class InscritoNotificacaoBusiness {

    private InscritoNotificacaoRepository repository = new InscritoNotificacaoRepository();

    public void salvarInscrito(InscritoNotificacao inscrito) {
        repository.salvarInscrito(inscrito);
    }

    public List<InscritoNotificacao> listarTodos() {
        return repository.listarTodos();
    }

    public InscritoNotificacao buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarInscrito(InscritoNotificacao inscrito) {
        repository.atualizarInscrito(inscrito);
    }

    public void deletarInscrito(Long id) {
        if (id == null) return;
        repository.deletarInscrito(id);
    }
}

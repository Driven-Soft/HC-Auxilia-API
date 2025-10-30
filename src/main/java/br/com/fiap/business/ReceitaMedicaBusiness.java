package br.com.fiap.business;

import br.com.fiap.model.ReceitaMedica;
import br.com.fiap.repository.ReceitaMedicaRepository;

import java.util.List;

public class ReceitaMedicaBusiness {

    private ReceitaMedicaRepository repository = new ReceitaMedicaRepository();

    public void salvarReceita(ReceitaMedica receita) {
        repository.salvarReceita(receita);
    }

    public List<ReceitaMedica> listarTodos() {
        return repository.listarTodas();
    }

    public ReceitaMedica buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarReceita(ReceitaMedica receita) {
        repository.atualizarReceita(receita);
    }

    public void deletarReceita(Long id) {
        if (id == null) return;
        repository.deletarReceita(id);
    }
}

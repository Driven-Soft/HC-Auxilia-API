package br.com.fiap.business;

import br.com.fiap.model.Cuidador;
import br.com.fiap.repository.CuidadorRepository;

import java.util.List;

public class CuidadorBusiness {

    private CuidadorRepository repository = new CuidadorRepository();

    public void salvarCuidador(Cuidador cuidador) {
        repository.salvarCuidador(cuidador);
    }

    public List<Cuidador> listarTodos() {
        return repository.listarTodos();
    }

    public Cuidador buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarCuidador(Cuidador cuidador) {
        repository.atualizarCuidador(cuidador);
    }

    public void deletarCuidador(Long id) {
        if (id == null) return;
        repository.deletarCuidador(id);
    }
}

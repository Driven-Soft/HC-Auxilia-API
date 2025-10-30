package br.com.fiap.business;

import br.com.fiap.model.Especialidade;
import br.com.fiap.repository.EspecialidadeRepository;

import java.util.List;

public class EspecialidadeBusiness {

    private EspecialidadeRepository repository = new EspecialidadeRepository();

    public void salvarEspecialidade(Especialidade especialidade) {
        repository.salvarEspecialidade(especialidade);
    }

    public List<Especialidade> listarTodos() {
        return repository.listarTodos();
    }

    public Especialidade buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarEspecialidade(Especialidade especialidade) {
        repository.atualizarEspecialidade(especialidade);
    }

    public void deletarEspecialidade(Long id) {
        if (id == null) return;
        repository.deletarEspecialidade(id);
    }
}

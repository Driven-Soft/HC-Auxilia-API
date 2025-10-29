package br.com.fiap.business;

import br.com.fiap.model.Exame;
import br.com.fiap.view.ExameView;
import br.com.fiap.repository.ExameRepository;

import java.util.List;

public class ExameBusiness {

    private ExameRepository repository = new ExameRepository();

    public void salvarExame(Exame exame) {
        // validações mínimas podem ser adicionadas aqui
        repository.salvarExame(exame);
    }

    public List<ExameView> listarTodos() {
        return repository.listarTodos();
    }

    public ExameView buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarExame(Exame exame) {
        repository.atualizarExame(exame);
    }

    public void deletarExame(Long id) {
        if (id == null) return;
        repository.deletarExame(id);
    }
}

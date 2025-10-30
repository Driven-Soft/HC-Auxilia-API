package br.com.fiap.business;

import br.com.fiap.model.Medico;
import br.com.fiap.repository.MedicoRepository;

import java.util.List;

public class MedicoBusiness {

    private MedicoRepository repository = new MedicoRepository();

    public void salvarMedico(Medico medico) {
        repository.salvarMedico(medico);
    }

    public List<Medico> listarTodos() {
        return repository.listarTodos();
    }

    public Medico buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarMedico(Medico medico) {
        repository.atualizarMedico(medico);
    }

    public void deletarMedico(Long id) {
        if (id == null) return;
        repository.deletarMedico(id);
    }
}

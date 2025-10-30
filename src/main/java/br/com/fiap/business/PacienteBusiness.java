package br.com.fiap.business;

import br.com.fiap.model.Paciente;
import br.com.fiap.repository.PacienteRepository;

import java.util.List;

public class PacienteBusiness {

    private PacienteRepository repository = new PacienteRepository();

    public void salvarPaciente(Paciente paciente) {
        repository.salvarPaciente(paciente);
    }

    public List<Paciente> listarTodos() {
        return repository.listarTodos();
    }

    public Paciente buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarPaciente(Paciente paciente) {
        repository.atualizarPaciente(paciente);
    }

    public void deletarPaciente(Long id) {
        if (id == null) return;
        repository.deletarPaciente(id);
    }
}

package br.com.fiap.business;

import br.com.fiap.model.Consulta;
import br.com.fiap.repository.ConsultaRepository;

import java.util.List;

public class ConsultaBusiness {

    private ConsultaRepository repository = new ConsultaRepository();

    public void salvarConsulta(Consulta consulta) {
        repository.salvarConsulta(consulta);
    }

    public List<Consulta> listarTodas() {
        return repository.listarTodas();
    }

    public Consulta buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarConsulta(Consulta consulta) {
        repository.atualizarConsulta(consulta);
    }

    public void deletarConsulta(Long id) {
        if (id == null) return;
        repository.deletarConsulta(id);
    }
}

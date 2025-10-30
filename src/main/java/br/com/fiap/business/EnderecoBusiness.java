package br.com.fiap.business;

import br.com.fiap.model.Endereco;
import br.com.fiap.repository.EnderecoRepository;

import java.util.List;

public class EnderecoBusiness {

    private EnderecoRepository repository = new EnderecoRepository();

    public void salvarEndereco(Endereco endereco) {
        repository.salvarEndereco(endereco);
    }

    public List<Endereco> listarTodos() {
        return repository.listarTodos();
    }

    public Endereco buscarPorId(Long id) {
        if (id == null) return null;
        return repository.buscarPorId(id);
    }

    public void atualizarEndereco(Endereco endereco) {
        repository.atualizarEndereco(endereco);
    }

    public void deletarEndereco(Long id) {
        if (id == null) return;
        repository.deletarEndereco(id);
    }
}

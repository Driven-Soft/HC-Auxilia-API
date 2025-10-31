package br.com.fiap.business;

import br.com.fiap.model.Feedback;
import br.com.fiap.repository.FeedbackRepository;

import java.util.List;

public class FeedbackBusiness {

    private FeedbackRepository repository = new FeedbackRepository();

    // Validar nome (mínimo 3 caracteres)
    public boolean validarNome(String nome) {
        return nome != null && nome.trim().length() >= 3;
    }

    // Validar sugestão (mínimo 5 caracteres)
    public boolean validarSugestao(String sugestao) {
        return sugestao != null && sugestao.trim().length() >= 5;
    }

    // Validar nível de satisfação (1-10)
    public boolean validarNivelSatisfacao(int nivelSatisfacao) {
        return nivelSatisfacao >= 1 && nivelSatisfacao <= 10;
    }

    // Registrar feedback apenas se válido
    public boolean registrarFeedback(Feedback feedback) {
        if (validarNome(feedback.getNome()) &&
                validarSugestao(feedback.getSugestao()) &&
                validarNivelSatisfacao(feedback.getNivelSatisfacao())) {

            repository.salvarFeedback(feedback);
            return true;
        }

        System.out.println("Erro: Feedback inválido!");
        return false;
    }

    public List<Feedback> listarTodos() {
        return repository.listarTodos();
    }

    public void deletarFeedback(Long id) {
        if (id == null) return;
        repository.deletarFeedback(id);
    }
}

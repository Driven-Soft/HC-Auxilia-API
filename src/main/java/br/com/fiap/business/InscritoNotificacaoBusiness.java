package br.com.fiap.business;

import br.com.fiap.model.InscritoNotificacao;
import br.com.fiap.repository.InscritoNotificacaoRepository;

import java.util.List;

public class InscritoNotificacaoBusiness {

    private InscritoNotificacaoRepository repository = new InscritoNotificacaoRepository();

    public void salvarInscrito(InscritoNotificacao inscrito) {

        validar(inscrito);

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

        validar(inscrito);

        repository.atualizarInscrito(inscrito);
    }

    public void deletarInscrito(Long id) {
        if (id == null) return;
        repository.deletarInscrito(id);
    }

    private void validar(InscritoNotificacao inscrito) {

        if (inscrito == null) {
            throw new IllegalArgumentException("Inscrição inválida.");
        }

        // --- Validar nome ---
        if (inscrito.getNome() == null || inscrito.getNome().trim().length() < 3 ||
                !inscrito.getNome().matches("[A-Za-zÀ-ÿ ]+")) {
            throw new IllegalArgumentException("Nome inválido. Deve ter pelo menos 3 letras e não conter números ou caracteres especiais.");
        }

        // --- Validar telefone ---
        String telefone = inscrito.getTelefone();
        if (telefone == null || !telefone.matches("\\d+")) {
            throw new IllegalArgumentException("Telefone inválido. Deve conter apenas números.");
        }

        int minDigitos = 11; // ajuste se quiser outro mínimo
        if (telefone.length() < minDigitos) {
            throw new IllegalArgumentException("Telefone inválido. Deve ter no mínimo " + minDigitos + " dígitos.");
        }

        // --- Validar recebeSms e recebeWhatsapp ---
        String sms = inscrito.getRecebeSms() != null ? inscrito.getRecebeSms().toUpperCase() : "N";
        String whatsapp = inscrito.getRecebeWhatsapp() != null ? inscrito.getRecebeWhatsapp().toUpperCase() : "N";

        if ((!sms.equals("S") && !sms.equals("N")) ||
                (!whatsapp.equals("S") && !whatsapp.equals("N"))) {
            throw new IllegalArgumentException("Valores inválidos. recebeSms e recebeWhatsapp devem ser 'S' ou 'N'.");
        }

        // Pelo menos um dos dois precisa ser S
        if (sms.equals("N") && whatsapp.equals("N")) {
            throw new IllegalArgumentException("É necessário aceitar pelo menos um canal de notificação (SMS ou WhatsApp).");
        }

        // Atualiza o objeto normalizando "s" para "S"
        inscrito.setRecebeSms(sms);
        inscrito.setRecebeWhatsapp(whatsapp);
    }
}

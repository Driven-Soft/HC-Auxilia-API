package br.com.fiap.repository;

import br.com.fiap.model.InscritoNotificacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscritoNotificacaoRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    // --- Salvar ---
    public void salvarInscrito(InscritoNotificacao inscrito) {
        String sql = "INSERT INTO INSCRICOES_NOTIFICACOES (NOME, TELEFONE, RECEBE_SMS, RECEBE_WHATSAPP) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"INSCRICAO_ID"})) {

            ps.setString(1, inscrito.getNome());
            ps.setString(2, inscrito.getTelefone());
            ps.setString(3, inscrito.getRecebeSms());
            ps.setString(4, inscrito.getRecebeWhatsapp());

            ps.executeUpdate();

            // Recupera o ID gerado automaticamente
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    inscrito.setIdInscritoNotificacao(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar inscrito", e);
        }
    }

    // --- Listar todos ---
    public List<InscritoNotificacao> listarTodos() {
        List<InscritoNotificacao> lista = new ArrayList<>();

        String sql = "SELECT INSCRICAO_ID, NOME, TELEFONE, RECEBE_SMS, RECEBE_WHATSAPP, DATA_INSCRICAO, STATUS " +
                "FROM INSCRICOES_NOTIFICACOES";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                InscritoNotificacao i = new InscritoNotificacao();
                i.setIdInscritoNotificacao(rs.getLong("inscricao_id"));
                i.setNome(rs.getString("nome"));
                i.setTelefone(rs.getString("telefone"));
                i.setRecebeSms(rs.getString("recebe_sms"));
                i.setRecebeWhatsapp(rs.getString("recebe_whatsapp"));

                Date data = rs.getDate("data_inscricao");
                if (data != null) {
                    i.setDataInscricao(data.toLocalDate());
                }

                i.setStatus(rs.getString("status"));

                lista.add(i);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar inscritos", e);
        }

        return lista;
    }

    // --- Buscar por ID ---
    public InscritoNotificacao buscarPorId(Long id) {
        String sql = "SELECT INSCRICAO_ID, NOME, TELEFONE, RECEBE_SMS, RECEBE_WHATSAPP, DATA_INSCRICAO, STATUS " +
                "FROM INSCRICOES_NOTIFICACOES WHERE INSCRICAO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    InscritoNotificacao i = new InscritoNotificacao();
                    i.setIdInscritoNotificacao(rs.getLong("inscricao_id"));
                    i.setNome(rs.getString("nome"));
                    i.setTelefone(rs.getString("telefone"));
                    i.setRecebeSms(rs.getString("recebe_sms"));
                    i.setRecebeWhatsapp(rs.getString("recebe_whatsapp"));

                    Date data = rs.getDate("data_inscricao");
                    if (data != null) {
                        i.setDataInscricao(data.toLocalDate());
                    }

                    i.setStatus(rs.getString("status"));
                    return i;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar inscrito por ID", e);
        }

        return null;
    }

    // --- Atualizar ---
    public void atualizarInscrito(InscritoNotificacao inscrito) {
        String sql = "UPDATE INSCRICOES_NOTIFICACOES SET NOME = ?, TELEFONE = ?, RECEBE_SMS = ?, RECEBE_WHATSAPP = ?, STATUS = ? " +
                "WHERE INSCRICAO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, inscrito.getNome());
            ps.setString(2, inscrito.getTelefone());
            ps.setString(3, inscrito.getRecebeSms());
            ps.setString(4, inscrito.getRecebeWhatsapp());
            ps.setString(5, inscrito.getStatus());
            ps.setLong(6, inscrito.getIdInscritoNotificacao());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum inscrito encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar inscrito", e);
        }
    }

    // --- Deletar ---
    public void deletarInscrito(Long id) {
        String sql = "DELETE FROM INSCRICOES_NOTIFICACOES WHERE INSCRICAO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum inscrito encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar inscrito", e);
        }
    }
}

package br.com.fiap.repository;

import br.com.fiap.model.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    // Create
    public Long salvarFeedback(Feedback feedback) {
        String sql = "INSERT INTO FEEDBACK (NOME, SUGESTAO, NIVEL_SATISFACAO) VALUES (?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, feedback.getNome());
            ps.setString(2, feedback.getSugestao());
            ps.setInt(3, feedback.getNivelSatisfacao());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Read
    public List<Feedback> listarTodos() {
        List<Feedback> lista = new ArrayList<>();
        String sql = "SELECT FEEDBACK_ID, NOME, EMAIL, NIVEL_SATISFACAO, SUGESTAO, DATA_ENVIO " +
                "FROM FEEDBACKS ORDER BY DATA_ENVIO DESC";
        try (Connection conn = factory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getLong("FEEDBACK_ID"),
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getInt("NIVEL_SATISFACAO"),
                        rs.getString("SUGESTAO"),
                        rs.getDate("DATA_ENVIO")
                );
                lista.add(f);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar feedbacks", e);
        }

        return lista;
    }

    // Update
    public void atualizarFeedback(Feedback feedback) {
        String sql = "UPDATE FEEDBACKS " +
                "SET NOME = ?, EMAIL = ?, SUGESTAO = ?, NIVEL_SATISFACAO = ? " +
                "WHERE FEEDBACK_ID = ?";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, feedback.getNome());
            ps.setString(2, feedback.getEmail());
            ps.setString(3, feedback.getSugestao());
            ps.setInt(4, feedback.getNivelSatisfacao());
            ps.setLong(5, feedback.getIdFeedback());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum feedback encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar feedback", e);
        }
    }

    // Delete
    public void deletarFeedback(long feedbackId) {
        String sql = "DELETE FROM FEEDBACKS WHERE FEEDBACK_ID = ?";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, feedbackId);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum feedback encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar feedback", e);
        }
    }
}
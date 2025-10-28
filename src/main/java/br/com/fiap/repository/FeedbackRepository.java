package br.com.fiap.repository;

import br.com.fiap.model.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    // Create
    public void salvarFeedback(Feedback feedback) {
        String sql = "INSERT INTO SPRINT_FEEDBACKS (NOME, EMAIL, SUGESTAO, NIVEL_SATISFACAO) VALUES (?, ?, ?, ?)";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, feedback.getNome());
            ps.setString(2, feedback.getEmail());
            ps.setString(3, feedback.getSugestao());
            ps.setInt(4, feedback.getNivelSatisfacao());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar feedback", e);
        }
    }

    // Read
    public List<Feedback> listarTodos() {
        List<Feedback> lista = new ArrayList<>();
        String sql = "SELECT ID_FEEDBACK, NOME, EMAIL, NIVEL_SATISFACAO, SUGESTAO, DATA_ENVIO " +
                "FROM SPRINT_FEEDBACKS ORDER BY DATA_ENVIO DESC";
        try (Connection conn = factory.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getLong("ID_FEEDBACK"),
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
}
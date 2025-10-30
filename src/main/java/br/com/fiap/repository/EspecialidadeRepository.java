package br.com.fiap.repository;

import br.com.fiap.model.Especialidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public void salvarEspecialidade(Especialidade especialidade) {
        String sql = "INSERT INTO ESPECIALIDADES (NOME) VALUES (?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidade.getNome());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar especialidade", e);
        }
    }

    public List<Especialidade> listarTodos() {
        List<Especialidade> lista = new ArrayList<>();

        String sql = "SELECT ESPECIALIDADE_ID, NOME FROM ESPECIALIDADES";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Especialidade e = new Especialidade();
                e.setIdEspecialidade(rs.getLong("especialidade_id"));
                e.setNome(rs.getString("nome"));

                lista.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar especialidades", e);
        }

        return lista;
    }

    public Especialidade buscarPorId(Long id) {
        String sql = "SELECT ESPECIALIDADE_ID, NOME FROM ESPECIALIDADES WHERE ESPECIALIDADE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Especialidade e = new Especialidade();
                    e.setIdEspecialidade(rs.getLong("especialidade_id"));
                    e.setNome(rs.getString("nome"));
                    return e;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar especialidade por ID", e);
        }

        return null;
    }

    public void atualizarEspecialidade(Especialidade especialidade) {
        String sql = "UPDATE ESPECIALIDADES SET NOME = ? WHERE ESPECIALIDADE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, especialidade.getNome());
            ps.setLong(2, especialidade.getIdEspecialidade());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhuma especialidade encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar especialidade", e);
        }
    }

    public void deletarEspecialidade(Long id) {
        String sql = "DELETE FROM ESPECIALIDADES WHERE ESPECIALIDADE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhuma especialidade encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar especialidade", e);
        }
    }
}

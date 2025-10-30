package br.com.fiap.repository;

import br.com.fiap.model.Cuidador;
import br.com.fiap.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuidadorRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public void salvarCuidador(Cuidador cuidador) {
        String sql = "INSERT INTO CUIDADORES (NOME, CPF, CONTATO, PACIENTE_ID) VALUES (?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getCpf());
            ps.setString(3, cuidador.getContato());

            if (cuidador.getPaciente() != null && cuidador.getPaciente().getIdPaciente() != null) {
                ps.setLong(4, cuidador.getPaciente().getIdPaciente());
            } else {
                ps.setNull(4, Types.BIGINT);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cuidador", e);
        }
    }

    public List<Cuidador> listarTodos() {
        List<Cuidador> lista = new ArrayList<>();

        String sql = "SELECT c.CUIDADOR_ID, c.NOME, c.CPF, c.CONTATO, " +
                "p.PACIENTE_ID, p.NOME AS paciente_nome " +
                "FROM CUIDADORES c " +
                "LEFT JOIN PACIENTES p ON c.PACIENTE_ID = p.PACIENTE_ID";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cuidador c = new Cuidador();
                c.setId(rs.getLong("cuidador_id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setContato(rs.getString("contato"));

                Long idPaciente = rs.getLong("paciente_id");
                if (idPaciente != null && idPaciente > 0) {
                    Paciente p = new Paciente();
                    p.setIdPaciente(idPaciente);
                    p.setNome(rs.getString("paciente_nome"));
                    c.setPaciente(p);
                }

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cuidadores", e);
        }

        return lista;
    }

    public Cuidador buscarPorId(Long id) {
        String sql = "SELECT c.CUIDADOR_ID, c.NOME, c.CPF, c.CONTATO, " +
                "p.PACIENTE_ID, p.NOME AS paciente_nome " +
                "FROM CUIDADORES c " +
                "LEFT JOIN PACIENTES p ON c.PACIENTE_ID = p.PACIENTE_ID " +
                "WHERE c.CUIDADOR_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cuidador c = new Cuidador();
                    c.setId(rs.getLong("cuidador_id"));
                    c.setNome(rs.getString("nome"));
                    c.setCpf(rs.getString("cpf"));
                    c.setContato(rs.getString("contato"));

                    Long idPaciente = rs.getLong("paciente_id");
                    if (idPaciente != null && idPaciente > 0) {
                        Paciente p = new Paciente();
                        p.setIdPaciente(idPaciente);
                        p.setNome(rs.getString("paciente_nome"));
                        c.setPaciente(p);
                    }

                    return c;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cuidador por ID", e);
        }

        return null;
    }

    public void atualizarCuidador(Cuidador cuidador) {
        String sql = "UPDATE CUIDADORES SET NOME = ?, CPF = ?, CONTATO = ?, PACIENTE_ID = ? WHERE CUIDADOR_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cuidador.getNome());
            ps.setString(2, cuidador.getCpf());
            ps.setString(3, cuidador.getContato());

            if (cuidador.getPaciente() != null && cuidador.getPaciente().getIdPaciente() != null) {
                ps.setLong(4, cuidador.getPaciente().getIdPaciente());
            } else {
                ps.setNull(4, Types.BIGINT);
            }

            ps.setLong(5, cuidador.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum cuidador encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cuidador", e);
        }
    }

    public void deletarCuidador(Long id) {
        String sql = "DELETE FROM CUIDADORES WHERE CUIDADOR_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhum cuidador encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar cuidador", e);
        }
    }

}

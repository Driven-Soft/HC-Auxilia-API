package br.com.fiap.repository;

import br.com.fiap.model.Consulta;
import br.com.fiap.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public void salvarConsulta(Consulta consulta) {
        String sql = "INSERT INTO CONSULTAS (PACIENTE_ID, DATA_HORA, MOTIVO) VALUES (?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Paciente
            if (consulta.getPaciente() != null && consulta.getPaciente().getIdPaciente() != null) {
                ps.setLong(1, consulta.getPaciente().getIdPaciente());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            // Data/Hora
            if (consulta.getDataHora() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(consulta.getDataHora()));
            } else {
                ps.setTimestamp(2, null);
            }

            // Motivo
            ps.setString(3, consulta.getMotivo());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta", e);
        }
    }

    public List<Consulta> listarTodas() {
        List<Consulta> lista = new ArrayList<>();

        String sql = "SELECT c.CONSULTA_ID, c.DATA_HORA, c.MOTIVO, " +
                "p.PACIENTE_ID, p.NOME AS paciente_nome " +
                "FROM CONSULTAS c " +
                "LEFT JOIN PACIENTES p ON c.PACIENTE_ID = p.PACIENTE_ID";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Consulta c = new Consulta();
                c.setId(rs.getLong("consulta_id"));

                // Paciente
                Long idPaciente = rs.getLong("paciente_id");
                if (idPaciente != null && idPaciente > 0) {
                    Paciente p = new Paciente();
                    p.setIdPaciente(idPaciente);
                    p.setNome(rs.getString("paciente_nome"));
                    c.setPaciente(p);
                }

                // Data
                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    c.setDataHora(ts.toLocalDateTime());
                }

                c.setMotivo(rs.getString("motivo"));

                lista.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas", e);
        }

        return lista;
    }

    public Consulta buscarPorId(Long id) {
        String sql = "SELECT c.CONSULTA_ID, c.DATA_HORA, c.MOTIVO, " +
                "p.PACIENTE_ID, p.NOME AS paciente_nome " +
                "FROM CONSULTAS c " +
                "LEFT JOIN PACIENTES p ON c.PACIENTE_ID = p.PACIENTE_ID " +
                "WHERE c.CONSULTA_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Consulta c = new Consulta();
                    c.setId(rs.getLong("consulta_id"));

                    // Paciente
                    Long idPaciente = rs.getLong("paciente_id");
                    if (idPaciente != null && idPaciente > 0) {
                        Paciente p = new Paciente();
                        p.setIdPaciente(idPaciente);
                        p.setNome(rs.getString("paciente_nome"));
                        c.setPaciente(p);
                    }

                    // Data
                    Timestamp ts = rs.getTimestamp("data_hora");
                    if (ts != null) {
                        c.setDataHora(ts.toLocalDateTime());
                    }

                    c.setMotivo(rs.getString("motivo"));
                    return c;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta por ID", e);
        }

        return null;
    }

    public void atualizarConsulta(Consulta consulta) {
        String sql = "UPDATE CONSULTAS SET PACIENTE_ID = ?, DATA_HORA = ?, MOTIVO = ? WHERE CONSULTA_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            // Paciente
            if (consulta.getPaciente() != null && consulta.getPaciente().getIdPaciente() != null) {
                ps.setLong(1, consulta.getPaciente().getIdPaciente());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            // Data
            if (consulta.getDataHora() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(consulta.getDataHora()));
            } else {
                ps.setTimestamp(2, null);
            }

            ps.setString(3, consulta.getMotivo());
            ps.setLong(4, consulta.getId());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhuma consulta encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta", e);
        }
    }

    public void deletarConsulta(Long id) {
        String sql = "DELETE FROM CONSULTAS WHERE CONSULTA_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhuma consulta encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar consulta", e);
        }
    }

}

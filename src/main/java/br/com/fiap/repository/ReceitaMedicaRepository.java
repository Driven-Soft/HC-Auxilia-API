package br.com.fiap.repository;

import br.com.fiap.model.ReceitaMedica;
import br.com.fiap.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaMedicaRepository {

    private ConnectionFactory factory = new ConnectionFactory();
    private PacienteRepository pacienteRepo = new PacienteRepository();

    public void salvarReceita(ReceitaMedica receita) {
        String sql = "INSERT INTO RECEITAS (PACIENTE_ID, MEDICAMENTOS, QTD_DIAS, OBSERVACAO) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Paciente
            if (receita.getPaciente() != null && receita.getPaciente().getIdPaciente() != null) {
                ps.setLong(1, receita.getPaciente().getIdPaciente());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            ps.setString(2, receita.getMedicamentos());
            ps.setInt(3, receita.getQtdDias());
            ps.setString(4, receita.getObservacao());

            ps.executeUpdate();

            // Recupera ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    receita.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar receita", e);
        }
    }

    public List<ReceitaMedica> listarTodas() {
        List<ReceitaMedica> lista = new ArrayList<>();

        String sql = "SELECT R.ID, R.MEDICAMENTOS, R.QTD_DIAS, R.OBSERVACAO, " +
                "P.PACIENTE_ID, P.NOME AS paciente_nome " +
                "FROM RECEITAS R " +
                "LEFT JOIN PACIENTES P ON R.PACIENTE_ID = P.PACIENTE_ID";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ReceitaMedica r = new ReceitaMedica();
                r.setId(rs.getLong("id"));
                r.setMedicamentos(rs.getString("medicamentos"));
                r.setQtdDias(rs.getInt("qtd_dias"));
                r.setObservacao(rs.getString("observacao"));

                Long idPaciente = rs.getLong("paciente_id");
                if (idPaciente != null && idPaciente > 0) {
                    Paciente p = new Paciente();
                    p.setIdPaciente(idPaciente);
                    p.setNome(rs.getString("paciente_nome"));
                    r.setPaciente(p);
                }

                lista.add(r);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar receitas", e);
        }

        return lista;
    }

    public ReceitaMedica buscarPorId(Long id) {
        String sql = "SELECT R.ID, R.MEDICAMENTOS, R.QTD_DIAS, R.OBSERVACAO, " +
                "P.PACIENTE_ID, P.NOME AS paciente_nome " +
                "FROM RECEITAS R " +
                "LEFT JOIN PACIENTES P ON R.PACIENTE_ID = P.PACIENTE_ID " +
                "WHERE R.ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ReceitaMedica r = new ReceitaMedica();
                    r.setId(rs.getLong("id"));
                    r.setMedicamentos(rs.getString("medicamentos"));
                    r.setQtdDias(rs.getInt("qtd_dias"));
                    r.setObservacao(rs.getString("observacao"));

                    Long idPaciente = rs.getLong("paciente_id");
                    if (idPaciente != null && idPaciente > 0) {
                        Paciente p = new Paciente();
                        p.setIdPaciente(idPaciente);
                        p.setNome(rs.getString("paciente_nome"));
                        r.setPaciente(p);
                    }

                    return r;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar receita por ID", e);
        }

        return null;
    }

    public void atualizarReceita(ReceitaMedica receita) {
        String sql = "UPDATE RECEITAS SET PACIENTE_ID = ?, MEDICAMENTOS = ?, QTD_DIAS = ?, OBSERVACAO = ? " +
                "WHERE ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (receita.getPaciente() != null && receita.getPaciente().getIdPaciente() != null) {
                ps.setLong(1, receita.getPaciente().getIdPaciente());
            } else {
                ps.setNull(1, Types.BIGINT);
            }

            ps.setString(2, receita.getMedicamentos());
            ps.setInt(3, receita.getQtdDias());
            ps.setString(4, receita.getObservacao());
            ps.setLong(5, receita.getId());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhuma receita encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar receita", e);
        }
    }

    public void deletarReceita(Long id) {
        String sql = "DELETE FROM RECEITAS WHERE ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhuma receita encontrada com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar receita", e);
        }
    }
}

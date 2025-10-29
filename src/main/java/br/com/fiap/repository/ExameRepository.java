package br.com.fiap.repository;

import br.com.fiap.model.Exame;
import br.com.fiap.view.ExameView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExameRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public void salvarExame(Exame exame) {
        String sql = "INSERT INTO EXAMES (NOME_EXAME, DATA_HORA, RESULTADO, STATUS, PACIENTE_ID, MEDICO_ID, ENDERECO_ID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, exame.getNomeExame());
            if (exame.getDataHoraExame() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(exame.getDataHoraExame()));
            } else {
                ps.setTimestamp(2, null);
            }
            ps.setString(3, exame.getResultado());
            ps.setString(4, exame.getStatus());

            if (exame.getPaciente() != null) {
                ps.setLong(5, exame.getPaciente().getIdPaciente());
            } else {
                ps.setNull(5, java.sql.Types.BIGINT);
            }

            if (exame.getMedico() != null && exame.getMedico().getIdMedico() != null) {
                ps.setLong(6, exame.getMedico().getIdMedico());
            } else {
                ps.setNull(6, java.sql.Types.BIGINT);
            }

            if (exame.getEndereco() != null) {
                ps.setLong(7, exame.getEndereco().getIdEndereco());
            } else {
                ps.setNull(7, java.sql.Types.BIGINT);
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar exame", e);
        }
    }

    public List<ExameView> listarTodos() {
        List<ExameView> lista = new ArrayList<>();
        String sql = "SELECT \n" +
                "    e.exame_id, e.nome_exame, e.data_hora, m.nome AS medico_nome,\n" +
                "    en.rua, en.numero, en.bairro, en.cidade, en.estado, e.status, e.resultado\n" +
                "FROM exames e\n" +
                "JOIN medicos m  \n" +
                "    ON e.medico_id = m.medico_id\n" +
                "LEFT JOIN enderecos en\n" +
                "    ON e.endereco_id = en.endereco_id";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ExameView ev = new ExameView();
                ev.setIdExame(rs.getLong("exame_id"));
                ev.setNomeExame(rs.getString("nome_exame"));

                Timestamp ts = rs.getTimestamp("data_hora");
                if (ts != null) {
                    java.time.LocalDateTime ldt = ts.toLocalDateTime();
                    java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy — HH:mm");
                    ev.setDataHoraExame(ldt.format(fmt));
                }

                ev.setNomeDoutor(rs.getString("medico_nome"));
                ev.setRua(rs.getString("rua"));
                ev.setNumero(rs.getString("numero"));
                ev.setBairro(rs.getString("bairro"));
                ev.setCidade(rs.getString("cidade"));
                ev.setEstado(rs.getString("estado"));
                ev.setStatus(rs.getString("status"));

                lista.add(ev);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar exames", e);
        }

        return lista;
    }

    public ExameView buscarPorId(long id) {
        String sql = "SELECT \n" +
                "    e.exame_id, e.nome_exame, e.data_hora, m.nome AS medico_nome,\n" +
                "    en.rua, en.numero, en.bairro, en.cidade, en.estado, e.status, e.resultado\n" +
                "FROM exames e\n" +
                "JOIN medicos m  \n" +
                "    ON e.medico_id = m.medico_id\n" +
                "LEFT JOIN enderecos en\n" +
                "    ON e.endereco_id = en.endereco_id\n" +
                "WHERE e.exame_id = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ExameView ev = new ExameView();
                    ev.setIdExame(rs.getLong("exame_id"));
                    ev.setNomeExame(rs.getString("nome_exame"));
                    Timestamp ts = rs.getTimestamp("data_hora");
                    if (ts != null) {
                        java.time.LocalDateTime ldt = ts.toLocalDateTime();
                        java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        ev.setDataHoraExame(ldt.format(fmt));
                    }
                    ev.setNomeDoutor(rs.getString("medico_nome"));
                    ev.setRua(rs.getString("rua"));
                    ev.setNumero(rs.getString("numero"));
                    ev.setBairro(rs.getString("bairro"));
                    ev.setCidade(rs.getString("cidade"));
                    ev.setEstado(rs.getString("estado"));
                    ev.setStatus(rs.getString("status"));
                    return ev;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar exame por id", e);
        }

        return null;
    }

    public void atualizarExame(Exame exame) {
        String sql = "UPDATE EXAMES SET NOME_EXAME = ?, DATA_HORA = ?, RESULTADO = ?, STATUS = ?, PACIENTE_ID = ?, MEDICO_ID = ?, ENDERECO_ID = ? WHERE EXAME_ID = ?";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, exame.getNomeExame());
            if (exame.getDataHoraExame() != null) {
                ps.setTimestamp(2, Timestamp.valueOf(exame.getDataHoraExame()));
            } else {
                ps.setTimestamp(2, null);
            }
            ps.setString(3, exame.getResultado());
            ps.setString(4, exame.getStatus());

            if (exame.getPaciente() != null) {
                ps.setLong(5, exame.getPaciente().getIdPaciente());
            } else {
                ps.setNull(5, java.sql.Types.BIGINT);
            }

            if (exame.getMedico() != null && exame.getMedico().getIdMedico() != null) {
                ps.setLong(6, exame.getMedico().getIdMedico());
            } else {
                ps.setNull(6, java.sql.Types.BIGINT);
            }

            if (exame.getEndereco() != null) {
                ps.setLong(7, exame.getEndereco().getIdEndereco());
            } else {
                ps.setNull(7, java.sql.Types.BIGINT);
            }

            ps.setLong(8, exame.getIdExame());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum exame encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar exame", e);
        }
    }

    public void deletarExame(long id) {
        String sql = "DELETE FROM EXAMES WHERE EXAME_ID = ?";
        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum exame encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar exame", e);
        }
    }
}

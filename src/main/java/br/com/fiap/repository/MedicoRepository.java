package br.com.fiap.repository;

import br.com.fiap.model.Medico;
import br.com.fiap.model.Endereco;
import br.com.fiap.model.Especialidade;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicoRepository {

    private ConnectionFactory factory = new ConnectionFactory();
    private EnderecoRepository enderecoRepo = new EnderecoRepository();
    private EspecialidadeRepository especialidadeRepo = new EspecialidadeRepository();

    public void salvarMedico(Medico medico) {
        String sql = "INSERT INTO MEDICOS (CRM, NOME, TELEFONE, EMAIL, NASCIMENTO, ENDERECO_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getTelefone());
            ps.setString(4, medico.getEmail());

            if (medico.getNascimento() != null) {
                ps.setDate(5, Date.valueOf(medico.getNascimento()));
            } else {
                ps.setDate(5, null);
            }

            if (medico.getEndereco() != null && medico.getEndereco().getIdEndereco() != null) {
                ps.setLong(6, medico.getEndereco().getIdEndereco());
            } else {
                ps.setNull(6, Types.BIGINT);
            }

            ps.executeUpdate();

            // Recupera o ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    medico.setIdMedico(rs.getLong(1));
                }
            }

            // Salva especialidades na tabela intermediária
            if (medico.getEspecialidades() != null) {
                for (Especialidade e : medico.getEspecialidades()) {
                    String sqlEsp = "INSERT INTO MEDICO_ESPECIALIDADE (MEDICO_ID, ESPECIALIDADE_ID) VALUES (?, ?)";
                    try (PreparedStatement psEsp = conn.prepareStatement(sqlEsp)) {
                        psEsp.setLong(1, medico.getIdMedico());
                        psEsp.setLong(2, e.getIdEspecialidade());
                        psEsp.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar médico", e);
        }
    }

    public List<Medico> listarTodos() {
        List<Medico> lista = new ArrayList<>();

        String sql = "SELECT M.MEDICO_ID, M.CRM, M.NOME, M.TELEFONE, M.EMAIL, M.NASCIMENTO, " +
                "E.ENDERECO_ID, E.RUA, E.NUMERO, E.COMPLEMENTO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP " +
                "FROM MEDICOS M " +
                "LEFT JOIN ENDERECOS E ON M.ENDERECO_ID = E.ENDERECO_ID";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medico m = new Medico();
                m.setIdMedico(rs.getLong("medico_id"));
                m.setCrm(rs.getString("crm"));
                m.setNome(rs.getString("nome"));
                m.setTelefone(rs.getString("telefone"));
                m.setEmail(rs.getString("email"));

                Date nascimento = rs.getDate("nascimento");
                if (nascimento != null) {
                    m.setNascimento(nascimento.toLocalDate());
                }

                // Endereço
                Long idEndereco = rs.getLong("endereco_id");
                if (idEndereco != null && idEndereco > 0) {
                    Endereco e = new Endereco();
                    e.setIdEndereco(idEndereco);
                    e.setRua(rs.getString("rua"));
                    e.setNumero(rs.getString("numero"));
                    e.setComplemento(rs.getString("complemento"));
                    e.setBairro(rs.getString("bairro"));
                    e.setCidade(rs.getString("cidade"));
                    e.setEstado(rs.getString("estado"));
                    e.setCep(rs.getString("cep"));
                    m.setEndereco(e);
                }

                // Especialidades
                String sqlEsp = "SELECT E.ESPECIALIDADE_ID, E.NOME " +
                        "FROM MEDICO_ESPECIALIDADE ME " +
                        "JOIN ESPECIALIDADES E ON ME.ESPECIALIDADE_ID = E.ESPECIALIDADE_ID " +
                        "WHERE ME.MEDICO_ID = ?";
                try (PreparedStatement psEsp = conn.prepareStatement(sqlEsp)) {
                    psEsp.setLong(1, m.getIdMedico());
                    try (ResultSet rsEsp = psEsp.executeQuery()) {
                        while (rsEsp.next()) {
                            Especialidade esp = new Especialidade();
                            esp.setIdEspecialidade(rsEsp.getLong("especialidade_id"));
                            esp.setNome(rsEsp.getString("nome"));
                            m.adicionarEspecialidade(esp);
                        }
                    }
                }

                lista.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar médicos", e);
        }

        return lista;
    }

    public Medico buscarPorId(Long id) {
        String sql = "SELECT M.MEDICO_ID, M.CRM, M.NOME, M.TELEFONE, M.EMAIL, M.NASCIMENTO, " +
                "E.ENDERECO_ID, E.RUA, E.NUMERO, E.COMPLEMENTO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP " +
                "FROM MEDICOS M " +
                "LEFT JOIN ENDERECOS E ON M.ENDERECO_ID = E.ENDERECO_ID " +
                "WHERE M.MEDICO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Medico m = new Medico();
                    m.setIdMedico(rs.getLong("medico_id"));
                    m.setCrm(rs.getString("crm"));
                    m.setNome(rs.getString("nome"));
                    m.setTelefone(rs.getString("telefone"));
                    m.setEmail(rs.getString("email"));

                    Date nascimento = rs.getDate("nascimento");
                    if (nascimento != null) {
                        m.setNascimento(nascimento.toLocalDate());
                    }

                    // Endereço
                    Long idEndereco = rs.getLong("endereco_id");
                    if (idEndereco != null && idEndereco > 0) {
                        Endereco e = new Endereco();
                        e.setIdEndereco(idEndereco);
                        e.setRua(rs.getString("rua"));
                        e.setNumero(rs.getString("numero"));
                        e.setComplemento(rs.getString("complemento"));
                        e.setBairro(rs.getString("bairro"));
                        e.setCidade(rs.getString("cidade"));
                        e.setEstado(rs.getString("estado"));
                        e.setCep(rs.getString("cep"));
                        m.setEndereco(e);
                    }

                    // Especialidades
                    String sqlEsp = "SELECT E.ESPECIALIDADE_ID, E.NOME " +
                            "FROM MEDICO_ESPECIALIDADE ME " +
                            "JOIN ESPECIALIDADES E ON ME.ESPECIALIDADE_ID = E.ESPECIALIDADE_ID " +
                            "WHERE ME.MEDICO_ID = ?";
                    try (PreparedStatement psEsp = conn.prepareStatement(sqlEsp)) {
                        psEsp.setLong(1, m.getIdMedico());
                        try (ResultSet rsEsp = psEsp.executeQuery()) {
                            while (rsEsp.next()) {
                                Especialidade esp = new Especialidade();
                                esp.setIdEspecialidade(rsEsp.getLong("especialidade_id"));
                                esp.setNome(rsEsp.getString("nome"));
                                m.adicionarEspecialidade(esp);
                            }
                        }
                    }

                    return m;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar médico por ID", e);
        }

        return null;
    }

    public void atualizarMedico(Medico medico) {
        String sql = "UPDATE MEDICOS SET CRM = ?, NOME = ?, TELEFONE = ?, EMAIL = ?, NASCIMENTO = ?, ENDERECO_ID = ? " +
                "WHERE MEDICO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, medico.getCrm());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getTelefone());
            ps.setString(4, medico.getEmail());

            if (medico.getNascimento() != null) {
                ps.setDate(5, Date.valueOf(medico.getNascimento()));
            } else {
                ps.setDate(5, null);
            }

            if (medico.getEndereco() != null && medico.getEndereco().getIdEndereco() != null) {
                ps.setLong(6, medico.getEndereco().getIdEndereco());
            } else {
                ps.setNull(6, Types.BIGINT);
            }

            ps.setLong(7, medico.getIdMedico());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum médico encontrado com o ID informado.");
            }

            // Atualiza especialidades: remove todas e insere as novas
            String delEsp = "DELETE FROM MEDICO_ESPECIALIDADE WHERE MEDICO_ID = ?";
            try (PreparedStatement psDel = conn.prepareStatement(delEsp)) {
                psDel.setLong(1, medico.getIdMedico());
                psDel.executeUpdate();
            }

            if (medico.getEspecialidades() != null) {
                for (Especialidade e : medico.getEspecialidades()) {
                    String sqlEsp = "INSERT INTO MEDICO_ESPECIALIDADE (MEDICO_ID, ESPECIALIDADE_ID) VALUES (?, ?)";
                    try (PreparedStatement psEsp = conn.prepareStatement(sqlEsp)) {
                        psEsp.setLong(1, medico.getIdMedico());
                        psEsp.setLong(2, e.getIdEspecialidade());
                        psEsp.executeUpdate();
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar médico", e);
        }
    }

    public void deletarMedico(Long id) {
        String sqlDelEsp = "DELETE FROM MEDICO_ESPECIALIDADE WHERE MEDICO_ID = ?";
        String sqlDelMed = "DELETE FROM MEDICOS WHERE MEDICO_ID = ?";

        try (Connection conn = factory.getConnection()) {
            // Remove especialidades
            try (PreparedStatement psEsp = conn.prepareStatement(sqlDelEsp)) {
                psEsp.setLong(1, id);
                psEsp.executeUpdate();
            }

            // Remove médico
            try (PreparedStatement psMed = conn.prepareStatement(sqlDelMed)) {
                psMed.setLong(1, id);
                int rows = psMed.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Nenhum médico encontrado com o ID informado.");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar médico", e);
        }
    }
}

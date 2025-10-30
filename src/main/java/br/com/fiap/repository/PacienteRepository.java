package br.com.fiap.repository;

import br.com.fiap.model.Paciente;
import br.com.fiap.model.Endereco;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository {

    private ConnectionFactory factory = new ConnectionFactory();
    private EnderecoRepository enderecoRepo = new EnderecoRepository();

    public void salvarPaciente(Paciente paciente) {
        String sql = "INSERT INTO PACIENTES (CPF, NOME, TELEFONE, EMAIL, SEXO, NASCIMENTO, ENDERECO_ID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getNome());
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getSexo());

            if (paciente.getNascimento() != null) {
                ps.setDate(6, Date.valueOf(paciente.getNascimento()));
            } else {
                ps.setDate(6, null);
            }

            if (paciente.getEndereco() != null && paciente.getEndereco().getIdEndereco() != null) {
                ps.setLong(7, paciente.getEndereco().getIdEndereco());
            } else {
                ps.setNull(7, Types.BIGINT);
            }

            ps.executeUpdate();

            // Recupera ID gerado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    paciente.setIdPaciente(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar paciente", e);
        }
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();

        String sql = "SELECT P.PACIENTE_ID, P.CPF, P.NOME, P.TELEFONE, P.EMAIL, P.SEXO, P.NASCIMENTO, " +
                "E.ENDERECO_ID, E.RUA, E.NUMERO, E.COMPLEMENTO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP " +
                "FROM PACIENTES P " +
                "LEFT JOIN ENDERECOS E ON P.ENDERECO_ID = E.ENDERECO_ID";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getLong("paciente_id"));
                p.setCpf(rs.getString("cpf"));
                p.setNome(rs.getString("nome"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setSexo(rs.getString("sexo"));

                Date nascimento = rs.getDate("nascimento");
                if (nascimento != null) {
                    p.setNascimento(nascimento.toLocalDate());
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
                    p.setEndereco(e);
                }

                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar pacientes", e);
        }

        return lista;
    }

    public Paciente buscarPorId(Long id) {
        String sql = "SELECT P.PACIENTE_ID, P.CPF, P.NOME, P.TELEFONE, P.EMAIL, P.SEXO, P.NASCIMENTO, " +
                "E.ENDERECO_ID, E.RUA, E.NUMERO, E.COMPLEMENTO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP " +
                "FROM PACIENTES P " +
                "LEFT JOIN ENDERECOS E ON P.ENDERECO_ID = E.ENDERECO_ID " +
                "WHERE P.PACIENTE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Paciente p = new Paciente();
                    p.setIdPaciente(rs.getLong("paciente_id"));
                    p.setCpf(rs.getString("cpf"));
                    p.setNome(rs.getString("nome"));
                    p.setTelefone(rs.getString("telefone"));
                    p.setEmail(rs.getString("email"));
                    p.setSexo(rs.getString("sexo"));

                    Date nascimento = rs.getDate("nascimento");
                    if (nascimento != null) {
                        p.setNascimento(nascimento.toLocalDate());
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
                        p.setEndereco(e);
                    }

                    return p;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar paciente por ID", e);
        }

        return null;
    }

    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE PACIENTES SET CPF = ?, NOME = ?, TELEFONE = ?, EMAIL = ?, SEXO = ?, NASCIMENTO = ?, ENDERECO_ID = ? " +
                "WHERE PACIENTE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getNome());
            ps.setString(3, paciente.getTelefone());
            ps.setString(4, paciente.getEmail());
            ps.setString(5, paciente.getSexo());

            if (paciente.getNascimento() != null) {
                ps.setDate(6, Date.valueOf(paciente.getNascimento()));
            } else {
                ps.setDate(6, null);
            }

            if (paciente.getEndereco() != null && paciente.getEndereco().getIdEndereco() != null) {
                ps.setLong(7, paciente.getEndereco().getIdEndereco());
            } else {
                ps.setNull(7, Types.BIGINT);
            }

            ps.setLong(8, paciente.getIdPaciente());

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum paciente encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar paciente", e);
        }
    }

    public void deletarPaciente(Long id) {
        String sql = "DELETE FROM PACIENTES WHERE PACIENTE_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Nenhum paciente encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar paciente", e);
        }
    }
}

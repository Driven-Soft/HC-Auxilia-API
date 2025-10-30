package br.com.fiap.repository;

import br.com.fiap.model.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoRepository {

    private ConnectionFactory factory = new ConnectionFactory();

    public void salvarEndereco(Endereco endereco) {
        String sql = "INSERT INTO ENDERECOS (RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setString(7, endereco.getCep());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar endereço", e);
        }
    }

    public List<Endereco> listarTodos() {
        List<Endereco> lista = new ArrayList<>();

        String sql = "SELECT ENDERECO_ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP FROM ENDERECOS";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdEndereco(rs.getLong("endereco_id"));
                e.setRua(rs.getString("rua"));
                e.setNumero(rs.getString("numero"));
                e.setComplemento(rs.getString("complemento"));
                e.setBairro(rs.getString("bairro"));
                e.setCidade(rs.getString("cidade"));
                e.setEstado(rs.getString("estado"));
                e.setCep(rs.getString("cep"));

                lista.add(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar endereços", e);
        }

        return lista;
    }

    public Endereco buscarPorId(Long id) {
        String sql = "SELECT ENDERECO_ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CIDADE, ESTADO, CEP " +
                "FROM ENDERECOS WHERE ENDERECO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Endereco e = new Endereco();
                    e.setIdEndereco(rs.getLong("endereco_id"));
                    e.setRua(rs.getString("rua"));
                    e.setNumero(rs.getString("numero"));
                    e.setComplemento(rs.getString("complemento"));
                    e.setBairro(rs.getString("bairro"));
                    e.setCidade(rs.getString("cidade"));
                    e.setEstado(rs.getString("estado"));
                    e.setCep(rs.getString("cep"));

                    return e;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar endereço por ID", e);
        }

        return null;
    }

    public void atualizarEndereco(Endereco endereco) {
        String sql = "UPDATE ENDERECOS SET RUA = ?, NUMERO = ?, COMPLEMENTO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, CEP = ? " +
                "WHERE ENDERECO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, endereco.getRua());
            ps.setString(2, endereco.getNumero());
            ps.setString(3, endereco.getComplemento());
            ps.setString(4, endereco.getBairro());
            ps.setString(5, endereco.getCidade());
            ps.setString(6, endereco.getEstado());
            ps.setString(7, endereco.getCep());
            ps.setLong(8, endereco.getIdEndereco());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhum endereço encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar endereço", e);
        }
    }

    public void deletarEndereco(Long id) {
        String sql = "DELETE FROM ENDERECOS WHERE ENDERECO_ID = ?";

        try (Connection conn = factory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("Nenhum endereço encontrado com o ID informado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar endereço", e);
        }
    }
}

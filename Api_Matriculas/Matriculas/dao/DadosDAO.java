package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Dados;

public class DadosDAO {
    private final Connection conexao;

    public DadosDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // ✅ Método corrigido para listar todos os dados
    public List<Dados> listarTodos() throws SQLException {
        List<Dados> lista = new ArrayList<>();
        String sql = "SELECT id, matricula_id, endereco, cep, estado, cidade, idade, genero FROM Dados";

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new Dados(
                    rs.getLong("id"),
                    rs.getLong("matricula_id"),
                    null, // Nome não está na tabela, então deixamos null
                    rs.getString("endereco"),
                    rs.getString("cep"),
                    rs.getString("estado"),
                    rs.getString("cidade"),
                    rs.getInt("idade"),
                    rs.getString("genero")
                ));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar os dados: " + e.getMessage(), e);
        }
        return lista;
    }

    public void inserir(String nome, String endereco, String cep, int idade, String genero) throws SQLException {
        String sql = "CALL inserir_dados(?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, endereco);
            stmt.setString(3, cep);
            stmt.setInt(4, idade);
            stmt.setString(5, genero);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir dados para " + nome + ": " + e.getMessage(), e);
        }
    }

    public Dados buscarPorId(long matriculaId) throws SQLException {
        String sql = "SELECT id, matricula_id, endereco, cep, estado, cidade, idade, genero FROM Dados WHERE matricula_id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, matriculaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Dados(
                        rs.getLong("id"),
                        rs.getLong("matricula_id"),
                        null, // Nome não está na tabela, então deixamos null
                        rs.getString("endereco"),
                        rs.getString("cep"),
                        rs.getString("estado"),
                        rs.getString("cidade"),
                        rs.getInt("idade"),
                        rs.getString("genero")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar dados pelo ID da matrícula: " + matriculaId + ": " + e.getMessage(), e);
        }
        return null;
    }
}





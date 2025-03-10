package service;

import dao.DadosDAO;
import model.Dados;
import util.Conexao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DadosService {
    private final DadosDAO dadosDAO;

    public DadosService() {
        try {
            Connection conexao = Conexao.getConexao();
            this.dadosDAO = new DadosDAO(conexao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    // ✅ Método corrigido para listar todos os dados
    public List<Dados> listarTodos() {
        try {
            return dadosDAO.listarTodos();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os dados: " + e.getMessage(), e);
        }
    }

    public void inserir(String nome, String endereco, String cep, int idade, String genero) {
        try {
            dadosDAO.inserir(nome, endereco, cep, idade, genero);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir dados para: " + nome, e);
        }
    }

    public Dados buscarPorId(long matriculaId) {
        try {
            return dadosDAO.buscarPorId(matriculaId);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar dados pelo ID da matrícula: " + matriculaId, e);
        }
    }
}







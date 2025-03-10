package service;

import dao.MatriculaDAO;
import model.Matricula;
import util.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MatriculaService {
    private MatriculaDAO matriculaDAO;

    public MatriculaService() {
        Connection conexao = Conexao.getConexao();
        this.matriculaDAO = new MatriculaDAO(conexao);
    }

    public void inserir(String nomeCompleto) {
        try {
            matriculaDAO.inserir(nomeCompleto);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir matrícula", e);
        }
    }

    public List<Matricula> listarTodas() {
        try {
            return matriculaDAO.listarTodas();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matrículas", e);
        }
    }

    public List<Matricula> buscarPorNome(String nome) {
        try {
            return matriculaDAO.buscarPorNome(nome);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar matrícula por nome", e);
        }
    }

    public Matricula buscarPorId(long id) {
        try {
            return matriculaDAO.buscarPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar matrícula por ID", e);
        }
    }

    public void alterar(long id, String novoNome) {
        try {
            matriculaDAO.alterar(id, novoNome);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar matrícula", e);
        }
    }

    public void excluir(long id) {
        try {
            matriculaDAO.excluir(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir matrícula", e);
        }
    }
}
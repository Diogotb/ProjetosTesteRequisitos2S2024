package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private Connection connection;

    public LivroDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adicionarLivro(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, isbn) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getIsbn());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livro.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar livro", e);
        }
        finally{
            fecharConexao();
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        String sql = "SELECT * FROM livro WHERE titulo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, titulo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("isbn")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar livro", e);
        } finally{
            fecharConexao();
        }
        return null;
    }

    public List<Livro> listarLivros() {
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("autor"),
                        rs.getString("isbn")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros", e);
        } finally{
            fecharConexao();
        }
        return livros;
    }

    public void fecharConexao() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
        }
    }
}

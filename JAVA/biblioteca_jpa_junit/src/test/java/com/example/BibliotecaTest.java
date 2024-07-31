package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;

import static org.junit.Assert.*;

public class BibliotecaTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Biblioteca biblioteca;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        em = emf.createEntityManager();
        biblioteca = new Biblioteca();
    }

    @After
    public void teardown() {
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Livro").executeUpdate();
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Test
    public void testeAdicionarLivro() {
        Livro livro = new Livro("Java Programming", "John Doe", "1234567890");
        biblioteca.adicionarLivro(livro);

        TypedQuery<Livro> query = em.createQuery("SELECT l FROM Livro l WHERE l.titulo = :titulo", Livro.class);
        query.setParameter("titulo", "Java Programming");
        List<Livro> livros = query.getResultList();

        assertFalse(livros.isEmpty());
        assertEquals("Java Programming", livros.get(0).getTitulo());
    }

    @Test
    public void testeBuscarLivroPorTitulo() {
        Livro livro1 = new Livro("Java Programming", "John Doe", "1234567890");
        Livro livro2 = new Livro("Effective Java", "Joshua Bloch", "0987654321");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        Livro resultado = biblioteca.buscarLivroPorTitulo("Effective Java");
        assertNotNull(resultado);
        assertEquals("Joshua Bloch", resultado.getAutor());
    }

    @Test
    public void testeBuscarLivroPorTituloInexistente() {
        Livro resultado = biblioteca.buscarLivroPorTitulo("Inexistent Book");
        assertNull(resultado);
    }

    @Test
    public void testeListarLivros() {
        Livro livro1 = new Livro("Java Programming", "John Doe", "1234567890");
        Livro livro2 = new Livro("Effective Java", "Joshua Bloch", "0987654321");
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);

        List<Livro> livros = biblioteca.listarLivros();
        assertEquals(2, livros.size());
    }
}

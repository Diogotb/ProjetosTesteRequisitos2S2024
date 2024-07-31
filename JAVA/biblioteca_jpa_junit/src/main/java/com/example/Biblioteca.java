package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Biblioteca {
    private EntityManagerFactory emf;
    private EntityManager em;

    public Biblioteca() {
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        em = emf.createEntityManager();
    }

    public void adicionarLivro(Livro livro) {
        em.getTransaction().begin();
        em.persist(livro);
        em.getTransaction().commit();
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        List<Livro> livros = em.createQuery("SELECT l FROM Livro l WHERE l.titulo = :titulo", Livro.class)
                .setParameter("titulo", titulo)
                .getResultList();
        return livros.isEmpty() ? null : livros.get(0);
    }

    public List<Livro> listarLivros() {
        return em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
    }
}


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.Livro;
import com.example.LivroDAO;

import java.util.List;

import static org.junit.Assert.*;

public class LivroDAOTest {
    private LivroDAO livroDAO;

    @Before
    public void setup() {
        livroDAO = new LivroDAO();
    }

    @After
    public void teardown() {
        livroDAO.fecharConexao();
    }

    @Test
    public void testeAdicionarLivro() {
        Livro livro = new Livro("Java Programming", "John Doe", "1234567890");
        livroDAO.adicionarLivro(livro);

        Livro resultado = livroDAO.buscarLivroPorTitulo("Java Programming");
        assertNotNull(resultado);
        assertEquals("Java Programming", resultado.getTitulo());
    }

    @Test
    public void testeBuscarLivroPorTitulo() {
        Livro livro1 = new Livro("Java Programming", "John Doe", "1234567890");
        Livro livro2 = new Livro("Effective Java", "Joshua Bloch", "0987654321");
        livroDAO.adicionarLivro(livro1);
        livroDAO.adicionarLivro(livro2);

        Livro resultado = livroDAO.buscarLivroPorTitulo("Effective Java");
        assertNotNull(resultado);
        assertEquals("Joshua Bloch", resultado.getAutor());
    }

    @Test
    public void testeBuscarLivroPorTituloInexistente() {
        Livro resultado = livroDAO.buscarLivroPorTitulo("Inexistent Book");
        assertNull(resultado);
    }

    @Test
    public void testeListarLivros() {
        Livro livro1 = new Livro("Java Programming", "John Doe", "1234567890");
        Livro livro2 = new Livro("Effective Java", "Joshua Bloch", "0987654321");
        livroDAO.adicionarLivro(livro1);
        livroDAO.adicionarLivro(livro2);

        List<Livro> livros = livroDAO.listarLivros();
        assertEquals(2, livros.size());
    }
}

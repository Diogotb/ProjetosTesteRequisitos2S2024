import org.junit.Before;
import org.junit.Test;

import com.example.Inventario;
import com.example.Produto;

import static org.junit.Assert.assertEquals;

import java.util.List;

public class InventarioTest {
    private Inventario inventario;

    @Before
    public void setup() {
        inventario = new Inventario();
    }

    @Test
    public void testeAdicionarProduto() {
        Produto produto = new Produto("Produto A", 10, 5.0);
        inventario.adicionarProduto(produto);
        List<Produto> produtos = inventario.listarProdutos();
        assertEquals(1, produtos.size());
        assertEquals("Produto A", produtos.get(0).getNome());
    }

    @Test
    public void testeRemoverProduto() {
        Produto produto = new Produto("Produto B", 20, 10.0);
        inventario.adicionarProduto(produto);
        inventario.removerProduto("Produto B");
        List<Produto> produtos = inventario.listarProdutos();
        assertEquals(0, produtos.size());
    }
    
    @Test
    public void testeAtualizarProduto() {
        Produto produto = new Produto("Produto C", 30, 15.0);
        inventario.adicionarProduto(produto);
        inventario.atualizarProduto("Produto C", 40, 20.0);
        Produto produtoAtualizado = inventario.listarProdutos().get(0);
        assertEquals("Produto C", produtoAtualizado.getNome());
        assertEquals(40, produtoAtualizado.getQuantidade());
        assertEquals(20.0, produtoAtualizado.getPreco(), 0.001);
    }

}

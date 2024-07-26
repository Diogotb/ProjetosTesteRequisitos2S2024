package com.example;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Produto> produtos;

    public Inventario() {
        produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void removerProduto(String nome) {
        produtos.removeIf(produto -> produto.getNome().equals(nome));
    }

    public void atualizarProduto(String nome, int quantidade, double preco) {
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nome)) {
                produto.setQuantidade(quantidade);
                produto.setPreco(preco);
                break;
            }
        }
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }
}

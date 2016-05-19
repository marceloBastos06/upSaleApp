/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsale.upsaleapp.model;

/**
 *
 * @author Mauricio R. Vidal
 */
public class ItemEstoque {
    
    private long id_estoque;
    private long id_produto;
    private int quantidade;

    public long getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(long id_estoque) {
        this.id_estoque = id_estoque;
    }

    public long getId_produto() {
        return id_produto;
    }

    public void setId_produto(long id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
    
}

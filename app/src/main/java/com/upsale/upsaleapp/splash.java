package com.upsale.upsaleapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.upsale.upsaleapp.activity.PrimeiraTelaActivity;
import com.upsale.upsaleapp.database.dao.CategoriaDAO;
import com.upsale.upsaleapp.database.dao.EstoqueDAO;
import com.upsale.upsaleapp.database.dao.ItemEstoqueDAO;
import com.upsale.upsaleapp.database.dao.ItemVendaDAO;
import com.upsale.upsaleapp.database.dao.ProdutoDAO;
import com.upsale.upsaleapp.database.dao.UsuarioDAO;
import com.upsale.upsaleapp.database.dao.VendaDAO;
import com.upsale.upsaleapp.model.Categoria;
import com.upsale.upsaleapp.model.Estoque;
import com.upsale.upsaleapp.model.ItemEstoque;
import com.upsale.upsaleapp.model.ItemVenda;
import com.upsale.upsaleapp.model.Produto;
import com.upsale.upsaleapp.model.Usuario;
import com.upsale.upsaleapp.model.Venda;
import com.upsale.upsaleapp.ws.WSUpSale;

import java.util.List;

public class splash extends AppCompatActivity implements Runnable {

    private UsuarioDAO daoUsuario;
    private CategoriaDAO daoCategoria;
    private EstoqueDAO daoEstoque;
    private VendaDAO daoVenda;
    private ProdutoDAO daoProduto;
    private ItemEstoqueDAO daoItemEstoque;
    private ItemVendaDAO daoItemVenda;

    private void initBD(Context context){
        daoUsuario = new UsuarioDAO(context);
        daoCategoria = new CategoriaDAO(context);
        daoEstoque = new EstoqueDAO(context);
        daoVenda = new VendaDAO(context);
        daoProduto = new ProdutoDAO(context);
        daoItemEstoque = new ItemEstoqueDAO(context);
        daoItemVenda = new ItemVendaDAO(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(this).start();
    }

    @Override
    public void run() {
        initBD(this);
        if(daoUsuario.getUsuario()==null){
            try {
                Thread.sleep(3*1000);// 3 segundos
                Intent it = new Intent(splash.this, PrimeiraTelaActivity.class);
                startActivity(it);
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        Usuario user = daoUsuario.getUsuario();
        List<Categoria> categorias = WSUpSale.getCategorias();
        List<Estoque> estoques = WSUpSale.getEstoques();
        List<Venda> vendas = WSUpSale.getVendas();
        List<Produto> produtos = WSUpSale.getProdutos(user.getId());
        if(produtos != null) {
            for (Produto p : produtos) {
                List<ItemEstoque> itemEstoques = WSUpSale.getItemEstoque(p.getId());
                List<ItemVenda> itemVendas = WSUpSale.getItemVendas(p.getId());
                daoItemEstoque.salvar(itemEstoques);
                daoItemVenda.salvar(itemVendas);
            }
        }
        daoCategoria.salvar(categorias);
        daoEstoque.salvar(estoques);
        daoVenda.salvar(vendas);
        daoProduto.salvar(produtos);
        Intent it = new Intent(splash.this, MainActivity.class);
        startActivity(it);
        finish();
    }
}

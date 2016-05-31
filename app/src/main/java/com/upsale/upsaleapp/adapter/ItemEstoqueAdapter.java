package com.upsale.upsaleapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.upsale.upsaleapp.R;
import com.upsale.upsaleapp.database.dao.ProdutoDAO;
import com.upsale.upsaleapp.model.ItemEstoque;
import com.upsale.upsaleapp.model.Produto;

import org.w3c.dom.Text;

/**
 * Created by Mauricio R. Vidal on 30/05/2016.
 */
public class ItemEstoqueAdapter extends ArrayAdapter<ItemEstoque>{

    private LayoutInflater inflater;
    private int resource;
    private ProdutoDAO daoProduto;

    public ItemEstoqueAdapter(Context context) {
        super(context, R.layout.item_itemestoque);
        inflater = LayoutInflater.from(context);
        daoProduto = new ProdutoDAO(context);
        this.resource = R.layout.item_itemestoque;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(resource, parent, false);
        }
        TextView nome = (TextView) convertView.findViewById(R.id.editNome);
        TextView descricao = (TextView) convertView.findViewById(R.id.editDescricao);
        TextView quantidade = (TextView) convertView.findViewById(R.id.editQuantidade);
        ProgressBar progress = (ProgressBar) convertView.findViewById(R.id.progressBar2);

        ItemEstoque ie = getItem(position);
        Produto p = daoProduto.getProdutoPorId(ie.getId_produto());

        nome.setText(p.getNome());
        descricao.setText(p.getDescricao());
        quantidade.setText(ie.getQuantidade() + "");
        progress.setMax(30);//Tamanho maximo do estoque
        int val = (int)((ie.getQuantidade() / 30.0)*30);
        System.out.println("DEBUG: "+ val);
        progress.setProgress(val);
        return convertView;
    }
}

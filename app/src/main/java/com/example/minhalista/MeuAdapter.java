package com.example.minhalista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MeuAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MeuItem> itens;

    public MeuAdapter(MainActivity mainActivity, List<MeuItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item, parent, false);
        return new MeuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MeuItem item = itens.get(position);

        View v = holder.itemView;

        TextView tvTitulo = v.findViewById(R.id.tvTitulo);
        tvTitulo.setText(item.titulo);

        TextView tvDescricao = v.findViewById(R.id.tvDescricao);
        tvDescricao.setText(item.descricao);

        ImageView imvFoto = v.findViewById(R.id.imvFoto);
        imvFoto.setImageBitmap(item.img);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}

package com.example.minhalista;

import android.graphics.Bitmap;

//Classe para armazenar os dados dos itens

public class MeuItem {
    Bitmap img;
    String titulo;
    String descricao;

    public MeuItem(Bitmap img, String titulo, String descricao) {
        this.img = img;
        this.titulo = titulo;
        this.descricao = descricao;
    }
}

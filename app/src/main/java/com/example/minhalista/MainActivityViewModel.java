package com.example.minhalista;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    //Classe ViewModel

    List<MeuItem> itens = new ArrayList<>();

    public List<MeuItem> getItens() {
        return itens;
    }
}

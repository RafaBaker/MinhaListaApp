package com.example.minhalista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    MeuAdapter meuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Criando uma intent para seja redirecionada ao NovoItemActivity
        FloatingActionButton fabAdicionar = findViewById(R.id.fabAdicionar);
        fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NovoItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });


        //Criando o ViewModel e pegando os dados da lista de items
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class); //o ViewModelProvider gerencia as ViewModel's

        //Array de itens do ViewModel
        List<MeuItem> itens = vm.getItens();

        //Definindo um tamanho fixo para o RecyclerView
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setHasFixedSize(true); // O padrão é true

        //LayouManager para definir como os itens serão distribuidos na tela
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this); //RecyclerView.HORIZONTAL pra ficar na Horizontal (Conversas de um app)
        rvLista.setLayoutManager(layoutManager);

        meuAdapter = new MeuAdapter(MainActivity.this, itens);
        rvLista.setAdapter(meuAdapter);
    }

    @Override

    //Executado depois de voltar da NovoItemActivity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Se o request code forem iguais...
        if (requestCode == NEW_ITEM_REQUEST) {
            //E se o result for ok...
            if (resultCode == Activity.RESULT_OK) {
                //Dados que vem da NovoItemActivity
                Uri fotoSelecionada = data.getData();
                try {
                    String titulo = data.getStringExtra("titulo");
                    String descricao = data.getStringExtra("descricao");
                    Bitmap fotoSelecionadaBitmap = Utils.getBitmap(MainActivity.this, fotoSelecionada, 3000, 300);
                    MeuItem item = new MeuItem(fotoSelecionadaBitmap, titulo, descricao);
                    MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class); //o ViewModelProvider gerencia as ViewModel's
                    List<MeuItem> itens = vm.getItens();
                    itens.add(item);
                    meuAdapter.notifyDataSetChanged();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Mensagens para que vejamos quais metodos estão sendo chamados, facilitando a busca por erros

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MINHA LISTA APP", "Método onStart() foi chamado");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MINHA LISTA APP", "Método onStop() foi chamado");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MINHA LISTA APP", "Método onDestroy() foi chamado");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MINHA LISTA APP", "Método onPausa() foi chamado");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MINHA LISTA APP", "Método onResume() foi chamado");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MINHA LISTA APP", "Método onRestart() foi chamado");
    }
}
package com.example.minhalista;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class NovoItemActivity extends AppCompatActivity {

    static int GALERIA_REQUEST = 1;
    Uri fotoSelecionada = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_item);

        //Pegando o botão que irá executar uma intent para abrir a galeria do celular
        ImageButton imbGaleria = findViewById(R.id.imbGaleria);
        imbGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent pra pegar um arquivo
                Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);

                //Tipo do arquivo que vai ser capturado
                i.setType("image/*");

                //inicia a intent que espera uma resposta
                startActivityForResult(i, GALERIA_REQUEST);
            }
        });

        Button btAdicionar = findViewById(R.id.btAdicionar);
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Testando se tudo está preenchido
                EditText etTitulo = findViewById(R.id.etTitulo);
                String titulo = etTitulo.getText().toString();
                if (titulo.isEmpty()) {
                    Toast.makeText(NovoItemActivity.this, "Titulo Vazio", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etDescricao = findViewById(R.id.etDescricao);
                String descricao = etDescricao.getText().toString();
                if (descricao.isEmpty()) {
                    Toast.makeText(NovoItemActivity.this, "Descrição Vazia", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(fotoSelecionada == null) {
                    Toast.makeText(NovoItemActivity.this, "Foto não selecionada", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Intent para pegar o conteudo e confirmar tal ação
                Intent i = new Intent();
                i.setData(fotoSelecionada);
                i.putExtra("titulo", titulo);
                i.putExtra("descricao", descricao);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Checando se ta tudo certo
        if (requestCode == GALERIA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                //Pegando a URI da imagem do intent
                fotoSelecionada = data.getData();
                ImageView imvFotoPreview = findViewById(R.id.imvFotoPreview);
                imvFotoPreview.setImageURI(fotoSelecionada);
            }
        }
    }
}
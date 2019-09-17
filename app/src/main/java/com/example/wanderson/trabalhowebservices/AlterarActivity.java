package com.example.wanderson.trabalhowebservices;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarActivity extends AppCompatActivity {
    EditText editTextNome,editTextTelefone, editTextInfo;

    Contatinho  contatinhoEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar);

        Intent intent = getIntent();
        if (intent.hasExtra("contatinho")) {
            contatinhoEdit = (Contatinho) intent.getSerializableExtra("contatinho");

            editTextNome = findViewById(R.id.editTextNome);
            editTextTelefone = findViewById(R.id.editTextTelene);
            editTextInfo = findViewById(R.id.editTextEmail);

            editTextNome.setText(contatinhoEdit.getNome());
            editTextTelefone.setText(contatinhoEdit.getTelefone());
            editTextInfo.setText(contatinhoEdit.getInfo());
        }


        Button button = findViewById(R.id.buttonUpdete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IContatinho postService = new RetrofitConfig().getContatinosService();

                Contatinho contatinho = new Contatinho(contatinhoEdit.getId(),editTextNome.getText().toString(), editTextTelefone.getText().toString(), editTextInfo.getText().toString());

                Call<Contatinho> resposta = postService.updateContatinho(contatinho);
                resposta.enqueue(new Callback<Contatinho>() {
                    @Override
                    public void onResponse(retrofit2.Call<Contatinho> call, Response<Contatinho> response) {
                        Contatinho postResposta = response.body();
                        Toast.makeText(AlterarActivity.this, "Contato Alterado com Susseso", Toast.LENGTH_SHORT).show();

                    }


                    @Override
                    public void onFailure(retrofit2.Call<Contatinho> call, Throwable t) {
                        Log.i("ERRO", "ERRO");
                    }
                });



                finish();

            }
        });
    }
}


package com.example.wanderson.trabalhowebservices;

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
public class AdicionarActivity extends AppCompatActivity {
    EditText editTextNome,editTextTelefone, editTextInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);
        editTextNome = findViewById(R.id.editText);
        editTextTelefone = findViewById(R.id.editText2);
        editTextInfo = findViewById(R.id.editText3);


        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IContatinho postService = new RetrofitConfig().getContatinosService();

                Contatinho contatinho = new Contatinho(editTextNome.getText().toString(),editTextTelefone.getText().toString(),editTextInfo.getText().toString());


                Call<Contatinho> resposta = postService.addContatinho(contatinho);
                resposta.enqueue(new Callback<Contatinho>() {
                    @Override
                    public void onResponse(retrofit2.Call<Contatinho> call, Response<Contatinho> response) {
                        Contatinho postResposta = response.body();
                        Toast.makeText(AdicionarActivity.this, "Contato Adicionado", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(retrofit2.Call<Contatinho> call, Throwable t) {
                        Log.i("Erro", "Erro");
                    }
                });
                finish();

            }
        });
    }
}

package com.example.wanderson.trabalhowebservices;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contatinho> contatinhos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdicionarActivity.class);
                startActivity(intent);
            }
        });

//        IContatinho contatinhoService= new RetrofitConfig().getContatinosService();


    }


    @Override
    protected void onStart() {
        super.onStart();
        recyclerView = findViewById(R.id.my_recycler_view);
        final IContatinho getService = new RetrofitConfig().getContatinosService();
        Call<List<Contatinho>> resposta = getService.searchContatinho();
        resposta.enqueue(new Callback<List<Contatinho>>() {


            @Override
            public void onResponse(retrofit2.Call<List<Contatinho>> call, Response<List<Contatinho>> response) {
                ArrayList<Contatinho> postResposta = (ArrayList<Contatinho>) response.body();
                ContatinhoAdapter adapter = new ContatinhoAdapter(postResposta, MainActivity.this);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);

                recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));

                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(retrofit2.Call<List<Contatinho>> call, Throwable t) {
                Log.d("ERRO", "ERRO");
            }
        });


    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_search, menu);
//
//        MenuItem searchItem = menu.findItem(R.id.search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//
//        searchView.setOnQueryTextListener(pesuisar());
//
//
//
//
//        return true;
//
//    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
//
//    }


//    private  SearchView.OnQueryTextListener pesuisar() {
//
//        return new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {

////                Log.i("D", "nome= "+s);
//
//                recyclerView = findViewById(R.id.my_recycler_view);
//                final IContatinho getService = new RetrofitConfig().getContatinosService();
//                Call<List<Contatinho>> resposta = getService.buscarContatinho(169);
//                resposta.enqueue(new Callback<List<Contatinho>>() {
//
//
//                    @Override
//                    public void onResponse(retrofit2.Call<List<Contatinho>> call, Response<List<Contatinho>> response) {
//                        ArrayList<Contatinho> postResposta = (ArrayList<Contatinho>) response.body();
////                        Log.d("Teste", postResposta.getNome());
//                        ContatinhoAdapter adapter = new ContatinhoAdapter(postResposta, MainActivity.this);
//                        recyclerView.setAdapter(adapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
//
//                        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
//
//                        recyclerView.setLayoutManager(layoutManager);
//                    }
//
//                    @Override
//                    public void onFailure(retrofit2.Call<List<Contatinho>> call, Throwable t) {
//                        Log.d("Teste", "Deu Ruim");
//                    }
//                });
//
//
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//
//
//                return true;
//            }
//        };
//    }
}

package com.example.wanderson.trabalhowebservices;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatinhoAdapter extends RecyclerView.Adapter<ContatinhoViewHolder> {

    private List<Contatinho> contatinhos;
    private Context context;

    public ContatinhoAdapter(List<Contatinho> contatinhos, Context context) {
        this.contatinhos = contatinhos;
        this.context = context;
    }

    @NonNull
    @Override
    public ContatinhoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View linha = LayoutInflater.from(context)
                .inflate(R.layout.lista_layaut, viewGroup, false);
        ContatinhoViewHolder holder = new ContatinhoViewHolder(linha);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContatinhoViewHolder contatinhoViewHolder, final int i) {
        final Contatinho contatinho = contatinhos.get(i);
        contatinhoViewHolder.campoNome.setText(contatinho.getNome());
        contatinhoViewHolder.campoTelefone.setText(contatinho.getTelefone());
        contatinhoViewHolder.campoInfo.setText(contatinho.getInfo());


        contatinhoViewHolder.inteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Editar este Usuario?")
                        .setMessage(contatinho.getNome())
                        .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Activity activity = getActivity(view);
                                Intent intent = new Intent(activity, AlterarActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                intent.putExtra("contatinho", contatinho);
                                activity.startActivity(intent);

                            }

                        }).setNegativeButton("CANCELAR", null)
                        .create()
                        .show();

            }


        });


        contatinhoViewHolder.inteView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                final View view = v;
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Excluir este Usuario?")
                        .setMessage(contatinho.getNome())
                        .setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                IContatinho postService = new RetrofitConfig().getContatinosService();
                                Call<Contatinho> resposta = postService.deleteContatinho(contatinho.getId());



                                resposta.enqueue(new Callback<Contatinho>() {
                                    @Override
                                    public void onResponse(Call<Contatinho> call, Response<Contatinho> response) {
                                        Contatinho postResposta = response.body();
                                        removerContatinho(contatinho);
                                        Snackbar.make(view, "Excluiu!", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                    @Override
                                    public void onFailure(retrofit2.Call<Contatinho> call, Throwable t) {
                                        Log.i("ERRO", "ERRO");
                                    }
                                });


                            }

                        }).setNegativeButton("CANCELAR", null)
                        .create()
                        .show();

                return true;
            }

        });
    }

    @Override
    public int getItemCount() {
        return this.contatinhos.size();
//        return 0;
    }


    public void removerContatinho(Contatinho contatinho) {
        int position = contatinhos.indexOf(contatinho);
        contatinhos.remove(position);
        notifyItemRemoved(position);
    }

    private Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


}


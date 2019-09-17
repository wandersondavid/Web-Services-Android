package com.example.wanderson.trabalhowebservices;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContatinhoViewHolder extends RecyclerView.ViewHolder {
    TextView campoNome;
    TextView campoInfo;
    TextView campoTelefone;
    View inteView;



    public ContatinhoViewHolder(View itemView) {
        super(itemView);

        campoNome = itemView.findViewById(R.id.textViewNome);
        campoTelefone = itemView.findViewById(R.id.textViewFone);
        campoInfo = itemView.findViewById(R.id.textViewInf);

        this.inteView=itemView;
    }
}

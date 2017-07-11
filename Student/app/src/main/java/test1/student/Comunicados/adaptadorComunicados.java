package test1.student.Comunicados;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test1.student.R;


/**
 * Created by Jespi on 03-07-2017.
 */

public class adaptadorComunicados extends RecyclerView.Adapter<adaptadorComunicados.ComViewHolder> {

    List<listComunicados> listComunicados;
    Context ctx;

    public adaptadorComunicados(Context ctx, List<listComunicados> data) {
        this.listComunicados = data;
        this.ctx = ctx;
    }


    public class ComViewHolder extends RecyclerView.ViewHolder {

        public TextView card_Texto, card_fech;

        public ComViewHolder(View itemView, Context ctx, ArrayList<listComunicados> listComunicados) {
            super(itemView);

            card_Texto = (TextView) itemView.findViewById(R.id.Car_Tit_com);
            card_fech = (TextView) itemView.findViewById(R.id.Car_txt_com);
        }

    }

    @Override
    public ComViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(ctx).inflate(R.layout.cardview_comunicados, parent, false);
        return new ComViewHolder(itemView, ctx, (ArrayList<listComunicados>) listComunicados);
    }

    @Override
    public void onBindViewHolder(ComViewHolder holder, int position) {

        listComunicados lista = listComunicados.get(position);
        holder.card_Texto.setText(lista.getTexto());
        holder.card_fech.setText(lista.getFecha());
    }

    @Override
    public int getItemCount() {
        return listComunicados.size();
    }



}
package test1.student.Galeria;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import test1.student.R;

/**
 * Created by Jespi on 27-06-2017.
 */

public class adaptadorGaleria extends RecyclerView.Adapter<adaptadorGaleria.MyViewHolder>{

    List<imagenGaleria> listaGaleria;
    Context context;
    public adaptadorGaleria(Context context, List<imagenGaleria>data){
        this.context=context;
        this.listaGaleria=data;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView card_titulo;/*,card_detalle*/
        public ImageView card_Imagen;

        ArrayList<imagenGaleria> listaGaleria = new ArrayList<imagenGaleria>();
        Context context;

        public MyViewHolder(View itemView, Context context, List<imagenGaleria> listaGaleria){
            super(itemView);
            this.listaGaleria= (ArrayList<imagenGaleria>) listaGaleria;
            this.context= context;
            itemView.setOnClickListener(this);
            card_titulo=(TextView)itemView.findViewById(R.id.Car_Tit);
            //card_detalle=(TextView)itemView.findViewById(R.id.Card_des);
            card_Imagen=(ImageView)itemView.findViewById(R.id.Card_img);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            imagenGaleria listaGaleria=this.listaGaleria.get(position);
            Intent in=new Intent(this.context,detalleImagen.class);
            in.putExtra("imagen",listaGaleria.getTitulo());
            this.context.startActivity(in);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(context).inflate(R.layout.cardview_galeria,parent,false);
        return new MyViewHolder(itemView,context,listaGaleria);
    }

    @Override
    public void onBindViewHolder(adaptadorGaleria.MyViewHolder holder, int position) {
        imagenGaleria imagenGaleria = listaGaleria.get(position);
        holder.card_titulo.setText(imagenGaleria.getTitulo());
        //holder.card_detalle.setText(imagenGaleria.getDetalle());
        Glide.with(context).load(imagenGaleria.getImagen()).into(holder.card_Imagen);

    }

    @Override
    public int getItemCount() {
        return listaGaleria.size();
    }

}

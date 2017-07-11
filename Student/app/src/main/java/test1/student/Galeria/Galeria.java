package test1.student.Galeria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test1.student.R;

public class Galeria extends AppCompatActivity {

    private ArrayList<imagenGaleria> listaImagenesGaleria;
    private RecyclerView recyclerView;
    private adaptadorGaleria adapter;

    imagenGaleria imagenGaleria;
    String titulo, texto, imagen, UrlImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaImagenesGaleria = new ArrayList<>();
        adapter = new adaptadorGaleria(this, listaImagenesGaleria);

        final RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        stringBuscar("http://mystudent.16mb.com/andrd/galeria.php");
    }
    public void stringBuscar(String url){
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        /*titulo = jsonObj.getString("raza");
                        texto = "Raza: "+jsonObj.getString("tipo_mascota");
                        imagen = jsonObj.getString("imagen");
                        UrlImagen="http://pruebawebjj.16mb.com/Galeria/"+imagen;*/
                        titulo=jsonObj.getString("titulo");
                        //texto=jsonObj.getString("texto");
                        imagen = jsonObj.getString("imagen");
                        UrlImagen="http://mystudent.16mb.com/galeria/fotos/"+imagen;
                        imagenGaleria = new imagenGaleria(titulo/*,texto*/,UrlImagen);
                        listaImagenesGaleria.add(imagenGaleria);
                    }
                    adapter.notifyDataSetChanged();
                }
                catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

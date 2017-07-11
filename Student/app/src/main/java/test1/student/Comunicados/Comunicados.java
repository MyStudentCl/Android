package test1.student.Comunicados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import test1.student.R;
import test1.student.Principal.User_Data;

public class Comunicados extends AppCompatActivity {

    private ArrayList<listComunicados> listaComunicados;
    private RecyclerView recyclervView_Comunicados;
    private adaptadorComunicados adapter_com;

    listComunicados listComunicados;
    String Texto,Fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicados);

        recyclervView_Comunicados=(RecyclerView)findViewById(R.id.RV_Com);
        listaComunicados =new ArrayList<>();
        adapter_com=new adaptadorComunicados(this,listaComunicados);

        final RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager(this);
        recyclervView_Comunicados.setLayoutManager(mLayoutManager);
        recyclervView_Comunicados.setItemAnimator(new DefaultItemAnimator());
        recyclervView_Comunicados.setAdapter(adapter_com);

        String parametro= User_Data.getIdUser();
        Comunis("http://mystudent.16mb.com/andrd/comunicado.php",parametro);
    }

    private void Comunis(String url,final String parametro) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(j);
                        Texto = jsonObj.getString("desc_comunicado");
                        Fecha=jsonObj.getString("fecha_comunicado");
                        listComunicados = new listComunicados(Texto,Fecha);
                        listaComunicados.add(listComunicados);
                    }
                    adapter_com.notifyDataSetChanged();
                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                Map<String,String> parameters =new HashMap<String, String>();
                parameters.put("id_usuario",parametro);
                return parameters;
            }
        };
        RequestQueue requestQue= Volley.newRequestQueue(this);
        requestQue.add(stringRequest);
    }


}


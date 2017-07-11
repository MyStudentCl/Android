package test1.student.Galeria;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import test1.student.R;

public class detalleImagen extends AppCompatActivity {

    ImageView img;
    TextView tit,det;
    String im,UrlImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_imagen);

        img=(ImageView)findViewById(R.id.imagen);
        tit=(TextView)findViewById(R.id.Titulo);
        det=(TextView)findViewById(R.id.descripcion);

        // Bundle bundle = this.getIntent().getExtras();
        String parametro;// = "FB_IMG_1497616208755.jpg";
        parametro=getIntent().getExtras().getString("imagen");

        //if (bundle != null) {
        //    parametro = bundle.getString("imagen");
        //   setTitle(parametro);
        // }

        cargarDatos("http://mystudent.16mb.com/andrd/detallesCategoria.php",parametro);
    }

    private void cargarDatos(String url,final String parametro) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObj = jsonArray.getJSONObject(i);
                        tit.setText("Nombre: " + jsonObj.getString("titulo"));
                        det.setText("Merito: " + jsonObj.getString("texto"));
                        im = jsonObj.getString("imagen");
                        UrlImagen = "http://mystudent.16mb.com/galeria/fotos/"+im;
                        Glide.with(getApplicationContext()).load(UrlImagen).into(img);


                    }
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("imagen", parametro);

                return parameters;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

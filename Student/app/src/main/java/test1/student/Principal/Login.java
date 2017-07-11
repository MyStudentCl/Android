package test1.student.Principal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

import test1.student.R;


public class Login extends AppCompatActivity{

    private static final String URL_LOGIN="http://mystudent.16mb.com/andrd/login.php";
    //http://mystudent.16mb.com/andrd/ http://pruebawebjj.16mb.com/login.php
    private String UserN, PassWd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText t_User=(EditText)findViewById(R.id.et_Username);
        final EditText t_Pass=(EditText)findViewById(R.id.et_Passwd);

        Button btn_log=(Button)findViewById(R.id.btn_login);

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserN=t_User.getText().toString();
                PassWd=t_Pass.getText().toString();
                login();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Reg=new Intent(getApplicationContext(),Regis.class);
                startActivity(Reg);
            }
        });

    }

    private void login(){
        StringRequest stringRequest =new StringRequest(Request.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success")){
                    User_Data.setIdUser(UserN);
                    Toast.makeText(Login.this,"Bienvenido "+User_Data.getIdUser(),Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Login.this,MenuPrincipal.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Login.this,"Error al Iniciar Sesión",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String, String>();
                map.put("username",UserN);
                map.put("password",PassWd);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

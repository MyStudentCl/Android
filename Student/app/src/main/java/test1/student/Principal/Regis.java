package test1.student.Principal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

public class Regis extends AppCompatActivity{

    private String RRut,RUser,RApp,REmail,RTel,RPass;
    private static final String REGISTRO_URL="http://mystudent.16mb.com/andrd/registro.php";
    //http://mystudent.16mb.com/andrd/  http://pruebawebjj.16mb.com/registro.php
    String p1,p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        final EditText r_Rut=(EditText)findViewById(R.id.regis_RUT);
        final EditText r_Name=(EditText)findViewById(R.id.regis_Name);
        final EditText r_App=(EditText)findViewById(R.id.regis_App);
        final EditText r_Mail=(EditText)findViewById(R.id.regis_Mail);
        final EditText r_Tel=(EditText)findViewById(R.id.regis_Tel);
        final EditText r_Pass=(EditText)findViewById(R.id.regis_Pass);

        Button btn_registrar=(Button)findViewById(R.id.Btn_Regis);

        final EditText r_pass2=(EditText)findViewById(R.id.regis_Pass2);


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RRut=r_Rut.getText().toString();
                RUser=r_Name.getText().toString();
                RApp=r_App.getText().toString();
                REmail=r_Mail.getText().toString();
                RTel=r_Tel.getText().toString();
                RPass=r_Pass.getText().toString();

                p1=r_Pass.getText().toString();
                p2=r_pass2.getText().toString();

                if(p1.equals(p2)){
                    Registrar();
                }else{
                    Toast.makeText(Regis.this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                    r_Pass.setText("");
                    r_pass2.setText("");
                    InputMethodManager teclado=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    teclado.showSoftInput(r_Pass, InputMethodManager.SHOW_IMPLICIT);
                }

            }
        });

    }

    private void Registrar(){


        StringRequest stringRequest =new StringRequest(Request.Method.POST,REGISTRO_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.trim().equals("Exist")){
                    Toast.makeText(Regis.this,"Este usuario ya existe",Toast.LENGTH_SHORT).show();
                    EditText rut=(EditText)findViewById(R.id.regis_RUT);
                    EditText nom=(EditText)findViewById(R.id.regis_Name);
                    EditText app=(EditText)findViewById(R.id.regis_App);
                    EditText email=(EditText)findViewById(R.id.regis_Mail);
                    EditText tel=(EditText)findViewById(R.id.regis_Tel);
                    EditText pass1=(EditText)findViewById(R.id.regis_Pass);
                    EditText pass2=(EditText)findViewById(R.id.regis_Pass2);
                    rut.setText("");
                    nom.setText("");
                    app.setText("");
                    email.setText("");
                    tel.setText("");
                    pass1.setText("");
                    pass2.setText("");
                    InputMethodManager teclado=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    teclado.showSoftInput(rut,InputMethodManager.SHOW_IMPLICIT);
                }else{
                    Toast.makeText(Regis.this,"Usuario Registrado",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Regis.this,Login.class);
                    startActivity(i);
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Regis.this,"Error al Registrar",Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<String, String>();
                map.put("rut",RRut);
                map.put("nombre",RUser);
                map.put("apellido",RApp);
                map.put("email",REmail);
                map.put("fono",RTel);
                map.put("pass",RPass);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

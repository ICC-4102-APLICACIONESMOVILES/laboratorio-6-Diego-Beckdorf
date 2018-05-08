package com.example.diego.continuos_lab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class Login extends AppCompatActivity {

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        networkManager = NetworkManager.getInstance(this);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();
                if (usernameText.matches("") || passwordText.matches("")) {
                    Toast.makeText(getApplicationContext(), "Falta ingresar Email o Contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    onLoginClick(usernameText, passwordText);
                }
            }
        });
    }


    private void getForms(){
        networkManager.getForms(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response);
                Toast.makeText(Login.this, "Loged in and forms loaded!", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, "Could not get Forms", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void onLoginClick(String usernameText, String passwordText){

        try {
            networkManager.login(usernameText, passwordText, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    getForms();
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // TODO: Handle error
                    Toast.makeText(Login.this, "Could not loged in", Toast.LENGTH_SHORT).show();
                    System.out.println(error);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void saveSession(String usernameText, String passwordText) {
        SharedPreferences sharedPreferences = getSharedPreferences("userSession", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", usernameText);
        editor.putString("password", passwordText);
        editor.apply();
    }
}

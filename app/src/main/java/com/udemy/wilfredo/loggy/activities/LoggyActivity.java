package com.udemy.wilfredo.loggy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.udemy.wilfredo.loggy.R;
import com.udemy.wilfredo.loggy.utils.Util;

public class LoggyActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Switch switchRemember;
    private Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggy);
        //Se inicializan los componentes de la UI
        initUI();

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        setFieldsIfExistInPreferences();
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(logIn(editTextEmail.getText().toString(), editTextPassword.getText().toString())){
                    Intent intentLogin = new Intent(LoggyActivity.this,DashboardActivity.class);
                    intentLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    savePreferences(editTextEmail.getText().toString(),editTextPassword.getText().toString());
                    startActivity(intentLogin);
                }
            }
        });
    }

    /**
     * Método que permite realizar login en la aplicación
     * @param email propiedad que representa el email ingresado por el usuario
     * @param password propiedad que representa el password ingresado por el usuario
     * @return true si es un login correcto o false si el login falló por algún motivo
     */
    private boolean logIn(String email, String password){
        boolean login;
        if(!emailIsValid(email)){
            Toast.makeText(this, "Email is wrong!!!", Toast.LENGTH_LONG).show();
            login = false;
        } else if(!passwordIsValid(password)) {
            Toast.makeText(this, "Password is wrong!!!", Toast.LENGTH_LONG).show();
            login = false;
        } else {
            login = true;
        }
        return login;
    }

    /**
     *
     */
    private void setFieldsIfExistInPreferences() {
        String email = Util.getValueForPreferences(prefs,"email");
        String password = Util.getValueForPreferences(prefs,"password");
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            editTextEmail.setText(email);
            editTextPassword.setText(password);
            switchRemember.setChecked(true);
        }
    }

    /**
     * Método que guarda las propiedades en el SharedPreferences
     * @param email  propiedad que representa el email ingresado por el usuario
     * @param password  propiedad que representa el password ingresado por el usuario
     */
    public void savePreferences(String email, String password){
        if(switchRemember.isChecked()) {
            SharedPreferences.Editor sharedEditor = prefs.edit();
            sharedEditor.putString("email",email);
            sharedEditor.putString("password", password);
            sharedEditor.apply();
        }
    }

    /**
     * Método que indica si el email ingresado por el usuario es valido para la aplicación
     * @param email propiedad que representa el email ingresado por el usuario
     * @return true si es valido o falso si es invalido
     */
    private boolean emailIsValid(String email){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * Método que indica si el password ingresado por el usuario es valido para la aplicación
     * @param password propiedad que representa el password ingresado por el usuario
     * @return true si es valido o falso si es invalido
     */
    private boolean passwordIsValid(String password){
        return !TextUtils.isEmpty(password) && password.length() >= 4;
    }

    /**
     * Método que inicializa los componentes de la UI de la aplicación
     */
    private void initUI() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        switchRemember = findViewById(R.id.switchRemember);
        btnLogIn = findViewById(R.id.btnLogin);
    }
}

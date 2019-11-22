package com.example.applabssgonzalezjgonzalezjbultron.Supervisor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.applabssgonzalezjgonzalezjbultron.R;

public class UsuarioComprasRegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_compras_reg);
        this.barraDeMenu();
    }

    private void barraDeMenu() {
        //Obtener perfil con sesion iniciada

        String perfil = getIntent().getStringExtra("name").toString();
        String tipoP = getIntent().getStringExtra("tipo").toString();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(perfil);
        actionBar.setSubtitle(tipoP);
    }



}

package com.example.applabssgonzalezjgonzalezjbultron.Comprador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.applabssgonzalezjgonzalezjbultron.Adapters.ArticulosAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Adapters.CompradorAdapters;
import com.example.applabssgonzalezjgonzalezjbultron.Entidades.Articulos;
import com.example.applabssgonzalezjgonzalezjbultron.Helpers.ArticulosBDHelper;
import com.example.applabssgonzalezjgonzalezjbultron.Login.MainActivity;
import com.example.applabssgonzalezjgonzalezjbultron.R;
import com.example.applabssgonzalezjgonzalezjbultron.Vendedor.ActualizarArticulosActivity;
import com.example.applabssgonzalezjgonzalezjbultron.Vendedor.EliminarArticuloActivity;
import com.example.applabssgonzalezjgonzalezjbultron.Vendedor.RegistrarArticulosActivity;

import java.util.ArrayList;
import java.util.List;

public class CompradorActivity extends AppCompatActivity {

    ListView lstArticulos;
    String perfil;
    String tipoP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprador);
        lstArticulos = (ListView)findViewById(R.id.lst_comprar_articulos);
        this.LoadListViewTemplate();


        this.barraDeMenu();
    }

    private void LoadListViewTemplate()
    {
        List<Articulos> opciones = this.ObtenerDatos();

        CompradorAdapters adapter = new CompradorAdapters(this, opciones);

        lstArticulos.setAdapter(adapter);
    }
    private List<Articulos> ObtenerDatos(){

        List<Articulos> lista = new ArrayList<Articulos>();

        try{
            ArticulosBDHelper arDB = new ArticulosBDHelper(this,"Articulos",null,1);

            SQLiteDatabase db = arDB.getReadableDatabase();

            if (db!= null)
            {

                //OBTINE LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA
                Cursor cursor = db.rawQuery("SELECT * FROM articulos", null);

                if (cursor.moveToFirst()){
                    do {
                        Articulos sm = new Articulos(); //INSERTA LOS DATOS EN LA LISTA

                        sm.setId(cursor.getString(0));
                        sm.setNombre(cursor.getString(1));
                        sm.setCantidad(cursor.getString(2));
                        sm.setEstado(cursor.getString(3));
                        sm.setPrecio(cursor.getString(4));
                        sm.setDire(cursor.getString(5));
                        /*byte[] image = cursor.getBlob(4);
                        sm.setImagen(image);*/

                        lista.add(sm);
                    }while (cursor.moveToNext());
                }
                db.close();
                cursor.close();
            }
        }
        catch (Exception e){
            Toast.makeText(this,"Error -> " + e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }

        return lista;
    }
    public void BuscarDatos(){

    }

    private void barraDeMenu() {
        //Obtener perfil con sesion iniciada
        SharedPreferences prePerfil = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        perfil = prePerfil.getString("perfil","Invitado");
        tipoP = prePerfil.getString("tipoP","Invitado");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(perfil);
        actionBar.setSubtitle(tipoP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar3, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//ACCIONES DE LOS BOTONES DE LA BARRA DE MENU
        switch (item.getItemId()){
            case R.id.cerSesion:
                Log.i("ActionBar","Cerrar");
                SharedPreferences preSesion = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preSesion.edit();
                editor.putString("sesion", "0");
                editor.commit();
                Intent z = new Intent(this, MainActivity.class);
                startActivity(z);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

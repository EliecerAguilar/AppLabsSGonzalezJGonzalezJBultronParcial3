package com.example.applabssgonzalezjgonzalezjbultron.Supervisor;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.applabssgonzalezjgonzalezjbultron.Helpers.datosHelper;
import com.example.applabssgonzalezjgonzalezjbultron.Login.MainActivity;
import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class SupervisorActivity extends AppCompatActivity {

    TextView txtBusqueda;
    Spinner spnTipoUser;
    Button btnBuscar;
    Spinner spnParam;
    ListView listUsuariosS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor);
        this.barraDeMenu();
        this.Iniciar();

    }
    //

    private void Iniciar() {
        txtBusqueda = (TextView) findViewById(R.id.txtBuscarUsuario);
        spnTipoUser = (Spinner) findViewById(R.id.spnTipoUser);
        spnParam = (Spinner)findViewById(R.id.spnTipoCampo);
        spnTipoUsuario();
        spnTipoCampo();
        listUsuariosS = (ListView)findViewById(R.id.ListUsuariosSuper);
        this.LoadListViewTemplate();
        final Intent is = new Intent(this, UsuarioComprasRegActivity.class);;

        listUsuariosS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                String idEmailUsr =((Usuarios) a.getItemAtPosition(position)).getEmail();
                String nameUsr =((Usuarios) a.getItemAtPosition(position)).getNombre();
                String tipoUsr =((Usuarios) a.getItemAtPosition(position)).getTipo();

                //Toast.makeText(SupervisorActivity.this, "Opción seleccionada: " + opcionSeleccionada, Toast.LENGTH_LONG).show();

                switch (tipoUsr){
                    case "1":
                        tipoUsr="Vendedor";
                        break;
                    case "2":
                        tipoUsr="Comprador";
                        break;
                    case "3":
                        tipoUsr="Supervisor";
                        break;
                        default:
                            tipoUsr="Invitado";
                }
                // guardar datos del usuario para el actioBar y consultar la base de datos con el ID
                is.putExtra("name",nameUsr);
                is.putExtra("tipo",tipoUsr);
                is.putExtra("email",idEmailUsr);

                startActivity(is);

            }
        });

    }

    private void LoadListViewTemplate(){
        List<Usuarios> usr = this.ObtenerDatos();

        UsuarioAdapterView adapter = new UsuarioAdapterView(this, usr);

        listUsuariosS.setAdapter(adapter);
    }
 //cambios
    private List<Usuarios> ObtenerDatos(){

        List<Usuarios> lista = new ArrayList<Usuarios>();

        try{
            datosHelper usrDB = new datosHelper(this,"Usuarios",null,1);

            SQLiteDatabase db = usrDB.getReadableDatabase();

            if (db!= null)
            {

                //OBTINE LOS DATOS DE LA TABLA ARTICULOS DEL USUARIO CON LA SESION ACTIVA
                Cursor cursor = db.rawQuery("SELECT * FROM usuariosR ", null);
                Usuarios usr = null;
                if (cursor.moveToFirst()){
                    do {
                        usr = new Usuarios(); //INSERTA LOS DATOS EN LA LISTA
                        usr.setEmail(cursor.getString(0));
                        usr.setTipo(cursor.getString(2));
                        usr.setNombre(cursor.getString(3));
                        usr.setApellido(cursor.getString(4));
                        lista.add(usr);

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

    private void spnTipoUsuario() {
        List<String>spn = new ArrayList<>();
        spn.add("tipo de campo");
        spn.add("email");
        spn.add("nombre");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,spn);
        spnParam.setAdapter(adapter);

    }

    private void spnTipoCampo() {
        //lista de datos a mostrar en el spinner
        List<String> Spn_op = new ArrayList<>();
        Spn_op.add("tipo de ususario");
        Spn_op.add("Comprador");
        Spn_op.add("Vendedor");

        //adaptador de los valores del arrayList a la spinner
        ArrayAdapter<String> AdapterList1 =new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,Spn_op);
        //envio del apadatador al spinner
        spnTipoUser.setAdapter(AdapterList1);
    }


public void BuscarUsuario(View view){

        String opcCampo = spnParam.getSelectedItem().toString();
        int opcTipoUser =spnTipoUser.getSelectedItemPosition();

        if(opcCampo.trim().equals("tipo de campo")){
            Toast.makeText(getApplicationContext(),"escoga un el tipo de campo para buscar",Toast.LENGTH_LONG).show();

        }
        else
            if (opcTipoUser == 0){
                Toast.makeText(getApplicationContext(),"escoga un el tipo de Usuario para buscar",Toast.LENGTH_LONG).show();
            }
            else{
                datosHelper userDB = new datosHelper(this, "Usuarios", null, 1);
                SQLiteDatabase db = userDB.getReadableDatabase();

                String txtbuscar = txtBusqueda.getText().toString();

                if (db != null){
                    Cursor cursor = db.rawQuery("SELECT * FROM usuariosR WHERE "+opcCampo+" LIKE '"+txtbuscar+"' AND tipo LIKE '"+opcTipoUser+"'",null);
                            List<Usuarios> lista = new ArrayList<Usuarios>();
                            Usuarios usr = null;
                            if (cursor.moveToFirst()){
                                do {
                                    usr = new Usuarios(); //INSERTA LOS DATOS EN LA LISTA
                                    usr.setEmail(cursor.getString(0));
                                    usr.setTipo(cursor.getString(2));
                                    usr.setNombre(cursor.getString(3));
                                    usr.setApellido(cursor.getString(4));
                                    lista.add(usr);

                                }while (cursor.moveToNext());
                            }

                            UsuarioAdapterView adapter = new UsuarioAdapterView(this, lista);
                            listUsuariosS.setAdapter(adapter);

                            cursor.close();

                    }
                db.close();

                }



        }




    private void barraDeMenu() {
        //Obtener perfil con sesion iniciada
        SharedPreferences prePerfil = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String perfil = prePerfil.getString("perfil","Invitado");
        String tipoP = prePerfil.getString("tipoP","Invitado");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(perfil);
        actionBar.setSubtitle(tipoP);
    }

    //acciones del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar3, menu);
        return true;
    }


    private void cerrarS(){
        Log.i("ActionBar","Cerrar");
        SharedPreferences preSesion = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preSesion.edit();
        editor.putString("sesion", "0");
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//ACCIONES DE LOS BOTONES DE LA BARRA DE MENU
        switch (item.getItemId()){
            case R.id.cerSesion:
              cerrarS();
                Intent z = new Intent(this, MainActivity.class);
                startActivity(z);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*cerrarS();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

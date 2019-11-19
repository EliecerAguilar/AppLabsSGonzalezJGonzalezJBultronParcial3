package com.example.applabssgonzalezjgonzalezjbultron.Supervisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.applabssgonzalezjgonzalezjbultron.R;

import java.util.ArrayList;
import java.util.List;

public class UsuarioAdapterView extends ArrayAdapter<Usuarios> {
    private List<Usuarios> opciones = new ArrayList<>();

    public UsuarioAdapterView(Context context, List<Usuarios> datos){
        super(context,R.layout.adapter_list_usuario, datos);

        opciones= datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.adapter_list_usuario, null);

        TextView txtNombre = (TextView)item.findViewById(R.id.txtNombreUsr);
        txtNombre.setText(opciones.get(position).getNombre());

        TextView txtTipo = (TextView)item.findViewById(R.id.txtTipoUsr);
        txtTipo.setText("Id: "+opciones.get(position).getTipo());

        return(item);
    }
}

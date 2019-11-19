package com.example.applabssgonzalezjgonzalezjbultron.Supervisor;

public class Usuarios {

    private String nombre;
    private String tipo;

    public Usuarios(String nombreU,String tipoU) {
        this.nombre = nombreU;
        this.tipo = tipoU;
    }

    public Usuarios() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
//private String id;


}

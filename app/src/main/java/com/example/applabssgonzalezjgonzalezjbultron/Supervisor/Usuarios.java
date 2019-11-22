package com.example.applabssgonzalezjgonzalezjbultron.Supervisor;

public class Usuarios {

    private String nombre;
    private String apellido;
    private String email;
    private String tipo;
    private String contra;

    public Usuarios(String nombreU,String tipoU,String EmailU, String apellidoU, String contraU) {
        this.nombre = nombreU;
        this.tipo = tipoU;
        this.apellido = apellidoU;
        this.email = EmailU;
        this.contra =contraU;

    }

    public Usuarios() {
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
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

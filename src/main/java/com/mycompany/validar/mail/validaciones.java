package com.mycompany.validar.mail;

public class validaciones {
    private String nombre;
    private boolean estado;

    public validaciones(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
    public String nombreValidaciones(){
        return nombre;
    }
    public boolean estadoValidaciones(){
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }    
}

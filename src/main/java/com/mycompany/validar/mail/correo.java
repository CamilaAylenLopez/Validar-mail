/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validar.mail;

/**
 *
 * @author camil
 */

import java.util.ArrayList;
import java.util.List;

public class correo { //esta clase es muy extensa no se si no la debería dividir
    private String correo;
    private String derechaCorreo;
    private String izquierdaCorreo;
    private List<validaciones> validaciones = new ArrayList<>();
    private String[] dominiosValidos;
    private String[] TDLValidos;
    private boolean arrobaValidada = false;

    public correo(String correo) {
        this.correo = correo;
        this.validaciones.add(new validaciones("arroba", false));
        this.validaciones.add(new validaciones("puntos", false));
        this.validaciones.add(new validaciones("dominio", false));
        this.validaciones.add(new validaciones("tld", false));
        this.dominiosValidos = new String[] { "palcam", "fppro" };
        this.TDLValidos = new String[] { "es", "org", "com", "cat"};
    }

    public boolean arroba(){
        int arrobas = correo.indexOf('@');
        if(arrobas == -1){
            return false;
        }
        else if(correo.indexOf('@', arrobas + 1) != -1){
            return false;
        }
        this.derechaCorreo = correo.substring(arrobas + 1);
        this.izquierdaCorreo = correo.substring(0, arrobas);
        this.validaciones.get(0).setEstado(true);
        arrobaValidada = true;
        return true;
    }

    public boolean puntos(){
        if (!arroba()) return false;
        
        int puntoIndex = izquierdaCorreo.indexOf('.');
        if (puntoIndex == -1 || puntoIndex == 0 || puntoIndex == izquierdaCorreo.length() - 1) {
            return false;
        } else {
            String palabra1 = izquierdaCorreo.substring(0, puntoIndex);
            String palabra2 = izquierdaCorreo.substring(puntoIndex + 1);

            if (esPalabraValida(palabra1) && esPalabraValida(palabra2)) {
                this.validaciones.get(1).setEstado(true);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean dominios(){
        if (!arroba()) return false;
        
        int puntoIndexDos = derechaCorreo.indexOf('.');
        if (puntoIndexDos == 0 || puntoIndexDos == izquierdaCorreo.length() - 1) return false;
        else if(puntoIndexDos == -1){
            for (String dominioValido : dominiosValidos) {
                if (derechaCorreo.equals(dominioValido)) {
                    this.validaciones.get(2).setEstado(true);
                    return true;
                }
            }
        }
        else {
            String primeraPalabra = derechaCorreo.substring(0, puntoIndexDos);
            for (String dominioValido : dominiosValidos) {
                if (primeraPalabra.equals(dominioValido)) {
                    this.validaciones.get(2).setEstado(true);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean TDL(){
        if (!arroba()) return false;
        
        int puntoIndexTres = derechaCorreo.indexOf('.');
        if (puntoIndexTres == -1 || puntoIndexTres == 0 || puntoIndexTres == derechaCorreo.length() - 1) {
            return false;
        } else {
            String segundaPalabra = derechaCorreo.substring(puntoIndexTres + 1);
            for (String tdlValido : TDLValidos) {
                if (segundaPalabra.equals(tdlValido)) {
                    this.validaciones.get(3).setEstado(true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean esPalabraValida(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    /* en internet encontre esta otra forma de hacerlo
    private boolean esPalabraValida(String palabra) {
    return palabra.matches("[a-zA-Z]+");
    }
     */

    public String correoEscrito(){
        return this.correo;
    }

    public List<validaciones> verValidaciones() {
        return new ArrayList<>(validaciones);
    }    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validar.mail;

/**
 *
 *
 * @author camil
 */

import java.util.ArrayList;
import java.util.List;

public class correo {
    private String correo;
    private String derechaCorreo;
    private String izquierdaCorreo;
    private List<validaciones> validaciones = new ArrayList<>();
    private String[] dominiosValidos;

    public correo(String correo) {
        this.correo = correo;
        this.validaciones.add(new validaciones("arroba", false));
        this.validaciones.add(new validaciones("puntos", false));
        this.validaciones.add(new validaciones("dominio", false));
        this.dominiosValidos = new String[] { "palcam.cat", "palcam.es", "palcam.org", "fppro.com", "fppro.es" };
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
        this.validaciones.get(0).estado = true;
        return true;
    }

    public boolean puntos(){
        if(arroba() == true){
            int puntoIndex = izquierdaCorreo.indexOf('.');

            if (puntoIndex == -1 || puntoIndex == 0 || puntoIndex == izquierdaCorreo.length() - 1) {
                return false;
            } else {
                String palabra1 = izquierdaCorreo.substring(0, puntoIndex);
                String palabra2 = izquierdaCorreo.substring(puntoIndex + 1);
    
                if (esPalabraValida(palabra1) && esPalabraValida(palabra2)) {
                    this.validaciones.get(1).estado = true;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public boolean dominios(){
        if(arroba() == true){
            for (String dominioValido : dominiosValidos) {
                if (derechaCorreo.equals(dominioValido)) {
                    this.validaciones.get(2).estado = true;
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

    public String correoEscrito(){
        return this.correo;
    }

    public List verValidaciones(){
        return validaciones;
    }
}

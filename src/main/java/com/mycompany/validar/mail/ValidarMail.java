/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.validar.mail;

/**
 *
 * @author camil
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Validaciones {
    String nombre;
    boolean estado;

    public Validaciones(String nombre, boolean estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
}

public class ValidarMail {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String mail = "";
        String[] dominiosValidos = {"palcam.cat", "palcam.es", "palcam.org", "fppro.com", "fppro.es"};

        List<Validaciones> validaciones = new ArrayList<>();
        validaciones.add(new Validaciones("arroba", false));
        validaciones.add(new Validaciones("puntos", false));
        validaciones.add(new Validaciones("dominio", false));

        System.out.print("Ingrese su mail para ver si es valido: ");
        mail = teclado.nextLine();
        mail = mail.toLowerCase();

        validarArroba(mail, validaciones);
        validarPuntos(mail, validaciones);
        validarDominio(mail, validaciones, dominiosValidos);

        System.out.println("Resultados de las validaciones:");
        for (Validaciones validacion : validaciones) {
            System.out.println(validacion.nombre + ": " + (validacion.estado ? "Cumplida" : "No cumplida"));
        }
    }
    
    public static void validarArroba(String mail, List<Validaciones> validaciones){
        int arrobas = mail.indexOf('@');
        if(arrobas == -1){
            System.out.println("El correo debe contener una @.");
        }
        else if(mail.indexOf('@', arrobas + 1) != -1){
            System.out.println("El correo no puede contener más de una @.");
        }
        else{
            validaciones.get(0).estado = true;
        }
    }
    public static void validarPuntos(String mail, List<Validaciones> validaciones) {
        int arrobas = mail.indexOf('@');
        if (arrobas == -1) {
            System.out.println("No se puede validar la parte izquierda porque no hay @.");
            return;
        }

        String izquierda = mail.substring(0, arrobas);
        int puntoIndex = izquierda.indexOf('.');

        if (puntoIndex == -1 || puntoIndex == 0 || puntoIndex == izquierda.length() - 1) {
            System.out.println("La parte izquierda debe contener dos palabras separadas por un punto.");
        } else {
            String palabra1 = izquierda.substring(0, puntoIndex);
            String palabra2 = izquierda.substring(puntoIndex + 1);

            if (esPalabraValida(palabra1) && esPalabraValida(palabra2)) {
                validaciones.get(1).estado = true;
            } else {
                System.out.println("Las palabras en la parte izquierda no son validas.");
            }
        }
    }
    public static void validarDominio(String mail, List<Validaciones> validaciones, String[] dominiosValidos) {
        int arrobas = mail.indexOf('@');
        if (arrobas == -1) {
            System.out.println("No se puede validar el dominio porque no hay @.");
            return;
        }

        String derecha = mail.substring(arrobas + 1);

        for (String dominioValido : dominiosValidos) {
            if (derecha.equals(dominioValido)) {
                validaciones.get(2).estado = true;
                return;
            }
        }
        System.out.println("El dominio no es valido.");
    }
    //solo verifica si es una letra, si x ejemplo es un numero no lo reconoce
    private static boolean esPalabraValida(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
}
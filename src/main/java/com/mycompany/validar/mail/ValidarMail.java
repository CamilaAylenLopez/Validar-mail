/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.validar.mail;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camil
 */

import java.util.Scanner;

public class ValidarMail {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        String mail = "";

        System.out.print("Ingrese su mail para ver si es valido: ");
        mail = teclado.nextLine();
        correo mailCorreo = new correo(mail.toLowerCase());
        System.out.println(mailCorreo.correoEscrito());
        
        mailCorreo.arroba();
        mailCorreo.dominios();
        mailCorreo.puntos();
        mailCorreo.TDL();

        System.out.println("Resultados de las validaciones del mail "+ mailCorreo.correoEscrito() + " son:");
        
        List<validaciones> listaValidaciones = new ArrayList<>();
        listaValidaciones.addAll(mailCorreo.verValidaciones());

        for (validaciones validacion : mailCorreo.verValidaciones()) {
            System.out.println(validacion.nombreValidaciones() + ": " + (validacion.estadoValidaciones() ? "Cumplida" : "No cumplida"));
        }
        
    }
}
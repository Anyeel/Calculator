package com.example.test.View.Controller;

public class Cifrado {
    public static String cifrarCesar(String input) {
        String resultado = "";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            resultado += (char)(c + 1); // clave de cifrado suma 1 mas en ASCII al character
        }
        return resultado;
    }

}

package com.allephnogueira.calculadoradechurrasco.Service;

import android.annotation.SuppressLint;

public class StringFormatada {
    @SuppressLint("DefaultLocale")
    public static String stringFormatted (Double parametro1, Double parametro2) {
        return String.format("%.3f", parametro1*parametro2);
    }
}
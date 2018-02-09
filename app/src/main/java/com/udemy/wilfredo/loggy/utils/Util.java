package com.udemy.wilfredo.loggy.utils;

import android.content.SharedPreferences;

/**
 * Clase utiitaria con métodos que pueden ser utilizados en toda la aplicación
 * Created by wilfredo on 08-02-18.
 */

public class Util {

    /**
     * Método que obtiene los parámetros desde el SharedPreferences dada una key
     * @param key propiedad que representa la llave para encontrar el valor
     * @return String el valor desde el Preferences a partir de la llave
     */
    public static String getValueForPreferences(SharedPreferences prefs, String key) {
        return prefs.getString(key,"");
    }

    /**
     * Método que olvida los parámetros guardados en el preference requerido
     */
    public static void forgetFields(SharedPreferences prefs) {
        prefs.edit().clear().apply();
    }
}

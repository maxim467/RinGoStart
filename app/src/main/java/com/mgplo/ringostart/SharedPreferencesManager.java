package com.mgplo.ringostart;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

public class SharedPreferencesManager {

    /**
     * Constante de SharedPreferences.
     */
    public static final String SHARED_PREFERENCES = "RinGoStartSharedPreferences";

    /**
     * TAG para SharedPreferenceManager Class.
     */
    private static final String TAG =  SharedPreferencesManager.class.getSimpleName();

    private static final String SHARED_PREFERENCES_CALENDAR_EXIST ="CalendarSaved" ;

    private static final String SHARED_PREFERENCES_DAY ="SharedPreferencesDay";

    private static final String SHARED_PREFERENCES_MONTH ="SharedPreferencesMonth";

    private static final String SHARED_PREFERENCES_YEAR ="SharedPreferencesYear";

    /**
     * Shared Preferences.
     */
    private SharedPreferences sharedPreferences;

    /**
     * Constructor para SharedPreferencesManager.
     *
     * @param ctx Context necesario para operar con SharedPreference.
     */
    public SharedPreferencesManager(Context ctx) {
        try {
            sharedPreferences = EncryptedSharedPreferences.create(SHARED_PREFERENCES, MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), ctx, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
        } catch (Exception e) {
            sharedPreferences = ctx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        }
    }


//    /**
//     * Obtiene el token almacenado.
//     *
//     * @return Token alamcenado en SahredPreferences.
//     */
//    public String obtainSavedToken() {
//        try {
//            return sharedPreferences.getString(SHARED_PREFERENCES_SAVED_TOKEN, "");
//        } catch (Exception e) {
//            Log.d(TAG, "exception retieving token from shared preferences");
//            return "";
//        }
//    }
//    /**
//     * Obtiene el token almacenado.
//     *
//     * @return Token alamcenado en SahredPreferences.
//     */
    public boolean isUserSaved() {
        try {
            return sharedPreferences.getBoolean(SHARED_PREFERENCES_CALENDAR_EXIST, false);
        } catch (Exception e) {
            Log.d(TAG, "exception retieving token from shared preferences");
            return false;
        }
    }
//    /**
//     * Almacena el token en las SharedPreferences.
//     *
//     * @param user Token que será guardado.
//     */
    public void writeSavedUser(boolean user) {

        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(SHARED_PREFERENCES_CALENDAR_EXIST, user).apply();
        }
    }
//
//    /**
//     * Almacena el token en las SharedPreferences.
//     *
//     * @param token Token que será guardado.
//     */
    public void writeSavedDay(int day) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(SHARED_PREFERENCES_DAY, day).apply();
        }
    }

    public void writeSavedMonth(int month) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(SHARED_PREFERENCES_MONTH, month).apply();
        }
    }

    public void writeSavedYear(int year) {
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(SHARED_PREFERENCES_YEAR, year).apply();
        }
    }
//
//    /**
//     * Obtiene el código de activación almacenado.
//     *
//     * @return pin recuperado de SharedPreferences.
//     */
//    public String obtainSavedPassword(String pin) {
//        try {
//            return sharedPreferences.getString(pin, "");
//        } catch (Exception e) {
//            Log.d(TAG, "exception retieving token from shared preferences");
//            return "";
//        }
//    }
//
//    /**
//     * Almacena el código de activación en las SharedPreferences.
//     *
//     * @param password Código de activación que será guardado.
//     * @param pin Código de activación que será guardado.
//     */
//    public void writeSavedPassword(String pin,String password) {
//
//        if (sharedPreferences != null) {
//            sharedPreferences.edit().putString(pin, password).apply();
//        }
//    }
//
//    /**
//     * Obtiene el código de activación almacenado.
//     *
//     * @return pin recuperado de SharedPreferences.
//     */
    public int obtainSavedDay() {
        try {
            return sharedPreferences.getInt(SHARED_PREFERENCES_DAY, 0);
        } catch (Exception e) {
            Log.d(TAG, "exception retieving token from shared preferences");
            return 0;
        }
    }
    public int obtainSavedMonth() {
        try {
            return sharedPreferences.getInt(SHARED_PREFERENCES_MONTH, 0);
        } catch (Exception e) {
            Log.d(TAG, "exception retieving token from shared preferences");
            return 0;
        }
    }
    public int obtainSavedYear() {
        try {
            return sharedPreferences.getInt(SHARED_PREFERENCES_YEAR, 0);
        } catch (Exception e) {
            Log.d(TAG, "exception retieving token from shared preferences");
            return 0;
        }
    }

    public void forgetUser() {
        sharedPreferences.edit().clear().apply();
    }
//
//    /**
//     * Almacena el código de activación en las SharedPreferences.
//     *
//     * @param password Código de activación que será guardado.
//     * @param nif Código de activación que será guardado.
//     */
//    public void writeSavedNif(String password,String nif) {
//
//        if (sharedPreferences != null) {
//            sharedPreferences.edit().putString(password, nif).apply();
//        }
//    }
//
//    /**
//     * Obtiene el token almacenado.
//     *
//     * @return Token alamcenado en SahredPreferences.
//     */
//    public String obtainSavedBiometric() {
//        try {
//            return sharedPreferences.getString(SHARED_PREFERENCES_BIOMETRIC, "");
//        } catch (Exception e) {
//            Log.d(TAG, "exception retieving token from shared preferences");
//            return "";
//        }
//    }
//
//    /**
//     * Obtiene el token almacenado.
//     *
//     * @return Token alamcenado en SahredPreferences.
//     */
//    public String obtainSavedBiometricIV() {
//        try {
//            return sharedPreferences.getString(SHARED_PREFERENCES_BIOMETRIC_IV, "");
//        } catch (Exception e) {
//            Log.d(TAG, "exception retieving token from shared preferences");
//            return "";
//        }
//    }
//
//    /**
//     * Almacena el token en las SharedPreferences.
//     *
//     * @param biometric Token que será guardado.
//     */
//    public void writeSavedBiometric(String biometric) {
//        Log.d(TAG, "writeSavedToken with size " + biometric.length());
//
//        if (sharedPreferences != null) {
//            sharedPreferences.edit().putString(SHARED_PREFERENCES_BIOMETRIC, biometric).apply();
//        }
//    }
//
//    /**
//     * Almacena el token en las SharedPreferences.
//     *
//     * @param biometricIV Token que será guardado.
//     */
//    public void writeSavedBiometricIV(String biometricIV) {
//        Log.d(TAG, "writeSavedToken with size " + biometricIV.length());
//
//        if (sharedPreferences != null) {
//            sharedPreferences.edit().putString(SHARED_PREFERENCES_BIOMETRIC_IV, biometricIV).apply();
//        }
//    }

//    /**
//     * Obtiene el código de activación almacenado.
//     *
//     * @return pin recuperado de SharedPreferences.
//     */
//    public String obtainSavedPIN() {
//        try {
//            return sharedPreferences.getString(SHARED_PREFERENCES_PIN, "");
//        } catch (Exception e) {
//            Log.d(TAG, "exception retieving token from shared preferences");
//            return "";
//        }
//    }

//    /**
//     * Almacena el código de activación en las SharedPreferences.
//     * @param pin Código de activación que será guardado.
//     */
//    public void writeSavedPIN(String pin) {
//
//        if (sharedPreferences != null) {
//            sharedPreferences.edit().putString(SHARED_PREFERENCES_PIN, pin).apply();
//        }
//    }


//    public void forgetUser() {
//        sharedPreferences.edit().clear().apply();
//    }
}

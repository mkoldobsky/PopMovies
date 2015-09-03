package com.example.mkoldobsky.popmovies;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Utility {
    public static String getPrefSortOrder(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.pref_sort_order_key), "");
    }

    public static void setPrefSortOrder(Context context, String sortOrder){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(context.getString(R.string.pref_sort_order_key), sortOrder);
        edit.apply();
    }

}
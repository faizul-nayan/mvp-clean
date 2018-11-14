package com.stockbook.ims.utills;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Faizul Haque Nayan on 18/10/17.
 */

public class MySharePreferences {

    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    private static MySharePreferences mySharePreferences;

    public static final String MyPREFERENCES = "LocalPreferences";
    public static final String IS_REMEMBER = "remember";


    public MySharePreferences(Context context) {

        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public static MySharePreferences getInstance(Context context){
        if(mySharePreferences == null){
           mySharePreferences =  new MySharePreferences(context);
        }

        return mySharePreferences;
    }

    public void setString(String key, String value){

        editor.putString(key,value);
        editor.apply();
        editor.commit();
    }


    public void setBoolean(String key, boolean temp){
        editor.putBoolean(key, temp);
        editor.apply();
        editor.commit();
    }

        public void setInt(String key, int value) {
            editor.putInt(key, value);
            editor.apply();
            editor.commit();
        }

    public boolean getboolean(String key){return sharedpreferences.getBoolean(key, false);}
    public int getInt(String key){return  sharedpreferences.getInt(key, 0);}
    public String getString(String key){return sharedpreferences.getString(key,"");}

}

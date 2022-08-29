package com.fegssp.motivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {


    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)


    fun storeString(key:String, data:String){
        security.edit().putString(key,data).apply()
    }

    fun getString(key:String): String {
        return security.getString(key,"") ?: ""
    }

    fun clearStoreString(key:String){
        security.edit().putString(key,"").apply()
    }
}
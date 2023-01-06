package com.adrict99.bestfilms.data.preferences

import android.content.Context
import javax.inject.Inject

class SharedPrefs @Inject constructor(private val context: Context) {

    companion object {
        const val SHARED_PREFS = "SHARED_PREFS"
    }

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)

    fun saveInShared(key: String, value: String) {
        val editor = sharedPrefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getFromShared(key: String): String? {
        return sharedPrefs.getString(key, "")
    }

    fun saveIntInShared(key: String, value: Int) {
        val editor = sharedPrefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getIntFromShared(key: String): Int {
        return sharedPrefs.getInt(key, -1)
    }

    fun saveBooleanInShared(key: String, value: Boolean) {
        val editor = sharedPrefs.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBooleanFromShared(key: String): Boolean {
        return sharedPrefs.getBoolean(key, false)
    }

    fun deleteFromShared(key: String) {
        val editor = sharedPrefs.edit()
        editor.remove(key)
        editor.apply()
    }

    fun deleteAllSharedPrefs() {
        sharedPrefs.edit().clear().apply()
    }

}
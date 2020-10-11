package br.com.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import br.com.data.model.DataException
import br.com.domain.storange.Cache

class SharedPreferencesCache(
    context: Context
) : Cache {

    private val name = "APP_PREFERENCES"
    private val mode = Context.MODE_PRIVATE
    private val minSdkVersion = Build.VERSION_CODES.LOLLIPOP
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(name, mode)

    override fun getString(key: String, value: String): String? {
        return sharedPreferences.getString(key, value)
    }

    override fun getBoolean(key: String, value: Boolean): Boolean? {
        return sharedPreferences.getBoolean(key, value)
    }

    override fun getInt(key: String, value: Int): Int {
        return sharedPreferences.getInt(key, value)
    }

    override fun setString(key: String, value: String?) {
        if (value == null) {
            shouldToDoAction { sharedPreferences.edit().remove(key) }
        } else {
            shouldToDoAction {
                sharedPreferences.edit().putString(key, value)
            }
        }
    }

    override fun setInt(key: String, value: Int?) {
        if (value == null) {
            shouldToDoAction { sharedPreferences.edit().remove(key) }
        } else {
            shouldToDoAction {
                sharedPreferences.edit().putInt(key, value)
            }
        }
    }

    override fun setBoolean(key: String, value: Boolean?) {
        if (value == null) {
            shouldToDoAction { sharedPreferences.edit().remove(key) }
        } else {
            shouldToDoAction {
                sharedPreferences.edit().putBoolean(key, value)
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun clear() {
        shouldToDoAction { sharedPreferences.edit().clear() }
    }

    private fun shouldToDoAction(action: () -> SharedPreferences.Editor) {
        if (Build.VERSION.SDK_INT >= minSdkVersion) {
            action.invoke().apply()
        } else {
            throw DataException.MinSDKSharedPreferencesException(minSdkVersion)
        }
    }
}
package ca.aletha.businesscard

import android.content.SharedPreferences
import android.provider.Settings.System.putString
import android.util.Log
import androidx.fragment.app.FragmentActivity

class CustomSharedPreferences {

    private val SHAREDPREFFILE = "ca.aletha.businesscard.SHARED_PREF"
    private var mySharedPreferences: SharedPreferences? = null
    private var fragmentActivity = FragmentActivity()

    fun sharedPrefSetVal(name: String, phone: String, email: String, role: String, photo: String) {
        mySharedPreferences = this.fragmentActivity
            .getSharedPreferences(SHAREDPREFFILE, 0) // 0 for private mode
        var myEditor = this.mySharedPreferences?.edit()

        myEditor?.putString("name", name)
            ?.putString("phone", phone)
            ?.putString("email", email)
            ?.putString("role", role)
            ?.putString("photo", photo)
            ?.commit()

        Log.d("-----Shared Preferences is null? ", (this.mySharedPreferences == null).toString())
    }

    fun sharedPrefGetVal(key: String): String {
        mySharedPreferences = this.fragmentActivity
            .getSharedPreferences(SHAREDPREFFILE, 0)
        return this.mySharedPreferences?.getString(key, "")
            .toString() //default is blank
    }

    fun setFragmentActivity(fa : FragmentActivity) {
        this.fragmentActivity = fa
    }

    fun getFragmentActivity() : FragmentActivity {
        return this.fragmentActivity
    }
}
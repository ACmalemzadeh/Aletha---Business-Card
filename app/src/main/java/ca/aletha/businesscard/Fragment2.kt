package ca.aletha.businesscard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager


/**
 */
class Fragment2 : Fragment() {

    private var mySharedPreferences = CustomSharedPreferences()
    private var editTextName: EditText? = null
    private var editTextPhone: EditText? = null
    private var editTextEmail: EditText? = null
    private var editTextRole: EditText? = null
    private var editTextPhoto: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        editTextName = view.findViewById(R.id.editTextName)
        editTextPhone = view.findViewById(R.id.editTextPhone)
        editTextEmail = view.findViewById(R.id.editTextEmail)
        editTextRole = view.findViewById(R.id.editTextRole)
        editTextPhoto = view.findViewById(R.id.editTextPhoto)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySharedPreferences.setFragmentActivity(this.requireActivity())
        if (mySharedPreferences.sharedPrefGetVal("name") == "") {
            editTextName?.setText("Mehrdad Alemzadeh")
            editTextPhone?.setText("+14168247576")
            editTextEmail?.setText("zade.mehrdad@gmail.com")
            editTextRole?.setText("Senior Architect")
            editTextPhoto?.setText("")
        } else {
            editTextName?.setText(mySharedPreferences.sharedPrefGetVal("name"))
            editTextPhone?.setText(mySharedPreferences.sharedPrefGetVal("phone"))
            editTextEmail?.setText(mySharedPreferences.sharedPrefGetVal("email"))
            editTextRole?.setText(mySharedPreferences.sharedPrefGetVal("role"))
            editTextPhoto?.setText(mySharedPreferences.sharedPrefGetVal("photo"))
        }

        //when save button is clicked
        view.findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            val name = editTextName?.getText().toString()
            val phone = editTextPhone?.getText().toString()
            val email = editTextEmail?.getText().toString()
            val role = editTextRole?.getText().toString()
            val photo = editTextPhoto?.getText().toString()

            mySharedPreferences.sharedPrefSetVal(name, phone, email, role, photo)
            updateQrCode() // Notifies Fragment1 to update QR Code with the new data from Shared Preferences
            Toast.makeText(this.requireActivity(), "VCard QR Code is Ready", Toast.LENGTH_SHORT)
                .show()
        })
    }

    private fun updateQrCode() {
        val intent = Intent("update-qr-code-in-fragment1")
        //intent.putExtra("message", "update")
        LocalBroadcastManager.getInstance(this.requireActivity()).sendBroadcast(intent)
    }
}
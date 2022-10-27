package ca.aletha.businesscard

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class Fragment1 : Fragment() {

    private var f1View: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        f1View = inflater.inflate(R.layout.fragment_1, container, false)
        resetImageView()
        //intent handler for communicating with fragment 2. when new contact info is saved in fragment2, fragment 1 is notified to refresh the QR Code
        LocalBroadcastManager.getInstance(this.requireActivity()).registerReceiver(receiver,
            IntentFilter("update-qr-code-in-fragment1")
        );
        return f1View
    }

    // This will be called whenever an Intent with an action named "update-qr-code-in-fragment1" is broadcast.
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            //val message = intent.getStringExtra("message")
            resetImageView()
        }
    }

    fun resetImageView() {
        var qCodeImage = f1View?.findViewById<ImageView>(R.id.imageViewQrCode)
        var qCode = QrCode()
        var mySharedPreferences = CustomSharedPreferences()
        mySharedPreferences.setFragmentActivity(this.requireActivity())
        val contact = qCode.vCardGenerator(mySharedPreferences)
        Log.d("Contact: ", contact.toString())

        qCodeImage!!.setImageBitmap(qCode.bitMatrix2BitMap(contact, 250, 250))
    }
}
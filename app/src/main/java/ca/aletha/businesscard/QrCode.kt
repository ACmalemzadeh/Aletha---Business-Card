package ca.aletha.businesscard


import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode
import java.util.stream.IntStream


class QrCode {

    fun vCardGenerator(mySharedPreferences : CustomSharedPreferences): String {
        return "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +

                "FN:" + mySharedPreferences.sharedPrefGetVal("name") + "\n" +

                "NOTE:" + mySharedPreferences.sharedPrefGetVal("name") + " - " + mySharedPreferences.sharedPrefGetVal("role") + " at Aletha.CA" + "\n" +

                "ADR:150 King Street West " +
                "Suite 805 - P.O. Box 35 " +
                "Toronto, ON M5H 1J9\n" +

                "TEL;TYPE=mobile:" + mySharedPreferences.sharedPrefGetVal("phone") + "\n" +
                "TEL;TYPE=work:+16473463000\n" +
                "EMAIL;TYPE=home:" + mySharedPreferences.sharedPrefGetVal("email") + "\n" +
                "EMAIL;TYPE=work:info@aletha.ca\n" +
                "URL:https://aletha.ca/\n" +

                "END:VCARD"
    }

    fun bitMatrix2BitMap(contact: String, w: Int, h: Int): Bitmap {
        val qrCodeWriter = QRCodeWriter()
        val bm : BitMatrix = qrCodeWriter.encode(contact, BarcodeFormat.QR_CODE, w, h)
        //convert bitmatrix to bitmap for ImageView readability
        return Bitmap.createBitmap(
            IntStream.range(0, h).flatMap { h ->
                IntStream.range(0, w).map { w ->
                    if (bm.get(
                            w,
                            h
                        )
                    ) Color.BLACK else Color.WHITE
                }
            }.toArray(),
            w, h, Bitmap.Config.ARGB_8888
        )
    }
}
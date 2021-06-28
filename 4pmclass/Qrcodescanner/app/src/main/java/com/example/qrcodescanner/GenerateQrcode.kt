package com.example.qrcodescanner

import android.graphics.Bitmap
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder

@Suppress("DEPRECATION")
class GenerateQrcode : AppCompatActivity() {
    lateinit var qrCodeIV: ImageView
    lateinit var dataEdt: EditText
    lateinit var generateQrBtn: Button
    var bitmap: Bitmap? = null
    var qrgEncoder: QRGEncoder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qrcode)
        // initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode)
        dataEdt = findViewById(R.id.idEdt)
        generateQrBtn = findViewById(R.id.idBtnGenerateQR)

        generateQrBtn.setOnClickListener {
            val datatext = dataEdt.getText().toString()
            if (TextUtils.isEmpty(datatext)) {
                Toast.makeText(
                    this@GenerateQrcode,
                    "Enter some text to generate QR Code",
                    Toast.LENGTH_SHORT
                ).show()
            }else
            {

                val manager = getSystemService(WINDOW_SERVICE) as WindowManager

                // initializing a variable for default display.
                val display = manager.defaultDisplay

                // creating a variable for point which
                // is to be displayed in QR Code.
                val point = Point()
                display.getSize(point)

                // getting width and
                // height of a point
                val width = point.x
                val height = point.y

                // generating dimension from width and height.
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4

                qrgEncoder =
                    QRGEncoder(datatext, null, QRGContents.Type.TEXT, dimen)
                bitmap = qrgEncoder!!.encodeAsBitmap()

                qrCodeIV.setImageBitmap(bitmap)

            }
        }
    }
}
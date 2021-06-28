package com.example.qrcodescanner

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.util.jar.Manifest

class QrCodeScancActivity : AppCompatActivity() {
    lateinit var textview: TextView
    lateinit var surfaceView: SurfaceView
    lateinit var openurl: Button
    var intendData = ""
    lateinit var barcodeDetector: BarcodeDetector
    lateinit var cameraSource: CameraSource
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_scanc)
        textview = findViewById(R.id.textbarcodevalue)
        surfaceView = findViewById(R.id.surfaceview)
        openurl = findViewById(R.id.btnaction)
        openurl.setOnClickListener {

            if (intendData.length > 0) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(intendData)))
            }
        }
    }
    override fun onResume() {
        super.onResume()
        initialiseDetectorAndSoruces()
    }

    private fun initialiseDetectorAndSoruces() {
        Toast.makeText(this, "BarCodeScnned started", Toast.LENGTH_SHORT).show()

        barcodeDetector = BarcodeDetector.Builder(this)
            .setBarcodeFormats(Barcode.ALL_FORMATS)
            .build()

        cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setRequestedPreviewSize(1920, 1080)
            .setAutoFocusEnabled(true)
            .build()
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {

                if (ActivityCompat.checkSelfPermission(applicationContext,android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                {

                    cameraSource.start(surfaceView!!.holder)
                }else
                {
                    ActivityCompat.requestPermissions(
                        this@QrCodeScancActivity, arrayOf(
                            android.Manifest.permission.CAMERA
                        ), REQUEST_CAMERA_PERMISSION
                    )
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }

        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode>{
            override fun release() {
                Toast.makeText(
                    this@QrCodeScancActivity,
                    "To prevent leaks barcode scanner has been stopped",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {

                val barcodes = detections!!.detectedItems
                if (barcodes.size() != 0) {

                    textview.post {
                        openurl.text = "LAUNCH URL"
                        intendData = barcodes.valueAt(0).displayValue
                        textview.text = intendData
                    }
                }else
                {
                    Toast.makeText(
                        this@QrCodeScancActivity,
                        "It is empty in size",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }
    override fun onPause() {
        super.onPause()
        cameraSource.release()
    }
    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 201
    }
}
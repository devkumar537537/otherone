package com.example.qrcodeinkotlin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Detector.Detections
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException


class BarCodeScannerActivity : AppCompatActivity() {
   lateinit var surfaceView: SurfaceView
   lateinit var texBArcodevalue: TextView
    lateinit var barcodeDetector: BarcodeDetector
    lateinit var cameraSource: CameraSource
    lateinit var btnaciton: Button
   lateinit var movetogenerate: Button
    var intendData = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code_scanner)
        bindview()
        btnaciton!!.setOnClickListener {
            if (intendData.length > 0) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(intendData)))
            }
        }
    }

    private fun bindview() {
        surfaceView = findViewById(R.id.surfaceview)
        btnaciton = findViewById(R.id.btnaction)
        texBArcodevalue = findViewById(R.id.textbarcodevalue)
        movetogenerate = findViewById(R.id.moveto_generate)
    }

    override fun onPause() {
        super.onPause()
        cameraSource.release()
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
        surfaceView!!.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                try {
                    if (ActivityCompat.checkSelfPermission(
                            this@BarCodeScannerActivity,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        cameraSource.start(surfaceView!!.holder)
                    } else {
                        ActivityCompat.requestPermissions(
                            this@BarCodeScannerActivity, arrayOf(
                                Manifest.permission.CAMERA
                            ), REQUEST_CAMERA_PERMISSION
                        )
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
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
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
                Toast.makeText(
                    this@BarCodeScannerActivity,
                    "To prevent leaks barcode scanner has been stopped",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun receiveDetections(detections: Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() != 0) {
                    texBArcodevalue.post {
                        btnaciton.text = "LAUNCH URL"
                        intendData = barcodes.valueAt(0).displayValue
                        texBArcodevalue.text = intendData
                    }
                } else {
                    Toast.makeText(
                        this@BarCodeScannerActivity,
                        "It is empty in size",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 201
    }
}
package com.example.qrcodescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateBarCode extends AppCompatActivity {

    private ImageView qrCodeIV;
    private EditText dataEdt;
    private Button generateQrBtn;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bar_code);

        // initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode);
        dataEdt = findViewById(R.id.idEdt);
        generateQrBtn = findViewById(R.id.idBtnGenerateQR);
        generateQrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datatext = dataEdt.getText().toString();
                if (TextUtils.isEmpty(datatext)) {


                    Toast.makeText(GenerateBarCode.this, "Enter some text to generate QR Code", Toast.LENGTH_SHORT).show();
                } else {

                    WindowManager manager = (WindowManager)  getSystemService(WINDOW_SERVICE);

                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);

                    int width = point.x;
                    int height = point.y;

                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;
               qrgEncoder = new QRGEncoder(datatext,null, QRGContents.Type.TEXT,dimen);
                    try {
                        bitmap = qrgEncoder.encodeAsBitmap();

                        qrCodeIV.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
    }
}
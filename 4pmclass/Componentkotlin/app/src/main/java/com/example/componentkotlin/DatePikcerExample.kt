package com.example.componentkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView

class DatePikcerExample : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var pickbtn:Button
    lateinit var datePicker: DatePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_pikcer_example)

        textView = findViewById(R.id.datetextveiw)
        pickbtn = findViewById(R.id.pickdatebtn)
        datePicker = findViewById(R.id.datapicker)

        textView.text = "Current Date : ${getCurrentDate()}"

        pickbtn.setOnClickListener {
            textView.text = "Current Date : ${getCurrentDate()}"
        }
    }

    private fun getCurrentDate(): String {
var stringBuilder  = StringBuilder()
        stringBuilder.append("${datePicker.month+1}/ ${datePicker.dayOfMonth} / ${datePicker.year}")

        return stringBuilder.toString()
    }
}
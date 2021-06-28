package com.example.contentprovider

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    val REQUEST_READ_CONTACTS = 79
    lateinit var progressBar: ProgressBar
    lateinit var nameArray: HashMap<Int, String>
   lateinit var numberArray: HashMap<Int, String>
   lateinit var textView: TextView
    private val mColumnProjection = arrayOf(
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.Contacts._ID,
        ContactsContract.Contacts.HAS_PHONE_NUMBER
    )
    var permissions = arrayOf(Manifest.permission.READ_CONTACTS)
    var requestcodes = 123
    var res = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectxml()
        nameArray = HashMap<Int,String>()
        numberArray = HashMap<Int,String>()
        if (res) {
            getPermissons()
        } else {
            getname()
        }


    }

    private fun connectxml() {
        progressBar = findViewById(R.id.progressbar)
        textView = findViewById(R.id.textcontact)
    }

    private fun getPermissons() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED
            ) {
                getname()
            } else {
                requestPermissions(permissions, requestcodes)
            }
        }
    }

    private fun getname() {
        nameArray = getAllContacts()

        var stringBuilder = StringBuilder()
        for (i in 1..nameArray!!.size) {
            stringBuilder.append("Name => ${nameArray.get(i)} \n")
            stringBuilder.append( "Number => ${numberArray.get(i) } \n\n")
        }
        progressBar!!.visibility = View.GONE
        textView!!.text = stringBuilder.toString()
    }


private fun getAllContacts(): java.util.HashMap<Int, String> {
    val nameList = HashMap<Int, String>()
    var count = 0
    val cr = contentResolver
    val cur = cr.query(
        ContactsContract.Contacts.CONTENT_URI, mColumnProjection,
        null, null, null
    )
    if (cur?.count ?: 0 > 0) {
        while (cur != null && cur.moveToNext()) {
            count++
            val name = cur.getString(0)
            val id = cur.getString(1)
            val status = cur.getString(2).toInt()
            nameList[count] = name
            if (status > 0) {
                val pCur = cr.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                    arrayOf(id),
                    null
                )
                while (pCur!!.moveToNext()) {
                    val phoneNo = pCur.getString(
                        pCur.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                        )
                    )
                    numberArray!![count] = phoneNo
                }
                pCur.close()
            }
        }
    }
    cur!!.close()
    return nameList
}
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestcodes && grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            res = false
            getname()
        } else {
            res = true
            getPermissons()
        }
    }
}
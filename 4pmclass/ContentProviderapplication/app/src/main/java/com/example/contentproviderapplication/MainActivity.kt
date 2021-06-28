package com.example.contentproviderapplication

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_READ_CONTACTS = 79
    var progressBar: ProgressBar? = null
    var nameArray: HashMap<Int, String>? = null
    var numberArray: HashMap<Int, String>? = null
    var textView: TextView? = null
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
        connextxml()
        nameArray = HashMap()
        numberArray = HashMap()
        if (res) {
            getPermissons()
        } else {
            getname()
        }

    }

    private fun connextxml() {
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
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

    private fun getname() {
        nameArray = getAllContacts()
        val stringBuilder = StringBuilder()
        for (i in 1..nameArray!!.size) {
            stringBuilder.append(
                """Name => ${nameArray!![i]} 
"""
            )
            stringBuilder.append(
                """
                Number => ${numberArray!![i]}
                
                
                """.trimIndent()
            )
        }
        progressBar!!.visibility = View.GONE
        textView!!.text = stringBuilder.toString()
    }

    private fun getAllContacts(): HashMap<Int, String> {
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


}
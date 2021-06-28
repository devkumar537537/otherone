package com.example.contentproviderapplication

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telecom.TelecomManager
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.contentproviderapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sendemail.setOnClickListener(View.OnClickListener {
            val email_text: String = receiveremail.getText().toString().trim { it <= ' ' }
            val subject_text: String = subjecttext.getText().toString().trim { it <= ' ' }
            val message_text: String = mssage.getText().toString().trim { it <= ' ' }
            val email = Intent(Intent.ACTION_SEND)
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf(email_text))
            email.putExtra(Intent.EXTRA_SUBJECT, subject_text)
            email.putExtra(Intent.EXTRA_TEXT, message_text)
            email.type = "message/rfc822"
            startActivity(Intent.createChooser(email, "Choose an Email client :"))
        })

        sendsmsbtn.setOnClickListener(View.OnClickListener {
            val number_text: String = numberedit.getText().toString().trim { it <= ' ' }
            val message_text: String = messageedit.getText().toString().trim { it <= ' ' }
            val smsintent = Intent(
                getApplicationContext(),
                com.example.androitelephonymanager.SmsActivity::class.java
            )
            val pendingIntent =
                PendingIntent.getActivity(getApplicationContext(), SMS_REQUEST, smsintent, 0)
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(number_text, null, message_text, pendingIntent, null)
            Toast.makeText(
                getApplicationContext(),
                "Messages Send Successfully",
                Toast.LENGTH_SHORT
            ).show()
        })

        callbtn.setOnClickListener(View.OnClickListener {
            val number: String = numbertext.getText().toString().trim { it <= ' ' }
            //  String numberr = "7009125438";
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(perm, CALL_REQUEST)
            } else {
                val telecomManager =
                    getApplicationContext().getSystemService(Context.TELECOM_SERVICE) as TelecomManager
                val phoneAccountHandleList = telecomManager.callCapablePhoneAccounts
                val callintent = Intent(Intent.ACTION_CALL)
                callintent.data = Uri.parse("tel:$number")
                callintent.putExtra("simSlot", 1)
                //                    i.putExtra("com.android.phone.extra.slot", SimIndex)
                startActivity(callintent)
            }
        })
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
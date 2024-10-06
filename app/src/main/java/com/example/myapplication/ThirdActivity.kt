package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityThirdBinding

class ThirdActivity : ComponentActivity() {
    private lateinit var binding: ActivityThirdBinding
    private var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the phone number from the intent
        phoneNumber = intent.getStringExtra("phone")
        binding.textViewPhoneNumber.text = phoneNumber

        // Set onClickListener for the call button
        binding.buttonCall.setOnClickListener {
            if (!phoneNumber.isNullOrEmpty()) {
                makePhoneCall(phoneNumber!!)
            } else {
                Toast.makeText(this, "Phone number is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun makePhoneCall(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "No app available to handle this action", Toast.LENGTH_SHORT).show()
        }
    }
}

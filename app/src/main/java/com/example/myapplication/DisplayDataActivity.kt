package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.myapplication.databinding.ActivityDisplayDataBinding

class DisplayDataActivity : ComponentActivity() {
    private lateinit var binding: ActivityDisplayDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from the intent
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val age = intent.getStringExtra("age")
        val expertise = intent.getStringExtra("expertise")
        val phone = intent.getStringExtra("phone")

        // Display the retrieved data
        """
                First Name: $firstName
                Last Name: $lastName
                Age: $age
                Expertise: $expertise
                Phone: $phone
            """.trimIndent().also { binding.textViewData.text = it }

        // Set button listeners
        binding.buttonOk.setOnClickListener {
            // Launch a third activity with the phone number
            val intent = Intent(this, ThirdActivity::class.java)
            intent.putExtra("phone", phone)
            startActivity(intent)
        }


        binding.buttonBack.setOnClickListener {
            // Go back to the previous activity
            finish()
        }
    }
}

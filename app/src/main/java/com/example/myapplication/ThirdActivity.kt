package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity

class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third) // Assuming you have an XML layout for this
    }
}

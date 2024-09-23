package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val typedText = "${binding.editText.text} ${binding.editText2.text} ${binding.editText3.text} ${binding.editText4.text} ${binding.editText5.text}"
            binding.textView.text = typedText
        }


        binding.button.setOnClickListener {
            if (validateInputs().isEmpty()) {
                val typedText = gatherInput()
                displayResult(typedText)
            } else {
                Toast.makeText(this, "Please fill in all fields\n" + validateInputs(), Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun gatherInput(): String {
        return "${binding.editText.text} ${binding.editText2.text} ${binding.editText3.text} ${binding.editText4.text} ${binding.editText5.text}"
    }

    private fun displayResult(result: String) {
        binding.textView.text = result
    }

    private fun validateInputs(): String {
        if (binding.editText.text.isBlank()) {
            return "please enter info into field: " + binding.editText.hint.toString()
        }
        if (binding.editText2.text.isBlank()) {
            return "please enter info into field: " + binding.editText2.hint.toString()
        }
        if (binding.editText3.text.isBlank()) {
            return "please enter info into field: " + binding.editText3.hint.toString()
        }
        if (binding.editText4.text.isBlank()) {
            return "please enter info into field: " + binding.editText4.hint.toString()
        }
        if (binding.editText5.text.isBlank()) {
            return "please enter info into field: " + binding.editText5.hint.toString()
        }
        return "";
    }


}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android test")
    }
}
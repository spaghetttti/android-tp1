package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.Locale

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setEditTextHints()

        binding.buttonSwitchLanguage.setOnClickListener {
            switchLanguage()
        }

        binding.validateButton.setOnClickListener {
            val validationMessage = validateInputs()
            if (validationMessage.isEmpty()) {
                showConfirmationDialog()
            } else {
                Toast.makeText(this, "Please fill in all fields\n$validationMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setEditTextHints() {
        binding.firstName.hint = getString(R.string.hint_first_name)
        binding.lastName.hint = getString(R.string.hint_last_name)
        binding.age.hint = getString(R.string.hint_age)
        binding.areaOfExpertise.hint = getString(R.string.hint_domain)
        binding.phoneNumber.hint = getString(R.string.hint_phone_number)
    }

    private fun gatherInput(): String {
        return listOf(
            binding.firstName.text.toString(),
            binding.lastName.text.toString(),
            binding.age.text.toString(),
            binding.areaOfExpertise.text.toString(),
            binding.phoneNumber.text.toString()
        ).joinToString(" ")
    }

    private fun validateInputs(): String {
        val fields = listOf(
            binding.firstName to getString(R.string.hint_first_name),
            binding.lastName to getString(R.string.hint_last_name),
            binding.age to getString(R.string.hint_age),
            binding.areaOfExpertise to getString(R.string.hint_domain),
            binding.phoneNumber to getString(R.string.hint_phone_number)
        )

        for ((editText, hint) in fields) {
            if (editText.text.isBlank()) {
                return getString(R.string.please_enter_info, hint)
            }
        }
        return ""
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Confirmation")
            .setMessage("Are you sure you want to submit?")
            .setPositiveButton("Yes") { _, _ ->
                // Launch the new activity with data
                val intent = Intent(this, DisplayDataActivity::class.java)
                intent.putExtra("firstName", binding.firstName.text.toString())
                intent.putExtra("lastName", binding.lastName.text.toString())
                intent.putExtra("age", binding.age.text.toString())
                intent.putExtra("expertise", binding.areaOfExpertise.text.toString())
                intent.putExtra("phone", binding.phoneNumber.text.toString())
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun switchLanguage() {
        val currentLocale = resources.configuration.locales[0].language
        val newLocale = if (currentLocale == "fr") "en" else "fr"

        val config = resources.configuration
        val locale = Locale(newLocale)
        Locale.setDefault(locale)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Restart activity to apply changes
        recreate()
    }
}

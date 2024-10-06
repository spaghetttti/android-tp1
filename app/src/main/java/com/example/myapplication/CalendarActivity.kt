package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityCalendarBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class CalendarActivity : ComponentActivity() {
    private lateinit var binding: ActivityCalendarBinding
    private val events = mutableListOf<Event>()
    private lateinit var eventAdapter: EventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year" // Month is 0-based
            binding.textViewEventDetails.text = "Selected date: $selectedDate"
        }

        binding.buttonAddEvent.setOnClickListener {
            showAddEventDialog()
        }
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter(events)
        binding.recyclerViewEvents.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEvents.adapter = eventAdapter
    }

    private fun showAddEventDialog() {
        val builder = MaterialAlertDialogBuilder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_add_event, null)
        builder.setView(dialogView)

        val editTextEventName = dialogView.findViewById<EditText>(R.id.editTextEventName)
        val editTextEventDetails = dialogView.findViewById<EditText>(R.id.editTextEventDetails)

        builder.setTitle("Add Event")
        builder.setPositiveButton("Add") { dialog, _ ->
            val eventName = editTextEventName.text.toString()
            val eventDetails = editTextEventDetails.text.toString()
            val date = binding.textViewEventDetails.text.toString().substringAfter(": ")

            if (eventName.isNotBlank() && eventDetails.isNotBlank() && date.isNotBlank()) {
                val event = Event(date, eventName, eventDetails)
                events.add(event) // Add event to the list
                eventAdapter.notifyItemInserted(events.size - 1) // Notify adapter
                Toast.makeText(this, "Event added!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }

            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
        builder.show()
    }
}

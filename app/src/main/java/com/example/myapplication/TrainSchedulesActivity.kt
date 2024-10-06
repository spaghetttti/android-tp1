package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityTrainSchedulesBinding

class TrainSchedulesActivity : ComponentActivity() {
    private lateinit var binding: ActivityTrainSchedulesBinding
    private lateinit var adapter: ScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainSchedulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.buttonSearch.setOnClickListener {
            val departureCity = binding.editTextDepartureCity.text.toString()
            val arrivalCity = binding.editTextArrivalCity.text.toString()

            if (departureCity.isBlank() || arrivalCity.isBlank()) {
            } else {
                val schedules = getMockSchedules(departureCity, arrivalCity)
                adapter.submitList(schedules)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = ScheduleAdapter()
        binding.recyclerViewSchedules.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSchedules.adapter = adapter
    }

    private fun getMockSchedules(departure: String, arrival: String): List<Schedule> {
        return listOf(
            Schedule("$departure to $arrival", "08:00 AM"),
            Schedule("$departure to $arrival", "10:30 AM"),
            Schedule("$departure to $arrival", "01:00 PM"),
            Schedule("$departure to $arrival", "03:45 PM")
        )
    }
}

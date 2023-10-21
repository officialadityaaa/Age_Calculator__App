package com.example.myapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val k = findViewById<Button>(R.id.button1)

        k.setOnClickListener { view ->
            clickDate(view)
        }
    }

    fun clickDate(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                val dateTextView = findViewById<TextView>(R.id.data1)
                dateTextView.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedData = sdf.parse(selectedDate)
                val selectedDataTime = selectedData!!.time / 60000

                val currentTime = System.currentTimeMillis()
                val currentTimeTime = sdf.parse(sdf.format(currentTime))!!.time / 60000

                val ageInMinutes = currentTimeTime - selectedDataTime

                val minutesTextView = findViewById<TextView>(R.id.minutes)
                minutesTextView.text = ageInMinutes.toString()

                val ageInYears = (ageInMinutes / (60 * 24 * 365.25)).toInt()
                val yearsTextView = findViewById<TextView>(R.id.year)
                yearsTextView.text = ageInYears.toString()+ "Years"
            },
            year, month, day
        ).show()
    }
}

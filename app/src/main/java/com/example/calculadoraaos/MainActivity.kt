package com.example.calculadoraaos

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedData)
        tvMinutes = findViewById(R.id.tvMinutes)

        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        var dpd = DatePickerDialog(this, {_, year, month, dayOfMonth ->

            Toast.makeText( this, "Hola", Toast.LENGTH_SHORT).show()

            var selectedDate = "$dayOfMonth/${month+1}/$year"
            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy")

            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedDatesInMinutes = theDate.time / 86400000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateInMinutes = currentDate.time / 86400000

                    val differenceInMinutes = currentDateInMinutes - selectedDatesInMinutes

                    tvMinutes?.text = differenceInMinutes.toString()
                }
            }

        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}
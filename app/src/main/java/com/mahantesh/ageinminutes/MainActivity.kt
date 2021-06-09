package com.mahantesh.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener {
                    datePicker, i, i2, i3 ->
                Toast.makeText(this, "Date selected $i, / $i2 / $i3", Toast.LENGTH_SHORT).show()

                val selectedDate = "$i3/${i2+1}/$i"
                tvSelectedDate.setText(selectedDate)

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate)

                val selectedDateInMinutes = date!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateInMinutes = currentDate!!.time / 60000
                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())

                val differenceInHours = differenceInMinutes / 60
                tvSelectedDateInHours.setText(differenceInHours.toString())

                val differenceInDays = differenceInMinutes / (60 *24)
                tvSelectedDateInDays.setText(differenceInDays.toString())

            }, year, month, day)
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}
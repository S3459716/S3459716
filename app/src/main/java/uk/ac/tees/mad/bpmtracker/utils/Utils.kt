package uk.ac.tees.mad.bpmtracker.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {
    fun convertMillisToDate(millis: Long): String {
        val pattern = "dd-MM-yyyy"
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        val date = Date(millis)
        return formatter.format(date)
    }

}
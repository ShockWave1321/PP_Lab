package Probleme

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun String.StringToDate(dateFormat: DateFormat) : Date
{
    return dateFormat.parse(this)
}


fun main()
{
    println("21-03-2002".StringToDate(SimpleDateFormat("dd-MM-yyyy")))
}
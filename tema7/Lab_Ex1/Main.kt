package com.pp.laborator

import java.io.File

class SyslogRecord(
    val TimeStamp:String,
    val Hostname:String,
    val AplicationName:String,
    val PID:Int? = null,
    val LogEntry:String
)
{
    fun print()
    {
        if(PID != null)
            println("Record: ${this.TimeStamp} ${this.Hostname} ${this.AplicationName} ${this.PID}: ${this.LogEntry}")
        else
            println("Record: ${this.TimeStamp} ${this.Hostname} ${this.AplicationName}: ${this.LogEntry}")
    }
}
fun main()
{
    val file = File("Syslog")
    val map = mutableMapOf<String,MutableList<SyslogRecord>>()
    file.useLines { lines ->
        lines.forEach { line ->
            val parts = line.split(" ", limit = 5)
            val timestamp = parts[0] + parts[1] + " " + parts[2] + " " + parts[3]
            val (hostname, appPid, logEntry) = parts[4].split(" ", limit = 3)
            val record:SyslogRecord

            if(appPid.contains('['))
            {
                val (app, pidStr) = appPid.split("[")
                val pid = pidStr.dropLast(2).toIntOrNull()
                record = SyslogRecord(timestamp, hostname, app, pid, logEntry)
            }
            else
            {
                record = SyslogRecord(timestamp, hostname, appPid.dropLast(1), null, logEntry)
            }
            if (!map.containsKey(record.AplicationName)) {
                map[record.AplicationName] = mutableListOf(record)
            } else {
                map[record.AplicationName]?.add(record)
            }
        }

    }
    map.values.forEach{ record ->
        record.sortBy { it.LogEntry }
    }
    map.values.forEach { record ->
        record.forEach{
            it.print()
        }
    }
    println()
    map.forEach{(app,records)->
        val filteredRecords = records.filter{it.PID!=null}
        for (filteredRecord in filteredRecords)
        {
            filteredRecord.print()
        }
    }
}


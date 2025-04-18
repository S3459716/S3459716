package uk.ac.tees.mad.bpmtracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val firebaseId:String = "",
    val name:String = "",
    val bpm:Int = 0,
    val time:Long = System.currentTimeMillis()
)

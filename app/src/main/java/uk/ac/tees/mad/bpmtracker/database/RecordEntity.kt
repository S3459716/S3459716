package uk.ac.tees.mad.bpmtracker.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "record_table")
data class RecordEntity(
    @PrimaryKey val firebaseId:String="",
    val userId:String,
    val name:String,
    val bpm:Int = 0,
    val time:Long = System.currentTimeMillis()
)

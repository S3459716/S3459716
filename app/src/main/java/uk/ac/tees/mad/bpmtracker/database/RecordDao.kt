package uk.ac.tees.mad.bpmtracker.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(entity: RecordEntity)

    @Query("SELECT * FROM record_table")
    fun getAllRecords():Flow<List<RecordEntity>>

    @Delete
    suspend fun deleteRecord(entity: RecordEntity)
}
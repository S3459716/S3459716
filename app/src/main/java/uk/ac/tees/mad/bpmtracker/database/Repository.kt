package uk.ac.tees.mad.bpmtracker.database

import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun addRecord(entity: RecordEntity):Int
    fun getAllRecords(): Flow<List<RecordEntity>>
    suspend fun deleteRecord(entity: RecordEntity)
}
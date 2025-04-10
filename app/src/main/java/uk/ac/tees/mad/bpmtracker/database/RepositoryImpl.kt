package uk.ac.tees.mad.bpmtracker.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: RecordDao
):Repository {
    override suspend fun addRecord(entity: RecordEntity):Long {
        return dao.addRecord(entity)
    }

    override fun getAllRecords(): Flow<List<RecordEntity>> {
        return dao.getAllRecords()
    }

    override suspend fun deleteRecord(entity: RecordEntity) {
        dao.deleteRecord(entity)
    }
}
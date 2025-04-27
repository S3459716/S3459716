package uk.ac.tees.mad.bpmtracker.database

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: RecordDao
):Repository {
    override suspend fun addRecord(entity: RecordEntity) {
        dao.addRecord(entity)
    }

    override fun getAllRecords(userId:String): Flow<List<RecordEntity>> {
        return dao.getAllRecords(userId)
    }

    override suspend fun deleteRecord(entity: RecordEntity) {
        dao.deleteRecord(entity)
    }
}
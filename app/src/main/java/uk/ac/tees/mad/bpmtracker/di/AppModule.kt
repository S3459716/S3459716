package uk.ac.tees.mad.bpmtracker.di

import android.content.Context
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.bpmtracker.database.RecordDao
import uk.ac.tees.mad.bpmtracker.database.RecordDatabase
import uk.ac.tees.mad.bpmtracker.database.Repository
import uk.ac.tees.mad.bpmtracker.database.RepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):RecordDatabase{
        return RecordDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideRecordDao(db:RecordDatabase):RecordDao{
        return db.recordDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao:RecordDao):Repository{
        return RepositoryImpl(dao)
    }
}
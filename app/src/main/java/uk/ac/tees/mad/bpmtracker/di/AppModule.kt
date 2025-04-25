package uk.ac.tees.mad.bpmtracker.di

import android.content.Context
import com.cloudinary.Cloudinary
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
    fun provideCloudinary():Cloudinary{
        val config = HashMap<String,String>().apply {
            put("cloud_name", "dtbkjek0w")
            put("api_key", "918463645369112")
            put("api_secret", "8MFlQWUQn2ZvxAadost4kI3ieXM")
        }

        return Cloudinary(config)
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
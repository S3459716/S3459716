package uk.ac.tees.mad.bpmtracker.screen.home

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uk.ac.tees.mad.bpmtracker.database.RecordEntity
import uk.ac.tees.mad.bpmtracker.database.Repository
import uk.ac.tees.mad.bpmtracker.utils.Constants.RECORDS
import uk.ac.tees.mad.bpmtracker.utils.Constants.USER
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val repository: Repository
):ViewModel() {
    private val userId = auth.currentUser?.uid?:""

    private val _recordList = MutableStateFlow<List<RecordEntity>>(emptyList())
    val recordList:StateFlow<List<RecordEntity>> get() = _recordList

    init {
        syncData()
    }

    fun saveRecord(name:String, bpm:Int, context: Context){
        viewModelScope.launch {
            val newRecord = RecordEntity(name = name, bpm = bpm)
            val id = repository.addRecord(entity = newRecord)
            if (name.isNotEmpty()) {
                db.collection(USER)
                    .document(userId)
                    .collection(RECORDS)
                    .add(newRecord.copy(id = id))
                    .addOnSuccessListener {
                        Toast.makeText(context,"Record added successfully", Toast.LENGTH_SHORT).show()
                    }
            }
            else{
                Toast.makeText(context,"Name required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun syncData(){
        viewModelScope.launch {
            repository.getAllRecords().collect{
                _recordList.value = it
                if (_recordList.value.isEmpty()){
                    db.collection(USER)
                        .document(userId)
                        .collection(RECORDS)
                        .get()
                        .addOnSuccessListener { documents->
                            val mRecords = documents.mapNotNull { doc->
                                doc.toObject(RecordEntity::class.java)
                            }
                            viewModelScope.launch {
                                mRecords.forEach { record->
                                    repository.addRecord(record)
                                }
                            }
                        }
                }
            }
        }
    }
}
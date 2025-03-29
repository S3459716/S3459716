package uk.ac.tees.mad.bpmtracker.screen.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import uk.ac.tees.mad.bpmtracker.database.RecordEntity
import uk.ac.tees.mad.bpmtracker.utils.Constants.RECORDS
import uk.ac.tees.mad.bpmtracker.utils.Constants.USER
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
):ViewModel() {
    private val userId = auth.currentUser?.uid?:""
    fun saveRecord(name:String, bpm:Int, context: Context){
        val newRecord = RecordEntity(name = name, bpm = bpm)
        if (name.isNotEmpty()) {
            db.collection(USER)
                .document(userId)
                .collection(RECORDS)
                .add(newRecord)
                .addOnSuccessListener {
                    Toast.makeText(context,"Record added successfully", Toast.LENGTH_SHORT).show()
                }
        }
        else{
            Toast.makeText(context,"Name required", Toast.LENGTH_SHORT).show()
        }
    }
}
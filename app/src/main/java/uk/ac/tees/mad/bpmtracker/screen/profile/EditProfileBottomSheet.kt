package uk.ac.tees.mad.bpmtracker.screen.profile

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import uk.ac.tees.mad.bpmtracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileBottomSheet(
    currentName: String,
    uri:Uri?,
    onSave: (String) -> Unit,
    onImageClick:()->Unit,
    onDismiss: () -> Unit
) {
    var newName by remember { mutableStateOf(currentName) }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Edit Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = uri,
                contentDescription = "Weather Image",
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.dp, color = Color.Gray, shape = CircleShape)
                    .clickable {
                        onImageClick()
                    }
                ,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profile_pic),
                error = painterResource(id = R.drawable.profile_pic)
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = newName,
                onValueChange = { newName = it },
                label = { Text("Enter new name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onSave(newName) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
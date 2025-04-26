package uk.ac.tees.mad.bpmtracker.screen.profile

import android.Manifest
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import uk.ac.tees.mad.bpmtracker.R
import uk.ac.tees.mad.bpmtracker.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    var isEditing by remember{ mutableStateOf(false) }
    val imageUri by viewModel.imageUri.collectAsState()
    var uri by remember { mutableStateOf<Uri?>(null) }
    val hasPermission by viewModel.hasCameraPermission.collectAsState()
    val context = LocalContext.current

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            viewModel.checkCameraPermission(context)
            if (!isGranted) {
                Toast.makeText(context, "Camera permission is required!", Toast.LENGTH_SHORT).show()
            }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                uri?.let {
                    viewModel.onPicSelected(true)
                }
                Toast.makeText(context, "Picture Captured", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        viewModel.checkCameraPermission(context)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box{
            Box(
                modifier = Modifier.padding(bottom = 39.dp)
            ){
                Image(
                    painter = painterResource(R.drawable.auth_bg),
                    contentDescription = "null",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                        .height(260.dp)
                )
                Text("Profile",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier  = Modifier.padding(top = 28.dp, start = 16.dp)
                )
                IconButton({isEditing = true},
                    modifier = Modifier
                        .padding(16.dp)
                        .border(1.dp,color = Color.White, shape = RoundedCornerShape(12.dp))
                        .align(Alignment.BottomEnd)
                    ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "edit icon",
                        tint = Color.White
                    )
                }
            }
            AsyncImage(
                model = imageUri,
                contentDescription = "Weather Image",
                modifier = Modifier
                    .size(78.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.BottomCenter),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.profile_pic),
                error = painterResource(id = R.drawable.profile_pic)
            )
        }
        Text(viewModel.name?:"Name",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(viewModel.email?:"",
            fontSize = 14.sp,
        )

        TextButton({},
            colors = ButtonDefaults.buttonColors(),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
                .fillMaxWidth()
            ) {
            Text("Log out",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
                )
        }
    }

    if (isEditing){
        EditProfileBottomSheet(
            currentName = viewModel.name?:"",
            uri = uri,
            onSave = {
                uri?.let {
                    it1 -> viewModel.setImageUri1(it1)
                }
                viewModel.updateProfile(it,context)
                isEditing = false
            },
            onImageClick = {
                if (hasPermission) {
                    uri = viewModel.generateTempUri()
                    cameraLauncher.launch(uri!!)
                } else {
                    permissionLauncher.launch(Manifest.permission.CAMERA)
                }
            }
        ) {
            isEditing = false
        }
    }
}
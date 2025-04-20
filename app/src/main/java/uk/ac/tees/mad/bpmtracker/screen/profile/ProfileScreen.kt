package uk.ac.tees.mad.bpmtracker.screen.profile

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.bpmtracker.R
import uk.ac.tees.mad.bpmtracker.ui.theme.BPMTrackerTheme

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var isEditing by remember{ mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
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
                    modifier  = Modifier.padding(top = 24.dp, start = 16.dp)
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
            Image(
                painter = painterResource(R.drawable.profile_pic),
                contentDescription = "profile_pic",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(78.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .align(Alignment.BottomCenter)
            )
        }
        Text("Name",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text("test12@gmail.com",
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
            currentName = "Name",
            uri = null,
            onSave = {},
            onImageClick = {}
        ) {
            isEditing = false
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePrev() {
    BPMTrackerTheme {
        ProfileScreen()
    }
}
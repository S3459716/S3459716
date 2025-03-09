package uk.ac.tees.mad.bpmtracker.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import uk.ac.tees.mad.bpmtracker.R

@Composable
fun SplashScreen() {
    var showIcon by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        showIcon = true
        delay(2000)
        showIcon = false
        delay(1000)
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        AnimatedVisibility(showIcon) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.app_icon),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(44.dp)
                )
                Text("Track the Beats",
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 3.dp)
                )
            }
        }
    }
}

// preview
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}
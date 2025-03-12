package uk.ac.tees.mad.bpmtracker.screen.splash

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
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import uk.ac.tees.mad.bpmtracker.R
import uk.ac.tees.mad.bpmtracker.utils.Constants

@Composable
fun SplashScreen(
    navController: NavController
) {
    var showIcon by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(500)
        showIcon = true
        delay(2000)
        showIcon = false
        delay(800)
        navController.navigate(Constants.AUTH_SCREEN){
            popUpTo(Constants.SPLASH_SCREEN){
                inclusive = true
            }
        }
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
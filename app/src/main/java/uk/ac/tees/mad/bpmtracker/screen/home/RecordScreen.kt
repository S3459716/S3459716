package uk.ac.tees.mad.bpmtracker.screen.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import uk.ac.tees.mad.bpmtracker.ui.theme.BPMTrackerTheme
import kotlin.math.max
import kotlin.math.min

@Composable
fun RecordScreen(
    modifier:Modifier = Modifier
) {
    var bpm by remember { mutableIntStateOf(0) }
    var progress by remember { mutableFloatStateOf(0.0f) }
    var recordName by remember { mutableStateOf("") }

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(200)
            progress = max(0f, progress - 0.05f)
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 20.dp)

            ) {
            CircularProgressIndicator(
                progress = { animatedProgress },
                trackColor = Color.Gray,
                strokeCap = StrokeCap.Round,
                modifier = Modifier.size(200.dp),
                color = Color.Blue,
                strokeWidth = 10.dp,
            )


            Text(
                text = "$bpm BPM",
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Spacer(Modifier.weight(1f))


        TextButton(
            onClick = {
                bpm += 1
                progress = min(1f, progress + 0.1f) // Increase progress},
            },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(),
            modifier = Modifier.size(220.dp)
        ) {
            Text("TAP", fontSize = 20.sp)
        }
        Spacer(Modifier.weight(1f))

        Button(
            onClick = {
                bpm = 0
                progress = 0f
            },
            shape = RoundedCornerShape(8.dp),
        ) {
            Text("Reset")
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = recordName,
                onValueChange = {recordName = it},
                placeholder = { Text("Record Name") },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
            )
            TextButton(onClick = {},
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(),
                modifier = Modifier.padding(start = 6.dp)
                    .height(55.dp)
                ) {
                Text("Save")
            }
        }
    }
}
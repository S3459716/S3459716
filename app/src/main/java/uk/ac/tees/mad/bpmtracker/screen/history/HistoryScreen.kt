package uk.ac.tees.mad.bpmtracker.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.bpmtracker.database.RecordEntity
import uk.ac.tees.mad.bpmtracker.ui.theme.BPMTrackerTheme

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val colors = listOf(
        Color(0xFFffd6ff),
        Color(0xFFe7c6ff),
        Color(0xFFc8b6ff),
        Color(0xFFb8c0ff),
        Color(0xFFbbd0ff)
    )
    val entity = RecordEntity(
        name = "Record 1",
        bpm = 172
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        color = Color(0xFF003f88)
                    )
            ){
                Text(
                    "History",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }

            LazyColumn {
                items(15){ index->
                    RecordItem(
                        colors[index%5],
                        entity
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HistoryPrev() {
    BPMTrackerTheme {
        HistoryScreen()
    }
}
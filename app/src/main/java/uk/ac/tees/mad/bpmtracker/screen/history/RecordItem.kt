package uk.ac.tees.mad.bpmtracker.screen.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.bpmtracker.database.RecordEntity
import uk.ac.tees.mad.bpmtracker.utils.Utils.convertMillisToDate

@Composable
fun RecordItem(
    bgColor: Color,
    record:RecordEntity
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = bgColor)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                record.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                "Date: ${convertMillisToDate(record.time)}",
                fontSize = 16.sp,
                color = Color(0xFF0466c8)
            )
        }
        Text(
            "BPM = ${record.bpm}",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF0466c8)
        )
    }
}

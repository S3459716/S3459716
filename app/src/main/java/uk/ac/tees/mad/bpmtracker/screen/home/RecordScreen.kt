package uk.ac.tees.mad.bpmtracker.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RecordScreen(modifier: Modifier = Modifier) {
    // box with full size with text in centre
    Box(contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()) {
            Text(text = "Record")
        }
}
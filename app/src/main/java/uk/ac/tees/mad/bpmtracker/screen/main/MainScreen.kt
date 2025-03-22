package uk.ac.tees.mad.bpmtracker.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.bpmtracker.screen.history.HistoryScreen
import uk.ac.tees.mad.bpmtracker.screen.home.RecordScreen
import uk.ac.tees.mad.bpmtracker.screen.profile.ProfileScreen

@Composable
fun MainScreen() {
    var selectedScreen by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            MainBottomBar(
                isSelected = selectedScreen,
                onClick = { selectedScreen = it }
            )
        }
    )
    {paddingValues->
        when (selectedScreen) {
            0->RecordScreen(modifier = Modifier.padding(paddingValues))
            1->HistoryScreen()
            2->ProfileScreen()
        }
    }
}
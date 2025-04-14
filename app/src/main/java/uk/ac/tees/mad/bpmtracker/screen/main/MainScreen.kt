package uk.ac.tees.mad.bpmtracker.screen.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import uk.ac.tees.mad.bpmtracker.screen.history.HistoryScreen
import uk.ac.tees.mad.bpmtracker.screen.home.RecordScreen
import uk.ac.tees.mad.bpmtracker.screen.home.RecordViewModel
import uk.ac.tees.mad.bpmtracker.screen.profile.ProfileScreen

@Composable
fun MainScreen() {
    val recordViewModel:RecordViewModel = hiltViewModel()
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
            0->RecordScreen(recordViewModel,modifier = Modifier.padding(paddingValues))
            1->HistoryScreen(recordViewModel, modifier = Modifier.padding(paddingValues))
            2->ProfileScreen(modifier = Modifier.padding(paddingValues))
        }
    }
}
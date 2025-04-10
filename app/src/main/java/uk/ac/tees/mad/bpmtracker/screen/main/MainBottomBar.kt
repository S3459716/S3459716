package uk.ac.tees.mad.bpmtracker.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import uk.ac.tees.mad.bpmtracker.R

@Composable
fun MainBottomBar(
    isSelected: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier,
        ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_bar_chart_24),
                    contentDescription = "bar_icon"
                )
            },
            label = {
                Text("Record")
            },
            selected = isSelected == 0,
            onClick = { onClick(0) },
            alwaysShowLabel = false
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_history_24),
                    contentDescription = "History"
                )
            },
            label = {
                Text("History")
            },
            selected = isSelected == 1,
            onClick = { onClick(1) },
            alwaysShowLabel = false
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "person_icon"
                )
            },
            label = {
                Text("Profile")
            },
            selected = isSelected == 2,
            onClick = { onClick(2) },
            alwaysShowLabel = false
        )
    }
}

package uk.ac.tees.mad.bpmtracker.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.bpmtracker.screen.auth.AuthScreen
import uk.ac.tees.mad.bpmtracker.screen.splash.SplashScreen
import uk.ac.tees.mad.bpmtracker.utils.Constants

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Constants.SPLASH_SCREEN) {
        composable(Constants.SPLASH_SCREEN) {
            SplashScreen(navController)
        }
        composable(Constants.AUTH_SCREEN) {
            AuthScreen()
        }
    }
}
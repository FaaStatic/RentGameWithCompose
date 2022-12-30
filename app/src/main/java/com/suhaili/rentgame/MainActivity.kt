package com.suhaili.rentgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.suhaili.rentgame.component.Components
import com.suhaili.rentgame.component.Rute
import com.suhaili.rentgame.screen.*
import com.suhaili.rentgame.ui.theme.RentGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navControl = rememberNavController()
    val navBack by navControl.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {
            if(navBack?.destination?.route != Rute.Detail.rute  &&  navBack?.destination?.route != Rute.SplashScreen.rute){
                Components.BottomBar(navControl = navControl)
            }
    }) {
        NavHost(navController = navControl,
            startDestination = Rute.SplashScreen.rute
        ){
            composable(Rute.SplashScreen.rute){
                SplashScreen(navCon = navControl)
            }
           composable(Rute.Home.rute){
                HomeScreen(navControl)
           }
           composable(Rute.Shop.rute){
               ShopScreen(navControl)
           }
           composable(Rute.About.rute){
                AboutScreen()
           }
           composable(Rute.Detail.rute, arguments = listOf(
               navArgument("type") {
                   type = NavType.StringType
                   nullable = true
               },
               navArgument("id") {
                   type = NavType.IntType
                   defaultValue = 0
               },
           )){
               val id = it.arguments?.getInt("id")
               val type = it.arguments?.getString("type")?: ""
               DetailScreen(navControl, id , type)
           }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RentGameTheme {
        MyApp()
    }
}
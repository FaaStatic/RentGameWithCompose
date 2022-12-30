package com.suhaili.rentgame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.suhaili.rentgame.R
import com.suhaili.rentgame.component.Rute
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.backgorund
import com.suhaili.rentgame.ui.theme.unselected
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navCon : NavHostController){
    LaunchedEffect(Unit) {
        delay(3000)
        navCon.navigate(Rute.Home.rute){
            popUpTo(Rute.SplashScreen.rute) {
                inclusive = true
            }
        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,modifier = Modifier
        .fillMaxSize()
        .background(color = backgorund)) {
        Image(painter = painterResource(id = R.drawable.ic_joypad), contentDescription ="Logo", modifier = Modifier.size(150.dp), contentScale = ContentScale.FillBounds )
        Text(text = "RentGame", fontSize = 30.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(top = 16.dp), color = unselected)
    }
}



@Preview(showBackground = true)
@Composable
fun TestingSplash(){
    RentGameTheme {
        SplashScreen(rememberNavController())
    }
}
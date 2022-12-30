package com.suhaili.rentgame.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.suhaili.rentgame.R
import com.suhaili.rentgame.component.Components
import com.suhaili.rentgame.component.Rute
import com.suhaili.rentgame.repository.RepositoryData
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.backgorund
import com.suhaili.rentgame.ui.theme.unselected


val rentItem = RepositoryData.consoleRental.filter { it.id < 6 }

val offerItem = RepositoryData.consoleOffer.filter { it.id < 6 }

@Composable
fun HomeScreen(navCon: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgorund)
            .padding(bottom = 55.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Image(
            painter = painterResource(id = R.drawable.cat_image),
            contentDescription = "Dummy Akun",
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(50.dp))
        )
//
        Text(
            text = "Popular Rent Product",
            fontSize = 30.sp,
            color = unselected,
            fontWeight = FontWeight(800),
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        ) {
            items(rentItem, key = { it.id }) { menu ->
                Components.listItem(model = menu, onCLick = {
                    navigateDetail(navCon = navCon,"rental", menu.id)
                })
            }
        }

        Text(
            text = "Popular Product",
            fontSize = 30.sp,
            color = unselected,
            fontWeight = FontWeight(800),
            modifier = Modifier.padding(start = 24.dp, top = 16.dp, end = 24.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        ) {
            items(offerItem, key = { it.id }) { menu ->
                Components.listItem(model = menu, onCLick = {
                    navigateDetail(navCon = navCon,"offer", menu.id)
                })
            }
        }
    }
}

fun navigateDetail(navCon: NavHostController, type : String, id:Int) {
    Log.d("TES", "HomeScreen: ")
    navCon.navigate(route = Rute.Detail.createRoute(type, id)) {
        popUpTo(navCon.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
        launchSingleTop = true
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DefaultPreview() {
    RentGameTheme {
        //
    }
}
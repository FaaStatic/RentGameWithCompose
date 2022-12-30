package com.suhaili.rentgame.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.suhaili.rentgame.component.Components
import com.suhaili.rentgame.component.Rute
import com.suhaili.rentgame.repository.RepositoryData
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.backgorund
import com.suhaili.rentgame.ui.theme.unselected


@Composable
fun ShopScreen(navCon: NavHostController) {
    val configuration = LocalConfiguration.current
    val ctx = LocalContext.current;
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .background(color = backgorund)
    ) {
        Text(
            text = "Our Rent Product",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            color = unselected
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
        ) {
            items(RepositoryData.consoleRental, key = { it.id }) { menu ->
                Components.listItem(model = menu, onCLick = {
                    navigateDetail(navCon = navCon, "rental", menu.id)
                })
            }
        }
        Text(
            text = "Our Product",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
            color = unselected
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.height(screenHeight),
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp)
        ) {
            items(RepositoryData.consoleOffer, key = { it.id }) {
                Components.listShop(model = it) {
                    navCon.navigate(Rute.Detail.createRoute("buy", it.id))
                }
            }
        }


    }

}


@Composable
@Preview(showSystemUi = true, showBackground = true)
fun testShop() {
    RentGameTheme() {
        ShopScreen(navCon = rememberNavController())
    }
}
package com.suhaili.rentgame.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.suhaili.rentgame.component.Components
import com.suhaili.rentgame.repository.RepositoryData
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.backgorund
import com.suhaili.rentgame.ui.theme.bgSecond
import com.suhaili.rentgame.ui.theme.selected


@Composable
fun DetailScreen(navCon: NavHostController, id: Int?, type: String?) {
    val configuration = LocalConfiguration.current
    val ctx = LocalContext.current;
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val getItems =
        if (type == "rental") RepositoryData.consoleRental.filter { it.id == id } else RepositoryData.consoleOffer.filter { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgorund)
            .verticalScroll(rememberScrollState())
    ) {
        Components.HeaderApp("") { navCon.navigateUp() }
        Text(
            text = getItems[0].name,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 24.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .height(screenHeight / 2)
            .fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Card(
                    modifier = Modifier
                        .padding(bottom = 24.dp)
                        .width(screenWidth / 3)
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp,
                    backgroundColor = bgSecond,
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(
                            text = "Harga",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Rp. ${getItems[0].price}",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .width(screenWidth / 3)
                        .height(100.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = 5.dp,
                    backgroundColor = bgSecond
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Unit",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "${getItems[0].unit} Unit",
                            fontSize = 18.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }


                }

            }
            Card(modifier = Modifier.width(screenWidth / 2).fillMaxHeight(), shape = RoundedCornerShape(8.dp)) {
                AsyncImage(
                    model = getItems[0].image,
                    contentDescription = getItems[0].image,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(8.dp)
                        ),
                    contentScale = ContentScale.FillHeight
                )
            }

        }
        
        Text(text = "Deskripsi Produk", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White, modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp))

        Text(text = "${getItems[0].description}", fontSize = 18.sp, fontWeight = FontWeight.Normal, color = Color.White, modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp))

        Button(  onClick = {
            Components.toastGlob(ctx,"Proses")
        }, shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(selected), modifier = Modifier.padding(16.dp).fillMaxWidth().height(45.dp)) {
            Text(text = if(type =="rental") "Rent" else "Buy", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }


    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun test() {
    val navHostControll = rememberNavController()
    RentGameTheme {
        DetailScreen(navHostControll, 1, "rental")

    }
}
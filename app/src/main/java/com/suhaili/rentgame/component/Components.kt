package com.suhaili.rentgame.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.suhaili.rentgame.R
import com.suhaili.rentgame.model.ConsoleModel
import com.suhaili.rentgame.model.NavItem
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.bgSecond
import com.suhaili.rentgame.ui.theme.selected
import com.suhaili.rentgame.ui.theme.unselected

object Components {

    @Composable
    fun BottomBar(
        navControl: NavHostController,
        modifier: Modifier = Modifier
    ) {
        val navBack by navControl.currentBackStackEntryAsState()
        val currentRoute = navBack?.destination?.route

        val navigationItem = listOf<NavItem>(
            NavItem(1, "Home", R.drawable.ic_home, Rute.Home,  "home_page"),
            NavItem(2, "Shop", R.drawable.ic_joystick, Rute.Shop,"shop_page"),
            NavItem(3, "About", R.drawable.ic_about, Rute.About,"about_page"),
        )

        BottomNavigation(
            backgroundColor = bgSecond,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))

        ) {
            navigationItem.map {
                BottomNavigationItem(selected = currentRoute == it.screen.rute,
                    onClick = {
                        navControl.navigate(it.screen.rute) {
                            popUpTo(navControl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }

                    }, icon = {
                        Icon(
                            painter = painterResource(id = it.icon), contentDescription = it.description,
                            modifier = Modifier.size(if (currentRoute == it.screen.rute) 25.dp else 20.dp),
                            tint = if (currentRoute == it.screen.rute) selected else unselected
                        )
                    }, label = {
                        Text(
                            text = it.title,
                            fontSize = if (currentRoute == it.screen.rute) 15.sp else 12.sp,
                            fontWeight = if (currentRoute == it.screen.rute) FontWeight.Bold else FontWeight.Normal,
                            color = if (currentRoute == it.screen.rute) selected else unselected,
                            modifier = Modifier.padding(top = 8.dp)
                        )

                    })
            }
        }


    }
    
    @Composable
    fun HeaderApp(text: String = "", onBack : () -> Unit = {}){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start ,
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .height(45.dp)) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = bgSecond),
                onClick = {onBack()},
                modifier = Modifier
                    .padding(start = 24.dp, end = 16.dp)
                    .size(40.dp),
                shape = RoundedCornerShape(8.dp)

            ){
                Icon(painter = painterResource(id = R.drawable.ic_back), tint = Color.White, contentDescription ="back", modifier = Modifier.size(35.dp) )
            }
            Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.White )
        }
    }

    fun toastGlob(ctx : Context, message : String){
        Toast.makeText(ctx,message,Toast.LENGTH_SHORT).show()
    }

    @Composable
    fun listItem(model: ConsoleModel, onCLick: () -> Unit) {
        Box(
            modifier = Modifier
                .clickable(enabled = true, onClick = { onCLick() })
                .width(250.dp)
                .height(500.dp)
                .padding(start = 16.dp)
        ) {
            Card(
                elevation = 4.dp,
                backgroundColor = bgSecond,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()) {
                    AsyncImage(
                        model = model.image, contentDescription = model.name, modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clip(
                                RoundedCornerShape(8.dp)
                            ),
                        contentScale = ContentScale.FillHeight
                    )
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color.Black.copy(alpha = 0.6f)))
                Column() {
                    Text(text = model.name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = selected, modifier = Modifier.padding(start = 16.dp, top = 16.dp))
                    Text(text = "Rp."+model.price, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = unselected, modifier = Modifier.padding(start = 16.dp, top = 16.dp))
                }


            }
        }
    }

    @Composable
    fun searchTextEdt() {
        var inputState by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = inputState, onValueChange = {
                inputState = it
            }, modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(top = 8.dp, bottom = 8.dp, start = 24.dp, end = 24.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color = bgSecond),
            enabled = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "search Icon",
                    tint = selected,
                    modifier = Modifier
                        .height(25.dp)
                        .width(25.dp)
                )
            }
        )
    }

    @Composable
    fun listShop(model: ConsoleModel, onCLick: () -> Unit){
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        Card(backgroundColor = bgSecond, shape = RoundedCornerShape(8.dp)
        , modifier = Modifier
                .clickable { onCLick() }
                .width(screenWidth / 4).padding(16.dp)
                .height(300.dp)) {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(model = model.image, contentDescription =model.name, modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(
                        RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                    ),
                contentScale = ContentScale.FillHeight)
                Text(text = model.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 16.dp, top = 8.dp), color = unselected)
                Text(text = "Rp."+model.price, fontSize = 14.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(start = 16.dp, top = 8.dp), color = unselected)
            }

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    RentGameTheme {

    }
}

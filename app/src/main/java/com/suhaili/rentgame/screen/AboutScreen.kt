package com.suhaili.rentgame.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.suhaili.rentgame.R
import com.suhaili.rentgame.ui.theme.RentGameTheme
import com.suhaili.rentgame.ui.theme.backgorund
import com.suhaili.rentgame.ui.theme.unselected

@Composable
fun AboutScreen(){
  Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center,modifier = Modifier
      .fillMaxSize()
      .background(backgorund)
      .verticalScroll(
          rememberScrollState()
      )) {
      Image(painter = painterResource(id = R.drawable.photo_fix), contentDescription ="Photo", modifier = Modifier
          .size(150.dp)
          .clip(
              RoundedCornerShape(75.dp)
          ),
      contentScale = ContentScale.Crop)
      Text(text = "Suhaili Faruq", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = unselected, modifier = Modifier.padding(16.dp))
      Text(text = "suhaili.faruq01@gmail.com", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = unselected,modifier = Modifier.padding(16.dp))
  }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun testing(){
    RentGameTheme {
        AboutScreen()

    }
}
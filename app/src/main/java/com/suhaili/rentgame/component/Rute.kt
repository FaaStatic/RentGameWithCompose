package com.suhaili.rentgame.component

sealed class Rute(val rute : String){
    object SplashScreen: Rute("splash")
    object Home: Rute("home")
    object Shop: Rute("shop")
    object About: Rute("about")
    object Detail: Rute("detail/{type}/{id}"){
        fun createRoute( type : String ,id : Int) = "detail/${type}/${id}"

    }
}

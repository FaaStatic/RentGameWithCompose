package com.suhaili.rentgame.model

import com.suhaili.rentgame.component.Rute

data class NavItem(
    val id: Int,
    val title: String,
    val icon: Int,
    val screen: Rute,
    val description : String
)

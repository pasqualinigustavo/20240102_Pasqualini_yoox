package com.yoox.items.navigation

import androidx.navigation.NavDirections

sealed class NavEvent {

    class ByDirections(val directions: NavDirections) : NavEvent()

}

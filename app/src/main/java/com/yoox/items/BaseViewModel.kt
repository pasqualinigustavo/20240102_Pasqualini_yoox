package com.yoox.items

import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.yoox.items.navigation.NavEvent
import com.yoox.items.navigation.YooxSharedFlow

abstract class BaseViewModel
constructor() :
    ViewModel() {

    internal val navigationFlow by lazy { YooxSharedFlow<NavEvent>() }

    open fun onAttached() {
        /*NO-OP*/
    }

    fun onDetach() {
        /*NO-OP*/
    }

    protected fun navigate(directions: NavDirections) {
        navigationFlow.tryEmit(NavEvent.ByDirections(directions))
    }
}

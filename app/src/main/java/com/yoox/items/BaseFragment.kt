package com.yoox.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yoox.items.navigation.NavEvent
import com.yoox.items.utilities.extensions.safeNavigate
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {


    protected abstract val viewModel: ViewModel
    protected open val collectNavigationEvents: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (collectNavigationEvents) {
            collectNavigationEvents()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.onAttached()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.onDetach()
    }

    protected fun collectNavigationEvents() {
        lifecycleScope.launch {
            viewModel.navigationFlow.collect { nav ->
                when (nav) {
                    is NavEvent.ByDirections -> findNavController().safeNavigate(nav.directions)
                }
            }
        }
    }
}

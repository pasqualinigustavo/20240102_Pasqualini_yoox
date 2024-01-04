package com.yoox.items.presentation.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.yoox.items.BaseFragment
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.presentation.history.screen.HistoryFragmentScreen
import com.yoox.items.utilities.YooxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment :
    BaseFragment<HistoryViewModel>() {

    override val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                YooxTheme {
                    HistoryFragmentScreen(
                        isLoadingState = viewModel.isLoadingState == LoadingState.LOADING,
                        itemList = viewModel.itemsState,
                    )
                }
            }
        }

    }
}

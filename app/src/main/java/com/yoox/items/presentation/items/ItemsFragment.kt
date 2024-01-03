package com.yoox.items.presentation.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.yoox.items.BaseFragment
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.presentation.items.screen.ItemsFragmentScreen
import com.yoox.items.utilities.YooxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment :
    BaseFragment<ItemsViewModel>() {

    override val viewModel: ItemsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                YooxTheme {
                    ItemsFragmentScreen(
                        isLoadingState = viewModel.isLoadingState == LoadingState.LOADING,
                        isRefreshingItemListState = viewModel.isRefreshingState ?: false,
                        itemList = viewModel.itemsState,
                        onRefreshItemList = {
                            viewModel.loadNextPage()
                        },
                        onBottomListReached = {
                            viewModel.onBottomReached()
                        },
                        onItemTapped = { item ->
                            viewModel.onItemTapped(item)
                        },
                    )
                }
            }
        }

    }
}

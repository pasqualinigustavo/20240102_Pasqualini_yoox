package com.yoox.items.presentation.itemDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.yoox.items.BaseFragment
import com.yoox.items.domain.model.LoadingState
import com.yoox.items.presentation.itemDetails.screen.ItemDetailsFragmentScreen
import com.yoox.items.utilities.YooxTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment to visualise an asset's details. It uses a ViewPager to handle the two sub-tabs of
 * General details and History.
 */
@AndroidEntryPoint
class ItemDetailsFragment : BaseFragment<ItemDetailsViewModel>() {

    override val viewModel: ItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                YooxTheme {
                    ItemDetailsFragmentScreen(
                        loadingState = viewModel.isLoadingState,
                        isLoadingState = viewModel.isLoadingState == LoadingState.LOADING,
                        item = viewModel.itemsState,
                    )
                }
            }
        }

    }
}

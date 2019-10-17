package vn.sunasterisk.movieawesome.ui.base.fragment

import androidx.databinding.ViewDataBinding
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.ui.base.viewmodel.BaseLoadMoreRefreshViewModel

abstract class BaseLoadMoreRefreshFragment<ViewBinding : ViewDataBinding, ViewModel : BaseLoadMoreRefreshViewModel<Item>, Item> :
    BaseFragment<ViewBinding, ViewModel>() {

    override val layoutId: Int = R.layout.fragment_loadmore_refresh

    override fun handleShowLoading(isLoading: Boolean) {
        // use progress bar
    }
}

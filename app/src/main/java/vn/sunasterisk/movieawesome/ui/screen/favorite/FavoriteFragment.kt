package vn.sunasterisk.movieawesome.ui.screen.favorite

import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.databinding.FragmentFavoriteBinding
import vn.sunasterisk.movieawesome.ui.base.fragment.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    override val viewModel: FavoriteViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_favorite

    companion object {
        fun newInstance(): FavoriteFragment {
            return FavoriteFragment()
        }
    }
}

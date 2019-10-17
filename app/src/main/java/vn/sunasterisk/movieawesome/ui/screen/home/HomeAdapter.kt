package vn.sunasterisk.movieawesome.ui.screen.home

import androidx.recyclerview.widget.DiffUtil
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.databinding.ItemHorizontalMovieBinding
import vn.sunasterisk.movieawesome.ui.base.recyclerview.BaseRecyclerAdapter
import vn.sunasterisk.movieawesome.utils.setSingleClick

class HomeAdapter(val itemClickListener: (Movie) -> Unit = {}) :
    BaseRecyclerAdapter<Movie, ItemHorizontalMovieBinding>(object : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun getLayoutRes(viewType: Int) = R.layout.item_horizontal_movie

    override fun bindFirstTime(binding: ItemHorizontalMovieBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }
}

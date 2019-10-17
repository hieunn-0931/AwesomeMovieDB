package vn.sunasterisk.movieawesome.ui.screen.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.entity.MovieTrending
import vn.sunasterisk.movieawesome.databinding.ItemSlideBinding

class SlideAdapter : PagerAdapter() {
    private lateinit var binding: ItemSlideBinding
    private var movies = ArrayList<MovieTrending>()
    var currentSlide: Int = 0

    override fun isViewFromObject(view: View, `object`: Any) = (view == `object`)

    override fun getCount() = movies.size

    fun update(movies: ArrayList<MovieTrending>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val binding = DataBindingUtil.inflate<ItemSlideBinding>(
            inflater,
            R.layout.item_slide,
            container,
            true
        )
        binding.itemSlide = movies.get(position)
        binding.executePendingBindings()
        currentSlide = position
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }
}

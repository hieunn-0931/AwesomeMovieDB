package vn.sunasterisk.movieawesome.ui.screen.home

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.entity.MovieTrending
import vn.sunasterisk.movieawesome.databinding.FragmentHomeBinding
import vn.sunasterisk.movieawesome.ui.base.fragment.BaseLoadMoreRefreshFragment
import vn.sunasterisk.movieawesome.ui.screen.viewpager.SlideAdapter
import java.util.*


class HomeFragment :
    BaseLoadMoreRefreshFragment<FragmentHomeBinding, HomeViewModel, Movie>(),
    ViewPager.OnPageChangeListener {

    private val slideAdapter = SlideAdapter()

    private var currentSlide = 0

    private lateinit var dots: ArrayList<TextView>

    private val slideViewModel: SlideViewModel by viewModel()

    override val viewModel: HomeViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupSlide()
        slideViewModel.loadData()
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        currentSlide = position
    }

    private fun setupSlide() {
        slideViewModel.moviesTrending.observe(this, Observer {
            slideAdapter.update(it as ArrayList<MovieTrending>)
        })
        viewBinding.viewPager.adapter = slideAdapter
        viewBinding.viewPager.addOnPageChangeListener(this)
        currentSlide = slideAdapter.currentSlide
        initTimerChangeSlide()
    }

    private fun initTimerChangeSlide() {
        val handler = Handler()
        val update = {
            if (currentSlide == NUM_SLIDE) {
                currentSlide = 0
            }
            viewBinding.viewPager.setCurrentItem(currentSlide++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 100, 3000)
    }

    private fun setupAdapter() {
        val adapter = HomeAdapter(
            itemClickListener = { toMovieDetail(it) }
        )

        viewBinding.apply {
            root.setBackgroundColor(Color.BLACK)
            recycler_view.apply {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }

        viewModel.apply {
            listItem.observe(viewLifecycleOwner, Observer {
                adapter.submitList(it)
            })
            firstLoad()
        }
    }

    private fun toMovieDetail(movie: Movie) {

    }

    companion object {
        const val NUM_SLIDE = 5

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}

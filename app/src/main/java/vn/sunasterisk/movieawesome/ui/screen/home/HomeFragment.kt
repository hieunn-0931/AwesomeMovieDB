package vn.sunasterisk.movieawesome.ui.screen.home

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.data.entity.MovieTrending
import vn.sunasterisk.movieawesome.databinding.FragmentHomeBinding
import vn.sunasterisk.movieawesome.ui.base.fragment.BaseLoadMoreRefreshFragment
import vn.sunasterisk.movieawesome.ui.screen.detail.MovieDetailFragment
import vn.sunasterisk.movieawesome.ui.screen.viewpager.SlideAdapter
import java.util.*

class HomeFragment :
    BaseLoadMoreRefreshFragment<FragmentHomeBinding, HomeViewModel, Movie>(),
    ViewPager.OnPageChangeListener {

    private val slideAdapter = SlideAdapter()

    private var currentSlide = 0

    private val slideViewModel: SlideViewModel by viewModel()

    override val viewModel: HomeViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
        setupSlide()
        addDots(currentSlide)
        slideViewModel.loadData()
        view_pager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        currentSlide = position
        addDots(position)
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
        val update = {
            if (currentSlide == NUM_SLIDE) {
                currentSlide = 0
            }
            viewBinding.viewPager.setCurrentItem(currentSlide++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                activity?.runOnUiThread(update)
            }
        }, TIME_DELAY, TIME_PERIOD)
    }

    private fun addDots(current: Int) {
        val dots = arrayOfNulls<ImageView>(5)
        viewBinding.linearDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(activity)
            val widthHeight = 15
            val params =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams(widthHeight, widthHeight))
            params.setMargins(10, 10, 10, 10)
            dots[i]?.layoutParams = params
            dots[i]?.setImageResource(R.drawable.shape_circle)
            dots[i]?.setColorFilter(
                activity?.let {
                    ContextCompat.getColor(it, R.color.color_dark_gray)
                } as Int, PorterDuff.Mode.SRC_ATOP
            )
            linear_dots.addView(dots[i])
        }

        if (dots.isNotEmpty()) {
            dots[current]?.setColorFilter(
                activity?.let {
                    ContextCompat.getColor(it, R.color.color_light_gray)
                } as Int, PorterDuff.Mode.SRC_ATOP
            )
        }
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
        replaceFragment(MovieDetailFragment.newInstance(movie), MovieDetailFragment.TAG, true)
    }

    companion object {
        const val NUM_SLIDE = 5
        const val TIME_DELAY = 100L
        const val TIME_PERIOD = 2000L
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}

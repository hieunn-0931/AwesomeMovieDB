package vn.sunasterisk.movieawesome.ui.screen.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_loadmore_refresh.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import vn.sunasterisk.movieawesome.R
import vn.sunasterisk.movieawesome.data.entity.Movie
import vn.sunasterisk.movieawesome.databinding.FragmentHomeBinding
import vn.sunasterisk.movieawesome.ui.base.fragment.BaseLoadMoreRefreshFragment

class HomeFragment :
    BaseLoadMoreRefreshFragment<FragmentHomeBinding, HomeViewModel, Movie>() {

    override val viewModel: HomeViewModel by viewModel()

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = HomeAdapter(
            itemClickListener = {}
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

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }
}

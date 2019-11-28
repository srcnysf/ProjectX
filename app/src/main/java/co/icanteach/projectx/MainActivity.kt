package co.icanteach.projectx

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import co.icanteach.projectx.common.ui.PostsDiffUtilCallback
import co.icanteach.projectx.common.ui.afterTextChanged
import co.icanteach.projectx.common.ui.observeNonNull
import co.icanteach.projectx.common.ui.runIfNull
import co.icanteach.projectx.databinding.ActivityMainBinding
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedAdapter
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsFeedViewState
import co.icanteach.projectx.ui.populartvshows.PopularTVShowsViewModel
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var tvShowsFeedAdapter: PopularTVShowsFeedAdapter

    private lateinit var moviesViewModel: PopularTVShowsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory)
                .get(PopularTVShowsViewModel::class.java)

        moviesViewModel.getPopularTvShowsLiveData().observeNonNull(this) {
            renderPopularTVShows(it)
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.seasons,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinner.adapter = adapter
        }


        savedInstanceState.runIfNull {
            fetchMovies()
        }
        initPopularTVShowsRecyclerView()
    }

    private fun initPopularTVShowsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = tvShowsFeedAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun renderPopularTVShows(feedViewState: PopularTVShowsFeedViewState) {
        with(binding) {
            viewState = feedViewState
            executePendingBindings()
        }
        tvShowsFeedAdapter.setTvShows(feedViewState.getPopularTvShows())
        var filteredPosts: MutableList<PopularTvShowItem> = mutableListOf()

        binding.searchBar.afterTextChanged { s ->
            val diffResult = DiffUtil.calculateDiff(
                PostsDiffUtilCallback(feedViewState.getPopularTvShows().toMutableList(), filteredPosts)
            )
            diffResult.dispatchUpdatesTo(tvShowsFeedAdapter)
            tvShowsFeedAdapter.setTvShows(feedViewState.getPopularTvShows().filter { it.name!!.toLowerCase().contains(s.toLowerCase())})

        }

        binding.spinner.onItemSelectedListener  = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                tvShowsFeedAdapter.setTvShows(feedViewState.getPopularTvShows())
            }
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val diffResult = DiffUtil.calculateDiff(
                    PostsDiffUtilCallback(feedViewState.getPopularTvShows().toMutableList(), filteredPosts)
                )
                diffResult.dispatchUpdatesTo(tvShowsFeedAdapter)
                tvShowsFeedAdapter.setTvShows(feedViewState.getPopularTvShows().filter { it.appearance!!.contains(position+1)})
            }

        }



        tvShowsFeedAdapter.onItemClick = { popularTvShowItem ->

            val intent: Intent = DetailActivity.newIntent(
                this, popularTvShowItem.name!!,
                popularTvShowItem.img!!,
                popularTvShowItem.occupation!!,
                popularTvShowItem.status!!,
                popularTvShowItem.nickname!!,
                popularTvShowItem.appearance!!
            )
            startActivity(intent)

        }
    }

    private fun fetchMovies() {
        moviesViewModel.fetchMovies()
    }

    companion object {
        const val FIRST_PAGE = 1
    }

}
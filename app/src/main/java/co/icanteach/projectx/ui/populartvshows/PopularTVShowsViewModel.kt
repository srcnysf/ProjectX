package co.icanteach.projectx.ui.populartvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import co.icanteach.projectx.common.Resource
import co.icanteach.projectx.common.RxAwareViewModel
import co.icanteach.projectx.common.ui.plusAssign
import co.icanteach.projectx.domain.FetchPopularTvShowUseCase
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PopularTVShowsViewModel @Inject constructor(private val fetchPopularTvShowUseCase: FetchPopularTvShowUseCase) :
    RxAwareViewModel() {

    private val popularTvShowsLiveData = MutableLiveData<PopularTVShowsFeedViewState>()

    fun getPopularTvShowsLiveData(): LiveData<PopularTVShowsFeedViewState> = popularTvShowsLiveData

    fun fetchMovies() {
        fetchPopularTvShowUseCase
            .fetchMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoviesResultReady)
            .also {
                disposable += it
            }
    }

    private fun onMoviesResultReady(resource: Resource<List<PopularTvShowItem>>) {
        popularTvShowsLiveData.value = PopularTVShowsFeedViewState(
            status = resource.status,
            error = resource.error,
            data = resource.data
        )
    }

    private fun onSearchReady(resource: Resource<List<PopularTvShowItem>>) {
        popularTvShowsLiveData.value = PopularTVShowsFeedViewState(
            status = resource.status,
            error = resource.error,
            data = resource.data
        )
    }


}
package co.icanteach.projectx.ui.populartvshows

import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PopularTVShowsFeedItemViewState(private val tvShow: PopularTvShowItem) {

    fun getImageUrl() = tvShow.img

    fun getTvShowName() = tvShow.name
//    fun getTvShowOverview() = tvShow.overview
}
package co.icanteach.projectx.common.ui

import androidx.recyclerview.widget.DiffUtil
import co.icanteach.projectx.ui.populartvshows.model.PopularTvShowItem

class PostsDiffUtilCallback(private val oldList: List<PopularTvShowItem>, private val newList: List<PopularTvShowItem>) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = oldList[oldItemPosition].char_id == newList[newItemPosition].char_id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = true // for the sake of simplicity we return true here but it can be changed to reflect a fine-grained control over which part of our views are updated
}
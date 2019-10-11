package vn.sunasterisk.movieawesome.ui.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import vn.sunasterisk.movieawesome.ui.widgets.EndlessRecyclerOnScrollListener
import vn.sunasterisk.movieawesome.utils.Constants

abstract class BaseLoadMoreRefreshViewModel<Item>() : BaseViewModel() {

    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        if (isLoading.value == true
            || isRefreshing.value == true
        ) return@OnRefreshListener
        isRefreshing.value = true
        refreshData()
    }

    private val currentPage = MutableLiveData<Int>().apply { value = getPreFirstPage() }

    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    val isLastPage = MutableLiveData<Boolean>().apply { value = false }

    val onScrollListener = object : EndlessRecyclerOnScrollListener(getLoadMoreThreshold()) {
        override fun onLoadMore() {
            if (isLoading.value == true
                || isRefreshing.value == true
                || isLoadMore.value == true
                || isLastPage.value == true
            ) return
            isLoadMore.value = true
            loadMore()
        }
    }

    val listItem = MutableLiveData<ArrayList<Item>>()

    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    abstract fun loadData(page: Int)

    private fun isFirst() = currentPage.value == getPreFirstPage()
            && listItem.value?.isEmpty() ?: true

    fun firstLoad() {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage())
        }
    }

    fun refreshData() {
        loadData(getFirstPage())
    }

    fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: getFirstPage())
    }

    open fun getFirstPage() = Constants.DEFAULT_FIRST_PAGE

    private fun getPreFirstPage() = getFirstPage() - 1

    open fun getLoadMoreThreshold() = Constants.DEFAULT_NUM_VISIBLE_THRESHOLD

    open fun getNumberItemPerPage() = Constants.DEFAULT_ITEM_PER_PAGE

    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
        isLastPage.value = false
    }

    suspend fun onLoadSuccess(page: Int, items: List<Item>?) {
        withContext(Dispatchers.Main) {
            currentPage.value = page
            if (currentPage.value == getFirstPage()) listItem.value?.clear()
            if (isRefreshing.value == true) resetLoadMore()
            val newList = listItem.value ?: ArrayList()
            newList.addAll(items ?: listOf())
            listItem.value = newList
            isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()
            hideLoading()
            isRefreshing.value = false
            isLoadMore.value = false
            checkEmptyList()
        }
    }

    override suspend fun onLoadFail(throwable: Throwable) {
        withContext(Dispatchers.Main) {
            super.onLoadFail(throwable)
            isRefreshing.value = false
            isLoadMore.value = false
            checkEmptyList()
        }
    }

    private fun checkEmptyList() {
        isEmptyList.value = listItem.value?.isEmpty() ?: true
    }
}

package com.github.boybeak.crashviewer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.paging.CrashListDataSourceFactory

class CrashListViewModel : ViewModel() {
//    private var networkState: MutableLiveData<Int>
    private val dataList: LiveData<PagedList<Crash>>

    init {
        val factory = CrashListDataSourceFactory()
        val source = factory.create()
//        networkState = Transformations.switchMap(factory.dataSourceLiveData(), object : Function<LiveData<*>, LiveData<Int>>(){})
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(24)
                .setPageSize(24)
                .setPrefetchDistance(8)
                .setEnablePlaceholders(true)
                .build()

        dataList = LivePagedListBuilder(factory, config).build()
    }

    fun getCrashListLiveData(): LiveData<PagedList<Crash>> {
        return dataList
    }

}
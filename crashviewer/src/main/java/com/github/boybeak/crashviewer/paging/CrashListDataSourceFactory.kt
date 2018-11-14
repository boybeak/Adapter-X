package com.github.boybeak.crashviewer.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.github.boybeak.autobind.Crash

class CrashListDataSourceFactory : DataSource.Factory<Int, Crash>() {

    private val dataSourceData = MutableLiveData<CrashListDataSource>()
    private val dataSource = CrashListDataSource()

    override fun create(): DataSource<Int, Crash> {
        dataSourceData.postValue(dataSource)
        return dataSource
    }

    fun dataSourceLiveData(): MutableLiveData<CrashListDataSource> {
        return dataSourceData
    }

}
package com.github.boybeak.crashviewer.paging

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.github.boybeak.autobind.Api
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.api.Netstate
import com.github.boybeak.crashviewer.api.Return
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrashListDataSource : PageKeyedDataSource<Int, Crash>() {

    companion object {
        private val TAG = CrashListDataSource::class.java.simpleName
    }

    private var networkState: MutableLiveData<Int> = MutableLiveData()
    private var initialLoading: MutableLiveData<Int> = MutableLiveData()

    fun getNetworkState(): MutableLiveData<Int>{
        return networkState
    }

    fun getInitalLoading(): MutableLiveData<Int> {
        return initialLoading
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Crash>) {
        networkState.postValue(Netstate.LOADING)
        initialLoading.postValue(Netstate.LOADING)
        getCall(0, params.requestedLoadSize).enqueue(object : Callback<Return<List<Crash>>>{
            override fun onFailure(call: Call<Return<List<Crash>>>, t: Throwable) {
                networkState.postValue(Netstate.FAILD)
                initialLoading.postValue(Netstate.FAILD)
            }

            override fun onResponse(call: Call<Return<List<Crash>>>, response: Response<Return<List<Crash>>>) {
                val r = response.body()
                if (r != null && r.success) {
                    networkState.postValue(Netstate.SUCCESS)
                    initialLoading.postValue(Netstate.SUCCESS)
                    callback.onResult(r.data, null, 1)
                } else {
                    networkState.postValue(Netstate.FAILD)
                    initialLoading.postValue(Netstate.FAILD)
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Crash>) {
        Log.v(TAG, "loadAfter ${params.key}")
        if (networkState.value == Netstate.LOADING) {
            return
        }
        networkState.postValue(Netstate.LOADING)
        val key = params.key
        getCall(key, params.requestedLoadSize).enqueue(object : Callback<Return<List<Crash>>>{
            override fun onFailure(call: Call<Return<List<Crash>>>, t: Throwable) {
                networkState.postValue(Netstate.FAILD)
            }

            override fun onResponse(call: Call<Return<List<Crash>>>, response: Response<Return<List<Crash>>>) {
                val r = response.body()
                if (r != null && r.success) {
                    networkState.postValue(Netstate.SUCCESS)
                    callback.onResult(r.data, key + 1)
                } else {
                    networkState.postValue(Netstate.FAILD)
                }
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Crash>) {
        Log.v(TAG, "loadBefore ${params.key}")
    }

    private fun getCall(page: Int, pageSize: Int): Call<Return<List<Crash>>> {
        return Api.service.list(0, "", "", page, pageSize)
    }

}
package com.github.boybeak.crashviewer.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.R
import com.github.boybeak.crashviewer.adapter.CrashAdapter
import com.github.boybeak.crashviewer.api.Netstate
import kotlinx.android.synthetic.main.main_fragment.*

class CrashListFragment : Fragment() {

    companion object {
        fun newInstance() = CrashListFragment()
    }

    private lateinit var viewModel: CrashListViewModel
    private val crashListAdapter = CrashAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CrashListViewModel::class.java)

        recyclerView.adapter = crashListAdapter

        viewModel.getCrashListLiveData().observe(this, Observer<PagedList<Crash>> { t ->
            crashListAdapter.submitList(t)
        })
        viewModel.dataSource().getInitalLoading().observe(this, Observer<Int> { t ->
            swipeRefreshLayout.isRefreshing = t == Netstate.LOADING
        })
        swipeRefreshLayout.setOnRefreshListener {
        }

    }

}

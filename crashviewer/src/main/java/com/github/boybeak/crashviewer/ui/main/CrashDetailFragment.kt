package com.github.boybeak.crashviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.boybeak.autobind.Api
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.R
import com.github.boybeak.crashviewer.api.Return
import com.github.boybeak.crashviewer.databinding.FragmentCrashDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrashDetailFragment : Fragment() {

    private lateinit var binding: FragmentCrashDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentCrashDetailBinding>(inflater, R.layout.fragment_crash_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments!!.getInt("crash_id")
        Api.service.getInfo(id).enqueue(object : Callback<Return<Crash>> {
            override fun onFailure(call: Call<Return<Crash>>, t: Throwable) {

            }

            override fun onResponse(call: Call<Return<Crash>>, response: Response<Return<Crash>>) {
                val ret = response.body()
                if (ret?.data != null) {
                    binding.crash = ret.data
                }
            }
        })
    }
}
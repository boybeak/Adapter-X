package com.github.boybeak.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by gaoyunfei on 2018/3/7.
 */

open class BaseDataBindingHolder<out B : ViewDataBinding>(binding: B) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {

    private var mBinding: B? = null

    init {
        mBinding = binding
    }

    fun binding(): B {
        return mBinding!!
    }
}

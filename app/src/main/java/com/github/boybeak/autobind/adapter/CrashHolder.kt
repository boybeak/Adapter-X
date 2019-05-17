package com.github.boybeak.autobind.adapter

import android.content.Context
import android.content.Intent
import com.github.boybeak.adapter.AbsDataBindingHolder
import com.github.boybeak.adapter.annotation.Constructor
import com.github.boybeak.adapter.annotation.HolderInfo
import com.github.boybeak.adapter.annotation.LayoutInfo
import com.github.boybeak.adapter.annotation.Member
import com.github.boybeak.autobind.Crash
import com.github.boybeak.autobind.CrashInfoActivity
import com.github.boybeak.autobind.R
import com.github.boybeak.autobind.databinding.LayoutCrashBinding
import com.github.boybeak.autobind.startActivity

@HolderInfo(layoutId = R.layout.layout_crash,
        layoutInfo = LayoutInfo(name = "CrashImpl", source = Crash::class,
            constructors = arrayOf(
                Constructor(),
                Constructor(members = arrayOf(
                    Member(name="abc", type = String::class)
                ))
            )))
class CrashHolder(b: LayoutCrashBinding) : AbsDataBindingHolder<CrashImpl, LayoutCrashBinding>(b) {
    override fun onBindData(context: Context, layout: CrashImpl, position: Int, adapter: androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>) {
        binding().crash = layout.source
        binding().root.setOnClickListener {
            Intent(context, CrashInfoActivity::class.java)
                    .putExtra("crash", layout.source)
                    .startActivity(context)
        }
    }
}
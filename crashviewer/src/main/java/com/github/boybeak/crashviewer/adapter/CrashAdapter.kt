package com.github.boybeak.crashviewer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.R

class CrashAdapter : PagedListAdapter<Crash, CrashHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrashHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_crash, parent, false)
        return CrashHolder(view)
    }

    override fun onBindViewHolder(holder: CrashHolder, position: Int) {
        holder.onBind(getItem(position)!!)
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Crash>(){
            override fun areItemsTheSame(oldItem: Crash, newItem: Crash): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Crash, newItem: Crash): Boolean {
                return oldItem.id == oldItem.id
            }
        }
    }
}
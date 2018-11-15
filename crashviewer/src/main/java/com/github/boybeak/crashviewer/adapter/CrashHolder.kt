package com.github.boybeak.crashviewer.adapter

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.boybeak.autobind.Crash
import com.github.boybeak.crashviewer.R

class CrashHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val crashTime = itemView.findViewById<AppCompatTextView>(R.id.crashTime)
    private val studioName = itemView.findViewById<AppCompatTextView>(R.id.studioName)
    private val nameAndRole = itemView.findViewById<AppCompatTextView>(R.id.nameAndRole)
    private val simpleCrashInfo = itemView.findViewById<AppCompatTextView>(R.id.simpleCrashInfo)
    private val appVersion = itemView.findViewById<AppCompatTextView>(R.id.appVersion)
    private val sdk = itemView.findViewById<AppCompatTextView>(R.id.sdk)
    private val manufacturerAndModel = itemView.findViewById<AppCompatTextView>(R.id.manufacturerAndModel)
    private val incermental = itemView.findViewById<AppCompatTextView>(R.id.incermental)

    fun onBind(crash: Crash) {
        crashTime.text = crash.crash_time
        studioName.text = crash.studio_name
        nameAndRole.text = crash.nameWithRole
        simpleCrashInfo.text = crash.simple_crash_info
        appVersion.text = crash.app_version
        sdk.text = crash.sdk()
        manufacturerAndModel.text = crash.manufacturerAndModel
        incermental.text = crash.incermental

        itemView.setOnClickListener {
            val args = Bundle()
            args.putInt("crash_id", crash.id)
            it.findNavController().navigate(R.id.fragment_crash_detail, args)
        }
    }

}
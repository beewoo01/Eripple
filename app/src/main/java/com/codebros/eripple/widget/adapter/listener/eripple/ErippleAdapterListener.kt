package com.codebros.eripple.widget.adapter.listener.eripple

import com.codebros.eripple.model.eripple.Eripple
import com.codebros.eripple.widget.adapter.listener.AdapterListener

interface ErippleAdapterListener : AdapterListener {
    fun onItemClick(model : Eripple)
    fun onHeartClick(model : Eripple)
    fun onShearClick(model : Eripple)
}
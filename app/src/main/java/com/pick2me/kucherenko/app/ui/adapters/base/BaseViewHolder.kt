package com.pick2me.kucherenko.app.ui.adapters.base

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bindData(vh: BaseDataHolder, listener: BaseListener)
}
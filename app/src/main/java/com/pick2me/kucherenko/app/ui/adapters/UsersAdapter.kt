package com.pick2me.kucherenko.app.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.pick2me.kucherenko.app.R
import com.pick2me.kucherenko.app.api.data.UsersBody
import com.pick2me.kucherenko.app.ui.adapters.base.BaseDataHolder
import com.pick2me.kucherenko.app.ui.adapters.base.BaseListener
import com.pick2me.kucherenko.app.ui.adapters.base.BaseViewHolder
import kotlinx.android.synthetic.main.card_users.view.*

class UsersAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var items: MutableList<BaseDataHolder> = ArrayList()
    private var options: RequestOptions = initGlideOptions()
    private var mListener: BaseListener? = null

    companion object {
        private val ITEM_USER = 0
    }

    private fun initGlideOptions(): RequestOptions {
        return RequestOptions()
            .fitCenter()
            .dontAnimate()
            .circleCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .priority(Priority.IMMEDIATE)
    }

    fun setListener(listener: BaseListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        UserHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_users, parent, false))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, pos: Int) {
        if (items.isNotEmpty())
            if (holder.itemViewType == ITEM_USER)
                (holder as UserHolder).bindData(items[pos], mListener!!)

    }

    override fun getItemViewType(position: Int): Int {
        return items[position].viewType
    }

    fun setData(list: MutableList<BaseDataHolder>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun addItem(item: BaseDataHolder) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
        notifyItemRemoved(index)
    }

    inner class UserHolder(itemView: View) : BaseViewHolder(itemView) {

        override fun bindData(vh: BaseDataHolder, listener: BaseListener) {
            val fh = vh as UserDH
            Glide.with(itemView.rootView).setDefaultRequestOptions(options).load(fh.user.avatar)
                .into(itemView.user_image)
            itemView.user_name.text = fh.user.firstName
            itemView.user_surname.text = fh.user.lastName
            itemView.user_email.text = fh.user.email
            itemView.setOnClickListener {
                (listener as UserListener).clickUser(
                    fh.user.id.toLong()
                )
            }
        }
    }

    class UserDH(val user: UsersBody) : BaseDataHolder() {
        override val viewType: Int
            get() = ITEM_USER
    }


    interface UserListener : BaseListener {
        fun clickUser(id: Long)
    }

}
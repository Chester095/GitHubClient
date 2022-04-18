package com.geekbrains.githubclient.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.databinding.FragmentRecyclerItemBinding

class RecyclerFragmentAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var dataSet: MutableList<Pair<Int, Data>>,
    private val onStartDragListener: OnStartDragListener,
) : RecyclerView.Adapter<RecyclerFragmentAdapter.BaseViewHolder>(), ItemTouchHelperAdapter {

    override fun getItemViewType(position: Int): Int {
        return dataSet[position].second.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val itemBinding: FragmentRecyclerItemBinding =
            FragmentRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        return ViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount() = dataSet.size

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(data: Pair<Int, Data>)
    }

    inner class ViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(data: Pair<Int, Data>) {
            FragmentRecyclerItemBinding.bind(itemView).apply {
                userImageView.setOnClickListener {
                    onListItemClickListener.onItemClick(data.second)
                }
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        TODO("Not yet implemented")
    }

    override fun onItemDismiss(position: Int) {
        TODO("Not yet implemented")
    }
}
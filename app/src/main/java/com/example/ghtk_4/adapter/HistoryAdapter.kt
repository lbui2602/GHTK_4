package com.example.ghtk_4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ghtk_4.databinding.LayoutHistoryItemBinding
import com.example.ghtk_4.model.History
import com.example.ghtk_4.model.User

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(val itemBinding: LayoutHistoryItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    private val differCallback = object : DiffUtil.ItemCallback<History>(){
        override fun areItemsTheSame(old: History, new: History): Boolean {
            return old.title == new.title  &&
                    old.isUp == new.isUp
        }
        override fun areContentsTheSame(old: History, new: History): Boolean {
            return old == new
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutHistoryItemBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentHistory = differ.currentList[position]
        currentHistory.let {
            holder.itemBinding.historyStatus.text = currentHistory.isUp.toString()
            if(currentHistory.isUp==true){
                holder.itemBinding.historyStatus.text = "UP!"
            }else{
                holder.itemBinding.historyStatus.text = "DOWN!"
            }
            holder.itemBinding.historyTitle.text = currentHistory.title
        }
    }
}
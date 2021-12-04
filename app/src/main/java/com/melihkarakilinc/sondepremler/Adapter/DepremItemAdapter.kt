package com.melihkarakilinc.sondepremler.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.databinding.DepremItemBinding

class DepremItemAdapter() :
    RecyclerView.Adapter<DepremItemAdapter.ViewHolder>() {

    var itemList=mutableListOf<DepremInf>()

    @SuppressLint("NotifyDataSetChanged")
    fun depremFunList(itemList: List<DepremInf>) {
        this.itemList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DepremItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtSehir.text=itemList[position].yer
        holder.binding.txtSiddet.text=itemList[position].siddet
        holder.binding.txtTarih.text=itemList[position].tarih

    }


    inner class ViewHolder(val binding: DepremItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
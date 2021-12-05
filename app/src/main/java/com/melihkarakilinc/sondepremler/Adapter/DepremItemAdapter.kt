package com.melihkarakilinc.sondepremler.Adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.databinding.DepremItemBinding
import com.melihkarakilinc.sondepremler.databinding.ItemLayoutBinding


class DepremItemAdapter() :
    RecyclerView.Adapter<DepremItemAdapter.ViewHolder>() {

    var itemList=mutableListOf<DepremInf>()
    lateinit var onClickListener: View.OnClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun depremFunList(itemList: List<DepremInf>) {
        this.itemList = itemList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtSehir2.text = itemList[position].yer
        holder.binding.txtSiddet2.text = itemList[position].siddet
        holder.binding.txtTarih2.text = itemList[position].tarih
        holder.binding.mapView3.onCreate(null)
        holder.binding.mapView3.getMapAsync(OnMapReadyCallback { googleMap ->
            var mMap = googleMap
            mMap.getUiSettings().setZoomControlsEnabled(true)
            val lat1 = itemList[position].enlem.toDouble()
            val lng1 = itemList[position].boylam.toDouble()
            val bord = LatLng(lat1, lng1)
            mMap.addMarker(MarkerOptions().position(bord).title(itemList[position].yer))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bord, 8F))
        })
        holder.binding.mapView3.postInvalidate()

    }


    inner class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
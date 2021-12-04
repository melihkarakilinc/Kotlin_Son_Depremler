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
        val binding = DepremItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtSehir.text = itemList[position].yer
        holder.binding.txtSiddet.text = itemList[position].siddet
        holder.binding.txtTarih.text = itemList[position].tarih
        holder.binding.mapView.onCreate(null)
        holder.binding.mapView.getMapAsync(OnMapReadyCallback { googleMap ->
            var mMap = googleMap
            mMap.getUiSettings().setZoomControlsEnabled(true)
            val lat1 = itemList[position].enlem.toDouble()
            val lng1 = itemList[position].boylam.toDouble()
            val bord = LatLng(lat1, lng1)
            mMap.addMarker(MarkerOptions().position(bord).title(itemList[position].yer))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bord, 8F))
        })
        holder.binding.mapView.postInvalidate()

    }


    inner class ViewHolder(val binding: DepremItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
package com.melihkarakilinc.sondepremler.View

import android.animation.ValueAnimator
import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.databinding.FragmentDetailBinding


class DetailFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var depremItem: DepremInf
    private lateinit var mMap: GoogleMap
    var enlem: Double = 0.0
    var boylam: Double = 0.0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        depremItem = args.depremItem!!

        with(binding) {
            txtTarihMap.setText(depremItem.tarih)
            txtSaatMap.setText(depremItem.saat)
            txtDerinlikMap.setText(depremItem.derinlik)
            txtSiddetMap.setText(depremItem.siddet)
            txtYerMap.setText(depremItem.yer)
            txtTipMap.setText(depremItem.tip)
        }

        binding.detailMap.onCreate(null)
        binding.detailMap.getMapAsync(OnMapReadyCallback { googleMap ->
            val mMap = googleMap
            mMap.getUiSettings().setZoomControlsEnabled(true)
            val lat1 = depremItem.enlem.toDouble()
            val lng1 = depremItem.boylam.toDouble()
            val bord = LatLng(lat1, lng1)
            mMap.addMarker(MarkerOptions().position(bord).title(depremItem.yer))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bord, 8F))
        })
        binding.detailMap.postInvalidate()

        binding.imgExpand.setOnClickListener {
            if (!binding.mainCard.isVisible) {
                showSubtitle()
                //dropdown icon 180 derece döndürüyoruz
                binding.imgExpand.rotation = 180f
            } else {
                hideSubtitle()
                //dropdown iconu ilk haline döndürüyoruz
                binding.imgExpand.rotation = 0f
            }
        }
    }

    private fun hideSubtitle() {

        val subtitleHeight = binding.mainCard.height
        val heightAnimator = ValueAnimator.ofInt(subtitleHeight, 0)

        heightAnimator.addUpdateListener {
            binding.mainCard.updateHeight(it.animatedValue as Int)
        }
        heightAnimator.doOnEnd {
            binding.mainCard.isVisible = false
        }
        heightAnimator.start()
    }

    private fun showSubtitle() {
        binding.mainCard.updateHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT)
        val totalMarginForSubtitle = 2 * 16.toPx()
        binding.mainCard.measure(
            View.MeasureSpec.makeMeasureSpec(
                binding.clContainer.width - totalMarginForSubtitle,
                View.MeasureSpec.EXACTLY
            ),
            View.MeasureSpec.UNSPECIFIED
        )
        val subtitleHeight = binding.mainCard.measuredHeight
        //binding.mainCard.height = 0
        binding.mainCard.isVisible = true
        val heightAnimator = ValueAnimator.ofInt(0, subtitleHeight)
        heightAnimator.addUpdateListener {
            //tvSubtitle.height = it.animatedValue as Int
        }
        heightAnimator.start()
    }

    fun View.updateHeight(newHeight: Int) {
        layoutParams = layoutParams.apply {
            height = newHeight
        }
    }

    fun Int.toPx() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

    override fun onMapReady(p0: GoogleMap) {
        TODO("Not yet implemented")
    }
}
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
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var depremItem: DepremInf

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        depremItem = args.depremItem!!
        //Toast.makeText(requireContext(), depremItem.toString(), Toast.LENGTH_SHORT).show()

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
        //yazı alanının kullandığı yükselik değerini alalım
        var subtitleHeight = binding.mainCard.height
        //makale de bu bölümün açıklamasını bulabilirsiniz
        var heightAnimator = ValueAnimator.ofInt(subtitleHeight, 0)

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
        //View measure metodu sayesinde tvSubtitle adlı Textview'in yükseklik değeri buluyoruz
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
        //Textviewdeki içerik animasyonlu görünür hale getiriyoruz
        val heightAnimator = ValueAnimator.ofInt(0, subtitleHeight)
        heightAnimator.addUpdateListener {
            // tvSubtitle.height = it.animatedValue as Int
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
}
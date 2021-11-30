package com.melihkarakilinc.sondepremler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.melihkarakilinc.sondepremler.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var depremlist:ArrayList<DepremInf>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this
        ).get(MainViewModel::class.java)

        viewModel.getDeprem()



        viewModel.DepremLiveData.observe(viewLifecycleOwner, Observer { depremlist ->
            depremlist.addAll(depremlist)
            //insertRoom(depremlist)
            Log.e("DepremList", depremlist.toString())
        })
    }
    fun insertRoom(depremInf: ArrayList<DepremInfItem>){
        for (i in depremInf){
            viewModel.insertData(i)
            Log.e("INSERT",i.toString())
        }
    }
}
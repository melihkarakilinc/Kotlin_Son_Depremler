package com.melihkarakilinc.sondepremler.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.melihkarakilinc.sondepremler.Adapter.DepremItemAdapter
import com.melihkarakilinc.sondepremler.ClickListener
import com.melihkarakilinc.sondepremler.Model.DepremInf
import com.melihkarakilinc.sondepremler.Network.ConnectionLiveData
import com.melihkarakilinc.sondepremler.Network.isConnected
import com.melihkarakilinc.sondepremler.ViewModel.MainViewModel
import com.melihkarakilinc.sondepremler.databinding.FragmentMainBinding


class MainFragment : Fragment(), ClickListener {
    lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var depremArrayList:List<DepremInf>
    protected lateinit var connectionLiveData: ConnectionLiveData
    //private val adapter = DepremItemAdapter(depremArrayList)
    private var adapter=DepremItemAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionLiveData = activity?.let { ConnectionLiveData(it.applicationContext) }!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter

        viewModel = ViewModelProvider(
            this
        ).get(MainViewModel::class.java)

        connectionLiveData.observe(viewLifecycleOwner) {
            viewModel.connectionLiveData.value = it
        }
        viewModel.connectionLiveData.value = requireContext().isConnected

        viewModel.getDeprem()


        viewModel.DepremLiveData.observe(viewLifecycleOwner, Observer { depremlist ->
            depremArrayList=depremlist
            adapter.depremFunList(depremlist,this)
            Log.e("DepremList", depremArrayList.toString())
        })
    }

    override fun OnItemSelect(depremInf: DepremInf) {
        Toast.makeText(requireContext(),depremInf.yer,Toast.LENGTH_SHORT).show()
    }
}
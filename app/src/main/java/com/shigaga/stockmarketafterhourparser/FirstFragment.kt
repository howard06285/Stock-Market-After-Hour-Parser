package com.shigaga.stockmarketafterhourparser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var pageViewModel: PageViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*button_first.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        pageViewModel.retrieveDataFromTWSE()

        // Create adapter for the RecyclerView
        val adapter = DataAdapter()
        StockInfoList_Rv.adapter = adapter

        pageViewModel.allShares.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }
}

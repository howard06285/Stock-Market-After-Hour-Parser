package com.shigaga.stockmarketafterhourparser

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.mainfragment.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), NetworkStatusCallBack.NetworkCallback {

    private lateinit var pageViewModel: PageViewModel
    private val ca : NetworkStatusCallBack = NetworkStatusCallBack(this)
    private var isConnected : Boolean = false


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        val request : NetworkRequest = NetworkRequest.Builder().build()

        val connectivityManager: ConnectivityManager =
                activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.requestNetwork(request, ca)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mainfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        communicationModeSw.setOnCheckedChangeListener { _, isChecked ->

            communicationModeTv.text = if (!isChecked) getString(R.string.header_communication_mode_1)
                                            else getString(R.string.header_communication_mode_2)
        }

        fab.setOnClickListener {
            Snackbar.make(it, getString(R.string.snack_bar_confirmation_text), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snack_bar_confirm_text)) {
                    retrieveDataFromTwse()

                }.show()
        }

        retrieveDataFromTwse()

        // Create adapter for the RecyclerView
        val adapter = DataAdapter()

        StockInfoList_Rv.adapter = adapter

        pageViewModel.allShares.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    private fun retrieveDataFromTwse(){
        pageViewModel.deleteAllShareData()

        if (isConnected) {

            if (!communicationModeSw.isChecked) {
                pageViewModel.retrieveDataFromTwseRetrofit()
                Toast.makeText(context, getString(R.string.header_communication_mode_1_is_loading), Toast.LENGTH_SHORT)
                    .show()

            }else {
                pageViewModel.retrieveDataFromTwseHttpUrlConnection()
                Toast.makeText(context, getString(R.string.header_communication_mode_2_is_loading), Toast.LENGTH_SHORT)
                    .show()
            }

        }else
            Snackbar.make(view!!, getString(R.string.header_communication_connection_error), Snackbar.LENGTH_LONG)
                .show()
    }

    override fun onConnectionStatusChanged(isConnected: Boolean) {
        this.isConnected = isConnected

        if (!isConnected)
            Toast.makeText(context, getString(R.string.communication_connection_onLost), Toast.LENGTH_SHORT)
                .show()
    }
}

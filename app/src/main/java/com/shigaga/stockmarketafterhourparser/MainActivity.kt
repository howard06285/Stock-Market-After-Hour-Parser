package com.shigaga.stockmarketafterhourparser

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java)

        fab.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.snack_bar_confirmation_text), Snackbar.LENGTH_LONG)
                    .setAction(getString(R.string.snack_bar_confirm_text)) {
                        pageViewModel.retrieveDataFromTWSE()
                    }.show()
        }
    }
}

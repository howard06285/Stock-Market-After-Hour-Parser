package com.shigaga.stockmarketafterhourparser

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shigaga.stockmarketafterhourparser.data.ShareDb
import com.shigaga.stockmarketafterhourparser.data.Shares
import com.shigaga.stockmarketafterhourparser.data.ioThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class PageViewModel(app: Application) : AndroidViewModel(app) {
    val dao = ShareDb.get(app).shareDao()

    /**
     * We use -ktx Kotlin extension functions here, otherwise you would use LivePagedListBuilder(),
     * and PagedList.Config.Builder()
     */
    val allShares = dao.allSharesById().toLiveData(Config(
            /**
             * A good page size is a value that fills at least a screen worth of content on a large
             * device so the User is unlikely to see a null item.
             * You can play with this constant to observe the paging behavior.
             * <p>
             * It's possible to vary this with list device size, but often unnecessary, unless a
             * user scrolling on a large device is expected to scroll through items more quickly
             * than a small device, such as when the large device uses a grid layout of items.
             */
            pageSize = 15,

            /**
             * If placeholders are enabled, PagedList will report the full size but some items might
             * be null in onBind method (PagedListAdapter triggers a rebind when data is loaded).
             * <p>
             * If placeholders are disabled, onBind will never receive null but as more pages are
             * loaded, the scrollbars will jitter as new pages are loaded. You should probably
             * disable scrollbars if you disable placeholders.
             */
            enablePlaceholders = true,

            /**
             * Maximum number of items a PagedList should hold in memory at once.
             * <p>
             * This number triggers the PagedList to start dropping distant pages as more are loaded.
             */
            maxSize = 60
        )
    )

    fun retrieveDataFromTWSE(){
        Toast.makeText(getApplication(), "After hour data is loading..", Toast.LENGTH_SHORT).show()

        deleteAllShareData()

        GlobalScope.launch {
            val url = URL("https://quality.data.gov.tw/dq_download_json.php?nid=11549&md5_url=bb878d47ffbe7b83bfc1b41d0b24946e")

            val httpConnection = url.openConnection() as HttpURLConnection
            val bufferReader = BufferedReader(InputStreamReader(httpConnection.inputStream))
            var line = bufferReader.readLine()
            val json = StringBuffer()
            while (line != null){
                json.append(line)
                line = bufferReader.readLine()
            }
            val listType = object : TypeToken<ArrayList<Shares>>(){}.type
            val jsonArr = Gson().fromJson<ArrayList<Shares>>(json.toString(), listType)

            saveAfterHourDataToRoomDb(jsonArr)
        }
    }

    private fun saveAfterHourDataToRoomDb(sharesList: ArrayList<Shares>){
        ioThread {
            dao.insert(sharesList)
        }
    }

    private fun deleteAllShareData() {
        ioThread {
            dao.deleteAllShareData()
        }
    }

}
package com.shigaga.stockmarketafterhourparser

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import com.shigaga.stockmarketafterhourparser.communication.GetRequest_Interface
import com.shigaga.stockmarketafterhourparser.data.ShareDb
import com.shigaga.stockmarketafterhourparser.data.Shares
import com.shigaga.stockmarketafterhourparser.data.ioThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class PageViewModel(app: Application) : AndroidViewModel(app) {
    private val dao = ShareDb.get(app).shareDao()

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
            pageSize = 30,

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
            maxSize = 100
        )
    )

    fun retrieveDataFromTwseHttpUrlConnection(){

        thread {
            val url = URL("https://quality.data.gov.tw/dq_download_json.php?nid=11549&md5_url=bb878d47ffbe7b83bfc1b41d0b24946e")

            val httpConnection = url.openConnection() as HttpURLConnection

            val bufferReader = BufferedReader(InputStreamReader(httpConnection.inputStream))

            var line = bufferReader.readLine()

            val json = StringBuffer()

            while (line != null) {
                json.append(line)
                line = bufferReader.readLine()
            }

            val listType = object : TypeToken<ArrayList<Shares>>() {}.type
            val jsonArr = Gson().fromJson<ArrayList<Shares>>(json.toString(), listType)

            saveAfterHourDataToRoomDb(jsonArr)
        }
    }

    fun retrieveDataFromTwseRetrofit(){

        GlobalScope.launch {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://quality.data.gov.tw/")                 // 設定網絡請求的URL網址
                .addConverterFactory(GsonConverterFactory.create())             // 設定數據解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      // 支援RxJava
                .build()

            // 建立網路請求接口實例
            val request: GetRequest_Interface = retrofit.create(GetRequest_Interface::class.java)

            // 對發送請求進行封裝
            val call: Call<JsonArray> = request.getDataFromServer("11549", "bb878d47ffbe7b83bfc1b41d0b24946e")

            //發送網路請求(非同步)
            call.enqueue(object : Callback<JsonArray> {

                // 請求失敗時 --> onFailure()
                override fun onFailure(call: Call<JsonArray>?, t: Throwable?) {
                    Log.e("retrieve_Retrofit", "連接失敗, t=${t}")

                }

                // 請求成功時 --> onResponse()
                override fun onResponse(call: Call<JsonArray>?, response: Response<JsonArray>?) {
                    val yourArray: ArrayList<Shares> = Gson().fromJson(response?.body().toString(),
                        object : TypeToken<List<Shares?>?>() {}.type
                    )

                    saveAfterHourDataToRoomDb(yourArray)
                }
            })
        }
    }

    private fun saveAfterHourDataToRoomDb(sharesList: ArrayList<Shares>){
        ioThread {
            dao.insert(sharesList)
        }
    }

    fun deleteAllShareData() {
        ioThread {
            dao.deleteAllShareData()
        }
    }
}
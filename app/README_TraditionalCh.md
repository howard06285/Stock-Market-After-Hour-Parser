# 股市盤後公開資訊解析器
<br>
[English_README](README.md)<br>
<br>
[<img align="center" src ="app/src/main/res/mipmap-xxxhdpi/ic_app_icon.png">](https://play.google.com/store/apps/details?id=com.shigaga.stockmarketafterhourparser)<br>
<br>

[<img src="app/src/main/res/mipmap-xxxhdpi/googleplay_logo.png" width="20%" height="20%" align="center" valign="center">](https://play.google.com/store/apps/details?id=com.shigaga.stockmarketafterhourparser)<br>
<br>
<br>

這個簡易的Demo專案將會透過[臺灣證券交易所(TWSE)](https://data.gov.tw/dataset/11549#r0)公開資訊OpenDataAPI抓取股市的盤後資料。
這個Demo將透過[[Retrofit 2](https://square.github.io/retrofit/) + [RxJava2](https://github.com/ReactiveX/RxJava)]、 
[HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection)兩種網路通信方式從資料來源抓取數據，
且此專案是使用 MVVM設計模式架構的。(Android Kotlin)
<br>
<br>


## 這個Demo使用到以下功能：

| 實作項目 | 功能描述 |
| --- | --- |
| [Coroutines](https://developer.android.com/kotlin/coroutines) | 協程是 Kotlin 中高效能、且輕量的多工、多執行緒功能 |
| [Retrofit 2](https://square.github.io/retrofit/) + [RxJava2](https://github.com/ReactiveX/RxJava) | 一個使用REST API設計，用來做HTTP請求的網路通信框架 |
| [HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection) | 一種相當常見的Http請求，用來做存取資料的網路通信 |
| [Gson](https://github.com/google/gson) | 由Google公司發布，一個專門用來處理Json解析相關事務的開源工具 |
| [Android Paging Library*](https://developer.android.com/topic/libraries/architecture/paging/) | Android官方推出專門用來處理無限清單、大量資料清單的分頁功能函式庫 |
| [Room Persistence Library*](https://developer.android.com/topic/libraries/architecture/room) | 提升資料持久性的Android官方推薦資料庫操作函式庫 |
| [ViewModel*](https://developer.android.com/topic/libraries/architecture/viewmodel) | 一個[具有生命週期感知的元件](https://developer.android.com/topic/libraries/architecture/lifecycle) |
| [LiveData*](https://developer.android.com/topic/libraries/architecture/livedata)| 一個[具有生命週期感知的元件](https://developer.android.com/topic/libraries/architecture/lifecycle) |
| [MVVM Design Pattern*](https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b) | Model-View-ViewModel, Google官方推薦、最新的設計模式(架構) |

*星號表示這個功能或函式庫是屬於 [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - [Android Jetpack](https://developer.android.com/jetpack) 的一份子。

<br>
<br>
<br>

這個專案使用 [AndroidX相容包函式庫](https://developer.android.com/jetpack/androidx)

<br>
<br>
<br>
<img src="app/src/main/res/mipmap-xxxhdpi/after_hours_demo.gif" width="45%" height="45%" align="center" valign="center">
<br>
<br>
<br>
<br>

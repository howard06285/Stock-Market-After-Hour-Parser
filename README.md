# Stock-Market-After-Hour-Parser <br>

[繁體中文](app/README_TraditionalCh.md)<br>
<br>

[<img align="center" src ="app/src/main/res/mipmap-xxxhdpi/ic_app_icon.png">](https://play.google.com/store/apps/details?id=com.shigaga.stockmarketafterhourparser)<br>
<br>

[<img src="app/src/main/res/mipmap-xxxhdpi/googleplay_logo.png" width="20%" height="20%" align="center" valign="center">](https://play.google.com/store/apps/details?id=com.shigaga.stockmarketafterhourparser)<br>
<br>
<br>

A simple practice for stock market after-hours data retrieving from [TWSE(OpenData)](https://data.gov.tw/dataset/11549#r0) with [[Retrofit 2](https://square.github.io/retrofit/) + [RxJava2](https://github.com/ReactiveX/RxJava)] , [HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection), and architectured by MVVM design pattern. - Android Kotlin
<br>
<br>


## This sample showcases the following things:

| Implementation | Description |
| --- | --- |
| [Coroutines](https://developer.android.com/kotlin/coroutines) | High-performance, light-weight multi-threading in Kotlin |
| [Retrofit 2](https://square.github.io/retrofit/) + [RxJava2](https://github.com/ReactiveX/RxJava) | A type-safe HTTP client for Android, network communication for data retrieving |
| [HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection) | Another network communication for data retrieving |
| [Gson](https://github.com/google/gson) | A Java serialization/deserialization library to parse respond JSON data |
| [Android Paging Library*](https://developer.android.com/topic/libraries/architecture/paging/) | For large/infinite lists of data pagination |
| [Room Persistence Library*](https://developer.android.com/topic/libraries/architecture/room) | Database for data persistence |
| [ViewModel*](https://developer.android.com/topic/libraries/architecture/viewmodel) | [Lifecycle-aware component](https://developer.android.com/topic/libraries/architecture/lifecycle) |
| [LiveData*](https://developer.android.com/topic/libraries/architecture/livedata)| [Lifecycle-aware component](https://developer.android.com/topic/libraries/architecture/lifecycle) |
| [MVVM Design Pattern*](https://medium.com/upday-devs/android-architecture-patterns-part-3-model-view-viewmodel-e7eeee76b73b) | Model-View-ViewModel, Google recommended design pattern |

___* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Part of [Android Jetpack](https://developer.android.com/jetpack)___

<br>
<br>
<br>

This project use [AndroidX library](https://developer.android.com/jetpack/androidx)

<br>
<br>
<br>
<img src="app/src/main/res/mipmap-xxxhdpi/after_hours_demo.gif" width="45%" height="45%" align="center" valign="center">
<br>
<br>
<br>
<br>

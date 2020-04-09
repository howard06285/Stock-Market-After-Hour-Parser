package com.shigaga.stockmarketafterhourparser.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Shares (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key") val key: Int,
    @ColumnInfo(name = "id")
    @SerializedName("\u8b49\u5238\u4ee3\u865f") val id: String,    //證券代號
    @ColumnInfo(name = "name")
    @SerializedName("\u8b49\u5238\u540d\u7a31") val name: String,  //證券名稱
    @ColumnInfo(name = "qty")
    @SerializedName("\u6210\u4ea4\u80a1\u6578") val qty: String,   //成交股數
    @ColumnInfo(name = "startPrice")
    @SerializedName("\u958b\u76e4\u50f9") val startPrice: String,  //開盤價
    @ColumnInfo(name = "highestPrice")
    @SerializedName("\u6700\u9ad8\u50f9") val highestPrice: String,//最高價
    @ColumnInfo(name = "lowestPrice")
    @SerializedName("\u6700\u4f4e\u50f9") val lowestPrice: String, //最低價
    @ColumnInfo(name = "endPrice")
    @SerializedName("\u6536\u76e4\u50f9") val endPrice: String     //收盤價
 )
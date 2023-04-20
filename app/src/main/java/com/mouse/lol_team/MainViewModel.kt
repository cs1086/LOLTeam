package com.mouse.lol_team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.lol_team.data.api.APIRepository
import com.mouse.lol_team.data.api.LolApiService
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(val repository: APIRepository) : ViewModel() {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://tw2.api.riotgames.com/") // 根据实际情况更改
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 打印请求日志
        }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val retrofit2 = Retrofit.Builder()
        .baseUrl("https://sea.api.riotgames.com/") // 根据实际情况更改
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 打印请求日志
        }).build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val lolApiService = retrofit.create(LolApiService::class.java)
    val lolApiService2 = retrofit2.create(LolApiService::class.java)

    fun getSummonner() {
        viewModelScope.launch {
            try {
                val summonerInfo = lolApiService.getSummonerInfo(
                    "神狙維尼",
                    "RGAPI-4fcb5838-256a-4a3a-9119-2c674e97674f"
                )
                lolApiService2.getMatchListInfo(
                    summonerInfo.puuid,
                    0, 10,
                    "RGAPI-4fcb5838-256a-4a3a-9119-2c674e97674f"
                ).forEach{
                    val summonerInfo = lolApiService.getSummonerInfo(
                        "神狙維尼",
                        "RGAPI-4fcb5838-256a-4a3a-9119-2c674e97674f"
                    )
                }
                // 处理返回的数据
            } catch (e: Exception) {
                // 处理异常
                println("####e=$e")
            }
        }
    }
}
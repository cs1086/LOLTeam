package com.mouse.lol_team

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mouse.lol_team.data.api.APIRepository
import com.mouse.lol_team.data.api.LolApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
const val API_KEY="RGAPI-3c16d27b-3321-4918-8e8a-213de8c05c4b"
class MainViewModel(val repository: APIRepository) : ViewModel() {
    val playerList=mutableStateListOf("剁汝雞","我笑他人","放下愛","冬片四季","神狙維尼","阿承小恩","cityoftown","DCKaterERC")
    val _AList= MutableStateFlow(mutableStateListOf<String>())
    val AList=_AList.asStateFlow()
    val _BList= MutableStateFlow(mutableStateListOf<String>())
    val BList=_BList.asStateFlow()
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
    fun randGroup() {
        val shuffledList = playerList.shuffled() // 打亂列表
        // 分割列表
        val midPoint = shuffledList.size / 2
        _AList.value = shuffledList.take(midPoint).toMutableStateList()
        _BList.value = shuffledList.drop(midPoint).toMutableStateList()
    }
    fun clearGroup(){
        _AList.value.clear()
        _BList.value.clear()
    }
    fun getSummonner() {
        viewModelScope.launch {
            try {
                val summonerInfo = lolApiService.getSummonerInfo(
                    "剁汝雞",
                    API_KEY
                )
                lolApiService2.getMatchListInfo(
                    summonerInfo.puuid,
                    0, 10,
                    API_KEY
                ).forEach{
                    val summonerInfo = lolApiService.getSummonerInfo(
                        "剁汝雞",
                        API_KEY
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
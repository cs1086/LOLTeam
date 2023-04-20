package com.mouse.lol_team.data.api

import com.mouse.lol_team.data.model.SummonerInfo
import com.mouse.lol_team.data.model.SummonerInfoDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LolApiService {
    //查詢召喚師資料
    @GET("lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummonerInfo(
        @Path("summonerName") summonerName: String,
        @Query("api_key") apiKey: String
    ): SummonerInfo
    //取得積分排位基本資料
    @GET("lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    suspend fun getSummonerDetailInfo(
        @Path("encryptedSummonerId") encryptedSummonerId: String,
        @Query("api_key") apiKey: String
    ): SummonerInfoDetail
    //取得近期對戰紀錄
    @GET("lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatchListInfo(
        @Path("puuid") puuid: String,
        @Query("start") start: Int,
        @Query("count") count: Int,
        @Query("api_key") apiKey: String
    ): List<String>
}
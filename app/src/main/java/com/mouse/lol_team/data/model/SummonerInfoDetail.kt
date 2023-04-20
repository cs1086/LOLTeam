package com.mouse.lol_team.data.model

class SummonerInfoDetail : ArrayList<SummonerInfoDetailItem>()

data class SummonerInfoDetailItem(
    val freshBlood: Boolean,
    val hotStreak: Boolean,
    val inactive: Boolean,
    val leagueId: String,
    val leaguePoints: Int,
    val losses: Int,
    val queueType: String,
    val rank: String,
    val summonerId: String,
    val summonerName: String,
    val tier: String,
    val veteran: Boolean,
    val wins: Int
)
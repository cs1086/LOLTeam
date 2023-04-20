package com.mouse.lol_team.data.api

interface APIRepository {
    fun getSummonerInfo()
}
class HTTPRepository:APIRepository{
    override fun getSummonerInfo() {

    }
}
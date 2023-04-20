package com.mouse.lol_team.di

import com.mouse.lol_team.MainViewModel
import com.mouse.lol_team.data.api.APIRepository
import com.mouse.lol_team.data.api.HTTPRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<APIRepository> { HTTPRepository() }
    viewModel { MainViewModel(get()) }
}
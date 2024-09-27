package com.onlineassignment.fetchrewards.challenge.di

import com.onlineassignment.fetchrewards.challenge.viewmodel.HiringViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HiringViewModel(get()) }
}

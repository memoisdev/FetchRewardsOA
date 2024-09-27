package com.onlineassignment.fetchrewards.challenge.di

import com.onlineassignment.fetchrewards.challenge.domain.usecase.GetGroupedHiringItemsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGroupedHiringItemsUseCase(get()) }
}

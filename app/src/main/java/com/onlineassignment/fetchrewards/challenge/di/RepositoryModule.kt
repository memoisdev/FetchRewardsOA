package com.onlineassignment.fetchrewards.challenge.di

import com.onlineassignment.fetchrewards.challenge.data.repository.HiringRepository
import com.onlineassignment.fetchrewards.challenge.domain.repository.HiringRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<HiringRepository> { HiringRepositoryImpl(get()) }
}

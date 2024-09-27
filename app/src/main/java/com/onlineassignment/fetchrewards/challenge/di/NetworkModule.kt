package com.onlineassignment.fetchrewards.challenge.di

import com.onlineassignment.fetchrewards.challenge.data.remote.fetchrewards.api.FetchHiringService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val REWARDS_URL = "https://fetch-hiring.s3.amazonaws.com/"

val networkModule = module {
    single { provideRetrofit() }
    single { get<Retrofit>().create(FetchHiringService::class.java) }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(REWARDS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

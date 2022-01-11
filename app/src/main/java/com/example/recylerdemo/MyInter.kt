package com.example.recylerdemo

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



const val BASE_URL="https://reqres.in/api/"

interface MyInter {

    @GET("users")
    fun getHeadLines():Call<Any>

    object NewsService{
        val myInstance:MyInter
        init {
            val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
            myInstance=retrofit.create(MyInter::class.java)
        }
    }


}
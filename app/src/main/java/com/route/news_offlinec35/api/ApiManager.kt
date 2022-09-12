package com.route.news_offlinec35.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object{
        private val BASE_URL = "https://newsapi.org"
        private var retrofit: Retrofit? = null
        private fun getInstance(): Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
        fun getApis(): WebServices{
            return getInstance().create(WebServices::class.java)
        }
    }
}
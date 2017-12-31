package com.example.credr.snapsearch

import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import rx.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by punitdama on 30/12/17.
 * Amateur version of Retrofit Service created using RxJava + OkHttp used for Network calls
 */
@Singleton
class ApiService @Inject constructor(val client: OkHttpClient, val gson: Gson){

    inline fun <reified T> get(url : String) : Single<T> {
        return Single.fromCallable{
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            if(response.isSuccessful){
                return@fromCallable gson.fromJson(response.body()?.string(),T::class.java)
            }
            else{
                throw Exception("Error fetching data " + response.code().toString())
            }
        }
    }

    inline fun <reified T> post(url: String,postParams : String) : Single<T>{
        return Single.fromCallable {
            val mediaType = MediaType.parse("application/json; charset=utf-8")
            val requestBody = RequestBody.create(mediaType,postParams)

            val request = Request.Builder().url(url).post(requestBody).build()
            val response = client.newCall(request).execute()

            if(response.isSuccessful){
                return@fromCallable gson.fromJson(response.body()?.string(),T::class.java)
            }
            else{
                throw Exception("Error fetching data " + response.code().toString())
            }
        }
    }
}
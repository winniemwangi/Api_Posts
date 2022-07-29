package com.winnie.mypost

import retrofit2.Call
import retrofit2.http.GET

interface ApiInteface {
    @GET("/posts")
    fun  getPosts(): Call<List<Post>>
}
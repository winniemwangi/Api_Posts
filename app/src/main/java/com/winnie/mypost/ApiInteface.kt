package com.winnie.mypost

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInteface {
    @GET("/posts")
    fun  getPosts(): Call<List<Post>>

    @GET("/posts/{postId}")
    fun getPostById(@Path("postId")postId: Int): Call<Post>

    @GET("/posts/{postId/comments}")
    fun getComments(@Path("postId")commentsId: Int): Call<List<Comment>>
}

//interface is a contract between two systems
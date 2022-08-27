package com.winnie.mypost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.winnie.mypost.databinding.ActivityCommentsBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommentsBinding

    var postId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtainPostId()
        fetchPostById()
    }

    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID")?: 0
    }

    fun obtainComment(){
        var extras=intent
        postId=extras.getIntExtra("POST_ID",0)
    }
    
    fun fetchPostById(){
        var apiClient = ApiClient.buildApiClient(ApiInteface::class.java)
        var request = apiClient.getPostById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var post = response.body()
                    binding.tvPostTitle.text = post?.title
                    binding.tvPostBody.text = post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun fetchComments(){
        var apiClient=ApiClient.buildApiClient(ApiInteface::class.java)
        var request=apiClient.getComments(postId)

        request.enqueue(object :Callback<List<Comment>>{
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                var comment=response.body()
                if (response.isSuccessful){
                    Log.d("TAG",comment.toString())
                    comment?.let { displayComments(comment) }
                }

            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {

            }
        })
    }
    fun displayComments(comment: List<Comment>){
        var adapter=CommentsAdapter(comment)
        binding.rvComments.layoutManager= LinearLayoutManager(this)
        binding.rvComments.adapter=adapter

    }
}
package com.winnie.mypost

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.winnie.mypost.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchPost()
        title = "Api Posts"
    }

    fun fetchPost(){
        val apiClient = ApiClient.buildApiClient(ApiInteface::class.java)
        var request = apiClient.getPosts()



        request.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if(response.isSuccessful){
                    var post = response.body()
                    Toast.makeText(baseContext,"fetched ${post!!.size} posts", Toast.LENGTH_LONG)
                        .show()

                    var postAdapter = PostRvAdapter(baseContext,post)
                    binding.rvPost.layoutManager = LinearLayoutManager(baseContext)
                    binding.rvPost.adapter =postAdapter
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {

            }

        })
    }
}
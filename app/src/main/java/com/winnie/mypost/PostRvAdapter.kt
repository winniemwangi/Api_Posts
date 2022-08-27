package com.winnie.mypost


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.winnie.mypost.databinding.PostListItemBinding

class PostRvAdapter ( var postList: List<Post>):
    RecyclerView.Adapter<PostRvAdapter.RetrofitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetrofitViewHolder {

        var binding = PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RetrofitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RetrofitViewHolder, position: Int) {

        var currentPost = postList.get(position)
        holder.binding.tvUserId.text = currentPost.userId.toString()
        holder.binding.tvId.text = currentPost.id.toString()
        holder.binding.tvTitle.text=currentPost.title
        holder.binding.tvBody.text = currentPost.body
        val context = holder.itemView.context
        holder.binding.cvPosts.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("POST_ID", currentPost.id)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }


    class RetrofitViewHolder(val binding: PostListItemBinding): RecyclerView.ViewHolder(binding.root)
}





package com.winnie.mypost

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.winnie.mypost.databinding.CommentListItemBinding

class CommentsAdapter(var commentList: List<Comment>): RecyclerView.Adapter<CommentViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
                var binding = CommentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                return CommentViewHolder(binding)
        }

        override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
                var currentComment = commentList.get(position)
                holder.binding.tvPostId.text = currentComment.postId.toString()
                holder.binding.tvid.text = currentComment.id.toString()
                holder.binding.tvName.text = currentComment.name
                holder.binding.tvEmail.text = currentComment.email
                holder.binding.tvbody.text = currentComment.body
                val context = holder.itemView.context
                holder.binding.cvComments.setOnClickListener {
                        val intent = Intent(context, CommentsActivity::class.java)
                        intent.putExtra("POST_ID", currentComment.id)
                        context.startActivity(intent)
                }

        }

        override fun getItemCount(): Int {
                return commentList.size
        }

}

class CommentViewHolder(var binding: CommentListItemBinding): RecyclerView.ViewHolder(binding.root)
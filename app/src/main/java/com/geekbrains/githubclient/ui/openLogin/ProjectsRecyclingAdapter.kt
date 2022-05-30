package com.geekbrains.githubclient.ui.openLogin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.R
import com.geekbrains.githubclient.domain.GitProjectEntity

class ProjectsRecyclingAdapter    :  RecyclerView.Adapter<GitProjectVh>()  {
    private var data: List<GitProjectEntity> = listOf()

    fun setData(repos: List<GitProjectEntity>) {
        data = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitProjectVh {
        return GitProjectVh(
            LayoutInflater.from(parent.context).inflate(R.layout.item_git_project,parent,false))
    }

    override fun onBindViewHolder(holder: GitProjectVh, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int = data.size

}
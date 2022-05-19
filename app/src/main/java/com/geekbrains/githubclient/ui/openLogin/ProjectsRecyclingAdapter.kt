package com.geekbrains.githubclient.ui.openLogin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.domain.GitProjectEntity

class ProjectsRecyclingAdapter    :  RecyclerView.Adapter<GitProjectVh>()  {
    private var data: List<GitProjectEntity> = emptyList()

    // для обновления адаптера. метод который настраивает данные
    fun setData(repos: List<GitProjectEntity>) {
        data = repos
        // по хорошему надо только некоторые чтобы обновлялись, а не все сразу
        notifyDataSetChanged()
    }

    // создаём вьюхолдер (замена findViewById)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitProjectVh {
        return GitProjectVh.create(parent)
    }

    // передаём в элемент новые данные
    override fun onBindViewHolder(holder: GitProjectVh, position: Int) {
        holder.bind(getItem(position))
    }

    // получаем отдельный элемент
    private fun getItem(pos: Int): GitProjectEntity = data[pos]

    // получаем размер элемента (списка)
    override fun getItemCount(): Int = data.size
}
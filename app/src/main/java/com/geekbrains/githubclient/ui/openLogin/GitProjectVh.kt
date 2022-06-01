package com.geekbrains.githubclient.ui.openLogin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.githubclient.databinding.ItemGitProjectBinding
import com.geekbrains.githubclient.domain.GitProjectEntity

class GitProjectVh(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemGitProjectBinding.bind(view)

    // раздуваем item
    fun bind(item: GitProjectEntity) {
        binding.itemGitRepoId.text = item.id.toString()
        binding.itemGitRepoName.text = item.name
    }
}
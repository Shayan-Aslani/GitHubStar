package com.shayanaslani.githubstar.adapter

import javax.inject.Inject
import android.app.Activity
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shayanaslani.githubstar.R
import com.shayanaslani.githubstar.databinding.ItemGithubRepoBinding
import com.shayanaslani.githubstar.model.GitHubRepo

class GitHubRepoAdapter @Inject constructor() :
    RecyclerView.Adapter<GitHubRepoAdapter.GitHubRepoHolder>() {
    private val repoList = ArrayList<GitHubRepo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoHolder {
        val activity = parent.getContext() as Activity
        val binding: ItemGithubRepoBinding = DataBindingUtil.inflate(
            activity.layoutInflater
            ,
            R.layout.item_github_repo, parent, false
        )
        return GitHubRepoHolder(
            binding
        )
    }

    fun setRepoList(list: List<GitHubRepo>) {
        repoList.clear()
        repoList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: GitHubRepoHolder, position: Int) {
        holder.bind(repoList.get(position))
    }

    class GitHubRepoHolder(
        val binding: ItemGithubRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gitHubRepo: GitHubRepo) {
            binding.textRepoName.text = gitHubRepo.name
            binding.textRepoDescription.text = gitHubRepo.description
            binding.textLanguage.text = """Language : ${gitHubRepo.language}"""
            binding.textStars.text = """Stars : ${gitHubRepo.stargazersCount}"""
        }
    }
}
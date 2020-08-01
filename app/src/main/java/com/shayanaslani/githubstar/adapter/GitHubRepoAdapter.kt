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

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: GitHubRepoHolder, position: Int) {
        holder.bind(repoList.get(position))
    }

    class GitHubRepoHolder(
        val mBinding: ItemGithubRepoBinding
    ) : RecyclerView.ViewHolder(mBinding.root) {
        fun bind(gitHubRepo: GitHubRepo) {
            mBinding.textRepoName.text = gitHubRepo.name
            mBinding.textRepoDescription.text = gitHubRepo.description
            mBinding.textLanguage.text = """Language : ${gitHubRepo.language}"""
            mBinding.textStars.text = """Stars : ${gitHubRepo.stargazersCount}"""
        }
    }
}
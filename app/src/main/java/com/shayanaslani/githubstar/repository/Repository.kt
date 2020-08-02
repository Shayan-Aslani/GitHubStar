package com.shayanaslani.githubstar.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shayanaslani.githubstar.model.GitHubRepo
import com.shayanaslani.githubstar.network.GitHubService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val gitHubService: GitHubService) {
    val starredRepos: MutableLiveData<List<GitHubRepo>> = MutableLiveData()
    suspend fun loadStarredRepos(userName: String) {
        try {
            val response = gitHubService.loadStarredRepos(userName)
            if (response.isSuccessful)
                starredRepos.postValue(response.body())
            else
                Log.d("network :", response.code().toString())

        } catch (t: Throwable) {
            Log.d("network :", t.message)
        }
    }
}

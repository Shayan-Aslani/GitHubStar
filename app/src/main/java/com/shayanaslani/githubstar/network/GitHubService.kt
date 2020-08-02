package com.shayanaslani.githubstar.network

import androidx.lifecycle.LiveData
import com.shayanaslani.githubstar.model.GitHubRepo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/starred")
    suspend fun loadStarredRepos(@Path("user")userName :String) : Response<List<GitHubRepo>>
}
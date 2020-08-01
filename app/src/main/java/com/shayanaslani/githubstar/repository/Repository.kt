package com.shayanaslani.githubstar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shayanaslani.githubstar.model.GitHubRepo
import com.shayanaslani.githubstar.network.GitHubService
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val gitHubService: GitHubService) {
    val list: MutableLiveData<List<GitHubRepo>> = MutableLiveData()
    fun loadStarredRepos(userName: String) {
        gitHubService.loadStarredRepos(userName).enqueue(object : Callback<List<GitHubRepo>> {
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {
                Log.d("network :", t.message)
            }

            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                if (response.isSuccessful) {
                    list.postValue(response.body())
                }
            }
        }
        )
    }
}

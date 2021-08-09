package com.example.githubexplorer.service

import com.example.githubexplorer.repositoryScreen.domain.model.Items
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @GET("search/repositories")
    fun getRepositories(
        @Query("q") searchText: String
    ): Call<Items>
}
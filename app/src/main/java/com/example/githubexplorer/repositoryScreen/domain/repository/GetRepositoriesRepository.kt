package com.example.githubexplorer.repositoryScreen.domain.repository

import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import com.example.githubexplorer.service.ResponseListener

interface GetRepositoriesRepository {
    fun getRepositories(
        searchText: String,
        responseListener: ResponseListener
    )

    fun storeRepositoriesList(
        listOfRepositories: List<RepositoryDetails>,
        responseListener: ResponseListener
    )
}
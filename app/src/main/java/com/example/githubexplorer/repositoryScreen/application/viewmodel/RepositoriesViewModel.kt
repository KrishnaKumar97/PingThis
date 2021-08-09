package com.example.githubexplorer.repositoryScreen.application.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubexplorer.model.ApiError
import com.example.githubexplorer.repositoryScreen.domain.model.Items
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import com.example.githubexplorer.repositoryScreen.domain.usecase.fetchRepositories.RepositoriesUseCaseImpl
import com.example.githubexplorer.repositoryScreen.domain.usecase.insertRepositories.InsertRepositoriesUseCaseImpl

class RepositoriesViewModel:ViewModel() {
    private val repositoriesUseCase =
        RepositoriesUseCaseImpl()

    private val insertRepositoriesUseCase = InsertRepositoriesUseCaseImpl()

    fun fetchRepositoryDetails(searchText: String) {
        repositoriesUseCase.invoke(searchText)
    }
    fun getRepositorySuccessResponse(): LiveData<Items> =
        repositoriesUseCase.getRepositoriesSuccessResponse

    fun getRepositoryFailureResponse(): LiveData<ApiError> = repositoriesUseCase.getRepositoriesFailureResponse
    fun getRepositoryNetworkFailureResponse(): LiveData<Int> =
        repositoriesUseCase.getRepositoriesNetworkFailureResponse

    fun insertRepositoryDetails(listOfRepositories:List<RepositoryDetails>) {
        insertRepositoriesUseCase.invoke(listOfRepositories)
    }
    fun insertRepositorySuccessResponse(): LiveData<List<RepositoryDetails>> =
        insertRepositoriesUseCase.insertRepositoriesSuccessResponse

    fun insertRepositoryFailureResponse(): LiveData<ApiError> = insertRepositoriesUseCase.insertRepositoriesFailureResponse
    fun insertRepositoryNetworkFailureResponse(): LiveData<Int> =
        insertRepositoriesUseCase.insertRepositoriesNetworkFailureResponse
}
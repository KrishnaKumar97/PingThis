package com.example.githubexplorer.repositoryScreen.domain.usecase.insertRepositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubexplorer.model.ApiError
import com.example.githubexplorer.repositoryScreen.data.repository.GetRepositoriesRepositoryImpl
import com.example.githubexplorer.repositoryScreen.domain.model.Items
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import com.example.githubexplorer.repositoryScreen.domain.usecase.fetchRepositories.RepositoriesUseCase
import com.example.githubexplorer.service.ResponseListener

class InsertRepositoriesUseCaseImpl:
    InsertRepositoriesUseCase{

    private val creditRepository =
        GetRepositoriesRepositoryImpl()

    var insertRepositoriesSuccessResponse = MutableLiveData<List<RepositoryDetails>>()
    var insertRepositoriesFailureResponse = MutableLiveData<ApiError>()
    var insertRepositoriesNetworkFailureResponse = MutableLiveData<Int>()

    override fun invoke(listOfRepositories: List<RepositoryDetails>) {
        creditRepository.storeRepositoriesList(
            listOfRepositories,
            object : ResponseListener {
                override fun onSuccess(success: Any?) {
                    insertRepositoriesSuccessResponse.value = success as List<RepositoryDetails>?
                }

                override fun onNetworkFailure(errorCode: Int) {
                    insertRepositoriesNetworkFailureResponse.value = errorCode
                }

                override fun onFailure(apiError: ApiError?) {
                    insertRepositoriesFailureResponse.value = apiError
                }

            })
    }
}
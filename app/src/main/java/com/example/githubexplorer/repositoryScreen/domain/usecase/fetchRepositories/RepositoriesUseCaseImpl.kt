package com.example.githubexplorer.repositoryScreen.domain.usecase.fetchRepositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubexplorer.model.ApiError
import com.example.githubexplorer.repositoryScreen.data.repository.GetRepositoriesRepositoryImpl
import com.example.githubexplorer.repositoryScreen.domain.model.Items
import com.example.githubexplorer.repositoryScreen.domain.usecase.fetchRepositories.RepositoriesUseCase
import com.example.githubexplorer.service.ResponseListener

class RepositoriesUseCaseImpl:
    RepositoriesUseCase {

    private val creditRepository =
        GetRepositoriesRepositoryImpl()

    var getRepositoriesSuccessResponse = MutableLiveData<Items>()
    var getRepositoriesFailureResponse = MutableLiveData<ApiError>()
    var getRepositoriesNetworkFailureResponse = MutableLiveData<Int>()

    override fun invoke(searchText: String) {
        Log.d("KRISHNA",searchText)
        creditRepository.getRepositories(
            searchText,
            object : ResponseListener {
                override fun onSuccess(success: Any?) {
                    getRepositoriesSuccessResponse.value = success as Items?
                }

                override fun onNetworkFailure(errorCode: Int) {
                    getRepositoriesNetworkFailureResponse.value = errorCode
                }

                override fun onFailure(apiError: ApiError?) {
                    getRepositoriesFailureResponse.value = apiError
                }

            })
    }
}
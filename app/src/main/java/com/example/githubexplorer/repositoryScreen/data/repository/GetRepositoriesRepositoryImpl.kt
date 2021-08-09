package com.example.githubexplorer.repositoryScreen.data.repository

import android.util.Log
import com.example.githubexplorer.ApplicationClass
import com.example.githubexplorer.repositoryScreen.domain.model.Items
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails
import com.example.githubexplorer.repositoryScreen.domain.repository.GetRepositoriesRepository
import com.example.githubexplorer.service.ResponseListener
import com.example.githubexplorer.service.RestClient
import com.example.githubexplorer.utils.ErrorExceptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetRepositoriesRepositoryImpl:GetRepositoriesRepository {
    override fun getRepositories(searchText: String, responseListener: ResponseListener) {
        RestClient.getRetrofit().getRepositories(searchText)
            .enqueue(object : Callback<Items> {
                override fun onFailure(call: Call<Items>, t: Throwable) {
                    Log.e("GetRepositories Failed", t.message.toString())
                    responseListener.onNetworkFailure(ErrorExceptions.error(t))
                }

                override fun onResponse(
                    call: Call<Items>,
                    response: Response<Items>
                ) {
                    Log.e("GetRepositories Success", response.toString())
                    if (response.code() == 200)
                        responseListener.onSuccess(response.body())
                }

            })
    }

    override fun storeRepositoriesList(listOfRepositories: List<RepositoryDetails>,  responseListener: ResponseListener) {
        ApplicationClass.repositoryDatabase.repositoryDAO.insertRepositoriesList(
            listOfRepositories
        )
    }
}
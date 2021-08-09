package com.example.githubexplorer.service

import com.example.githubexplorer.model.ApiError

interface ResponseListener {
    fun onSuccess(success: Any?)
    fun onNetworkFailure(errorCode: Int)
    fun onFailure(apiError: ApiError?)
}
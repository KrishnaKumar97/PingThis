package com.example.githubexplorer.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiError(
    @SerializedName("status")
    var statusCode: Int? = 0,
    @SerializedName("message")
    var errorMessage: String? = null
): Serializable
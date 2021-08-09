package com.example.githubexplorer.utils

import com.example.githubexplorer.helper.Constants
import com.example.githubexplorer.model.ApiError
import com.example.githubexplorer.service.RestClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class ErrorExceptions {

    companion object {
        /**
         * Handle error on Http Failure Does
         * @param t which is the throwable type
         * @return Int type Of code
         */
        fun error(t: Throwable): Int {
            return when (t) {
                is SocketTimeoutException -> {
                    Constants.HttpStatusCode.POOR_INTERNET_CONNECTION
                }
                is UnknownHostException -> {
                    Constants.HttpStatusCode.POOR_INTERNET_CONNECTION
                }
                is ConnectException -> {
                    Constants.HttpStatusCode.POOR_INTERNET_CONNECTION
                }
                else -> {
                    Constants.HttpStatusCode.INTERNAL_SERVER_ERROR
                }
            }
        }

        /**
         * Convert the Error Response
         * @param response
         */

        fun <T> parseError(response: Response<T>, ipAddress: String): ApiError? {
            val error: ApiError
            val converter: Converter<ResponseBody, ApiError> =
                RestClient.retrofit(ipAddress).responseBodyConverter(
                    ApiError::class.java, arrayOfNulls<Annotation>(0)
                )

            error = try {
                if (response.errorBody() == null) throw AssertionError()
                if (response.code() == 502)
                    ApiError(502, "Bad Gateway")
                else
                    converter.convert(response.errorBody()!!)!!
            } catch (e: IOException) {
                return ApiError()
            }
            return error

        }
    }

}
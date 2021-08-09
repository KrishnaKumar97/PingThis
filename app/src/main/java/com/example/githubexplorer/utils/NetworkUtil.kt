package com.example.githubexplorer.utils

import okhttp3.HttpUrl

class NetworkUtil {
    companion object {

        fun getBaseUrl(url: HttpUrl, isHttps: Boolean = false): String {
            var baseUrl: String? = null

            baseUrl = if (isHttps) {
                "https://"
            } else {
                "http://"
            }

            val host = (url.host)
            val path1 = url.pathSegments[0]
            val path2 = url.pathSegments[1]

            return "$baseUrl$host/$path1/$path2/"
        }
    }
}
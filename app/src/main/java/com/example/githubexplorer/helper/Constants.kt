package com.example.githubexplorer.helper

import android.Manifest

class Constants {

    class RetrofitConstants {
        companion object {
            const val CONNECT_TIMEOUT_SECS = 160
        }
    }

    class HttpStatusCode {
        companion object {
            // SuccessFull Response
            const val OK_RESPONSE = 200

            // Poor Internet Connection
            const val POOR_INTERNET_CONNECTION = 505

            //Internal Server Error
            const val INTERNAL_SERVER_ERROR = 500

            //Not Found
            const val NOT_FOUND = 404

            //UnAuthorized
            const val UNAUTHORIZED = 401

            const val NO_INTERNET_CONNECTION = 501

            const val UNPROCESSED_ENTITY = 422

            const val CONFLICT_ERROR = 409
        }
    }
}
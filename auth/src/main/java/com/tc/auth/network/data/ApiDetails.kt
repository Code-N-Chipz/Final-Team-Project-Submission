package com.tc.auth.network.data

import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

/**
 * Firebase configuration details
 */
data class FirebaseApiDetails(
    val apiKey: String,
    val projectId: String,
    val appId: String,
    val databaseUrl: String,
    val auth: FirebaseAuth
) {
    companion object {
        /**
         * Retrieves the FirebaseApiDetails for the default Firebase app.
         */
        fun getInstance(): FirebaseApiDetails {
            val options = FirebaseApp.getInstance().options

            return FirebaseApiDetails(
                apiKey = options.apiKey,
                projectId = options.projectId ?: "",
                appId = options.applicationId,
                databaseUrl = options.databaseUrl ?: "",
                auth = FirebaseAuth.getInstance()
            )
        }
    }
}

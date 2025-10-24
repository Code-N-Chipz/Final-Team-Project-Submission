package com.tc.auth.data

//import android.R.attr.apiKey
import com.google.firebase.FirebaseApp
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.database.FirebaseDatabase

/**
 * Holds all Firebase configuration details for the current app.
 */
data class FirebaseApiDetails(
    val apiKey: String,
    val projectId: String,
    val appId: String,
    val databaseUrl: String,
    val auth: FirebaseAuth,
    val firestore: FirebaseFirestore,
    val realtimeDb: FirebaseDatabase
) {
    companion object {
        /**
         * Retrieves the FirebaseApiDetails for the default Firebase app.
         */
        fun getInstance(): FirebaseApiDetails {
            val options = FirebaseApp.getInstance().options

            return FirebaseApiDetails(
                apiKey = options.apiKey ?: "",
                projectId = options.projectId ?: "",
                appId = options.applicationId ?: "",
                databaseUrl = options.databaseUrl ?: "",
                auth = FirebaseAuth.getInstance(),
                firestore = FirebaseFirestore.getInstance(),
                realtimeDb = FirebaseDatabase.getInstance()
            )
        }
    }
}

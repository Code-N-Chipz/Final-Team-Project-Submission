package com.tc.auth.network.data

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
//    private val authApi: AuthApi,        //Retrofit API interface
    private val firebaseAuth: FirebaseAuth
) {

    // Firebase email/password sign in
    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Sign up new user
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            false
        }
    }
}

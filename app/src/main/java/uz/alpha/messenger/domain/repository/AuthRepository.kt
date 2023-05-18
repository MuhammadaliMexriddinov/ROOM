package uz.alpha.messenger.domain.repository

import kotlinx.coroutines.flow.SharedFlow

interface AuthRepository {
    val  signInGoogle : SharedFlow<Unit>
    fun signIn()
}
package uz.alpha.messenger.domain.repository.impl

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import uz.alpha.messenger.domain.repository.AuthRepository

class AuthRepositoryImpl : AuthRepository{
    override val signInGoogle= MutableSharedFlow<Unit>()


    override fun signIn() {

    }

}
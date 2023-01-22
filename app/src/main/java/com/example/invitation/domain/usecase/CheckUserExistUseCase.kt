package com.example.invitation.domain.usecase

import com.example.invitation.data.model.User
import com.example.invitation.domain.repository.InvitationRepository
import kotlinx.coroutines.flow.Flow

class CheckUserExistUseCase (
    private val invitationRepository: InvitationRepository
        ){

    fun execute(email:String) : User? {
        return invitationRepository.checkUser(email)
    }
}
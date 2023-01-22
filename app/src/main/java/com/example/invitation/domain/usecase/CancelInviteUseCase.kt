package com.example.invitation.domain.usecase

import com.example.invitation.data.model.User
import com.example.invitation.domain.repository.InvitationRepository

class CancelInviteUseCase(
    private val invitationRepository: InvitationRepository
) {
    suspend fun execute(user: User) = invitationRepository.cancelInvite(user)
}
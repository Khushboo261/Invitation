package com.example.invitation.domain.usecase

import com.example.invitation.data.model.User
import com.example.invitation.data.util.Resource
import com.example.invitation.domain.repository.InvitationRepository
import com.google.gson.JsonObject
import okhttp3.RequestBody

class GetInviteUseCase(
    private val invitationRepository:InvitationRepository
) {
    suspend fun execute(requestBody: JsonObject) : Resource<String> {
        return invitationRepository.getInvite(requestBody)
    }
}
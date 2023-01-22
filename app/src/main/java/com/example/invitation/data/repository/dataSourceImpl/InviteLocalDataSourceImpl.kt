package com.example.invitation.data.repository.dataSourceImpl

import com.example.invitation.data.db.UserDAO
import com.example.invitation.data.model.User
import com.example.invitation.data.repository.dataSource.InviteLocalDataSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class InviteLocalDataSourceImpl(
    private val userDAO: UserDAO
) : InviteLocalDataSource{

    override suspend fun saveUserToDB(user: User) {
        userDAO.insertUser(user)
    }

    override suspend fun deleteUserFromDB(user: User) {
        return userDAO.deleteUser(user)
    }

    override fun checkUserInDB(email: String): User? {
        return userDAO.checkUser(email)
    }

}
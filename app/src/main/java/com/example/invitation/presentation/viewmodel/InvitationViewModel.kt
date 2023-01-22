package com.example.invitation.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.invitation.data.model.User
import com.example.invitation.data.util.Resource
import com.example.invitation.domain.usecase.*
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class InvitationViewModel(
    private val app : Application,
    private val getInviteUseCase: GetInviteUseCase,
    private val saveInviteUseCase: SaveInviteUseCase,
    private val cancelInviteUseCase: CancelInviteUseCase,
    private val checkUserExistUseCase: CheckUserExistUseCase
) : AndroidViewModel(app) {


    private fun isNetworkAvailable(context: Context):Boolean{
        if(context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null){
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    //Get Invite

    val data : MutableLiveData<Resource<String>> = MutableLiveData()
    fun getInvite(requestBody: JsonObject) =
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(Resource.Loading())
            try {
                if (isNetworkAvailable(app)){
                    val response = getInviteUseCase.execute(
                        requestBody
                    )
                    data.postValue(response)
                }else{
                    data.postValue(Resource.Error("No Internet Connection"))
                }
            }catch (e:Exception){
                data.postValue(Resource.Error(e.message.toString()))
            }
        }

    //save data to database
    fun saveInvite(user: User) = viewModelScope.launch {
        saveInviteUseCase.execute(user)
    }

    //delete from database
    fun cancelInvite(user: User?) = viewModelScope.launch {
        if (user != null) {
            cancelInviteUseCase.execute(user)
        }
    }

    //Check if user exist
    val _checkUserLivedata : MutableLiveData<User?> = MutableLiveData()
    val checkUserLivedata = _checkUserLivedata
    fun checkUser(email: String) {
      val user =  checkUserExistUseCase.execute(email)
        _checkUserLivedata.postValue(user)
    }
}
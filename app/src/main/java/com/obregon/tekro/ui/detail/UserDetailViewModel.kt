package com.obregon.tekro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.data.response.UserSummary
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _users= MutableLiveData<List<UserSummary>>()
    var users:LiveData<List<UserSummary>> =_users

    fun getUsers(name:String){

     viewModelScope.launch {
         _users.value=userRepository.getUsers(name)
     }

    }
}
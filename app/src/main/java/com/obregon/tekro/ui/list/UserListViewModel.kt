package com.obregon.tekro.ui.list

import androidx.lifecycle.*
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.data.response.UserSummary
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _users= MutableLiveData<List<UserSummary>>()
    var users: LiveData<List<UserSummary>> =_users

    fun getUsers(name:String){

        viewModelScope.launch {
            _users.value=userRepository.getUsers(name)
        }

    }
}
package com.obregon.tekro.ui.list

import androidx.lifecycle.*
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.data.response.UserSummary
import com.obregon.tekro.ui.model.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _users= MutableLiveData<List<User>>()
    var users: LiveData<List<User>> =_users

    fun getUsers(name:String){
        viewModelScope.launch {
            val list=userRepository.getUsers(name)
            val list2=list.map { User(it.login,it.avatar_url) }
            _users.value=list2
        }
    }

    fun loadMore(){

    }

}
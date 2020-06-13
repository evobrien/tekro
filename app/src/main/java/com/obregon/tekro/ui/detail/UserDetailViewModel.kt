package com.obregon.tekro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.ui.model.User
import com.obregon.tekro.ui.model.UserDetails
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _user= MutableLiveData<UserDetails>()
    var users:LiveData<UserDetails> =_user

    fun getUser(name:String){

     viewModelScope.launch {
         val userDetail=userRepository.getUser(name)
        _user.value= UserDetails(userDetail.name,
            userDetail.public_repos,
            userDetail.public_gists,
            userDetail.followers,
            userDetail.following,
            userDetail.avatar_url)
     }

    }
}
package com.obregon.tekro.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obregon.tekro.data.repo.UserRepository
import com.obregon.tekro.ui.model.UserDetails
import kotlinx.coroutines.launch
import timber.log.Timber

class UserDetailViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel(){
    private var _user= MutableLiveData<UserDetails>()
    var users: LiveData<UserDetails> =_user

    fun getUser(name:String){
        Timber.d("name is->$name")
         viewModelScope.launch {
             try{
                 val userDetail=userRepository.getUser(name)
                _user.value= UserDetails(userDetail.name,
                    userDetail.public_repos,
                    userDetail.public_gists,
                    userDetail.followers,
                    userDetail.following,
                    userDetail.avatar_url)
             }catch(e:Exception){
                Timber.e(e)
             }
         }

    }
}
package com.obregon.tekro.ui.model

data class UserDetails(val displayName:String, val numPublicRepos:Int, val  numPublicGists:Int,
                       val numFollowers:Int, val numFollowing:Int, val avatar:String)

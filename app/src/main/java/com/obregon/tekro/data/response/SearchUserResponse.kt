package com.obregon.tekro.data.response

data class SearchUserResponse( val total_count:Int, val incomplete_results:Boolean,
                               val items:List<UserSummary>)
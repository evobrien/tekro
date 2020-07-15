package com.obregon.tekro.data.repo

/*import androidx.paging.PagingSource
import com.obregon.tekro.data.network.SearchUserApi
import com.obregon.tekro.data.response.SearchUserResponse
import com.obregon.tekro.data.response.UserSummary
import javax.inject.Inject

class UserPagedKeyDataSource @Inject constructor(private val searchUserApi: SearchUserApi,private val query: String) :
    PagingSource<Int, List<UserSummary>>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,List<UserSummary>> {
        var page:Int=1
        params.key?.let { page=it  }
        val response:SearchUserResponse=searchUserApi.getUserByPage(query, page)
        return LoadResult(page,response.items)
    }


}*/
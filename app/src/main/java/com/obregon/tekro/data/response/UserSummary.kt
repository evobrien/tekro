package com.obregon.tekro.data.response

/*
"login": "tom",
"id": 748,
"node_id": "MDQ6VXNlcjc0OA==",
"avatar_url": "https://avatars1.githubusercontent.com/u/748?v=4",
"gravatar_id": "",
"url": "https://api.github.com/users/tom",
"html_url": "https://github.com/tom",
"followers_url": "https://api.github.com/users/tom/followers",
"following_url": "https://api.github.com/users/tom/following{/other_user}",
"gists_url": "https://api.github.com/users/tom/gists{/gist_id}",
"starred_url": "https://api.github.com/users/tom/starred{/owner}{/repo}",
"subscriptions_url": "https://api.github.com/users/tom/subscriptions",
"organizations_url": "https://api.github.com/users/tom/orgs",
"repos_url": "https://api.github.com/users/tom/repos",
"events_url": "https://api.github.com/users/tom/events{/privacy}",
"received_events_url": "https://api.github.com/users/tom/received_events",
"type": "User",
"site_admin": false,
"score": 1.0*/
data class UserSummary(val login:String, val id:Int, val node_id:String, val avatar_url:String, val gravatar_id:String,
                       val url:String, val html_url:String, val followers_url:String,
                       val following_url:String, val gists_url:String, val starred_url:String,
                       val subscriptions_url:String, val organizations_url:String, val repos_url:String,
                       val events_url:String, val received_events_url:String, val type:String,
                       val site_admin:Boolean, val score:Double )
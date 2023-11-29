package com.example.demo_06.network.res


data class UserLoginRes (
    val personalNo: String,
    val password: String,
    val appAuthority: Int=0,
)
package com.example.demo_06.network.res

import java.sql.ClientInfoStatus

data class BaseResponse<T> (
    val status: String,
    val message: String,
    var data: T?=null
)
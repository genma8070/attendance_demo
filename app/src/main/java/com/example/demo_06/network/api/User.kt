package com.example.demo_06.network.api

import com.example.demo_06.model.HolidayAcquireInfo
import com.example.demo_06.model.HolidayRecordInfo
import com.example.demo_06.model.LoginInfo
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.demo_06.network.res.UserHolidayRecordRes
import com.example.demo_06.network.res.UserLoginRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.lang.invoke.MethodHandleInfo

interface User {

//  ログイン
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/login")
    fun login(@Body loginInfo: LoginInfo): Call<BaseResponse<UserLoginRes>>

//  休暇申込
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_acquire")
    fun holidayAcquire(@Body holidayAcquireInfo: HolidayAcquireInfo): Call<BaseResponse<UserHolidayAcquireRes>>

    //  休暇記録
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_record")
    fun holidayRecord(@Body holidayRecordInfo: HolidayRecordInfo): Call<BaseResponse<List<UserHolidayRecordRes>>>

}
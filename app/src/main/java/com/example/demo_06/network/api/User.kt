package com.example.demo_06.network.api

import com.example.demo_06.model.HolidayAcquireReq
import com.example.demo_06.model.HolidayFinalReviewDeniedReq
import com.example.demo_06.model.HolidayRecordReq
import com.example.demo_06.model.HolidayReviewAcceptReq
import com.example.demo_06.model.HolidayReviewDeniedReq
import com.example.demo_06.model.HolidayReviewReq
import com.example.demo_06.model.LoginReq
import com.example.demo_06.model.WorkSpotReq
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.demo_06.network.res.HolidayAcquire
import com.example.demo_06.network.res.UserLoginRes
import com.example.demo_06.network.res.SearchBelongWorkSpotRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface User {

//  ログイン
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/login")
    fun Login(@Body loginReq: LoginReq): Call<BaseResponse<UserLoginRes>>

//  担当現場
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/search_belong_work_spot")
    fun WorkSpot(@Body workSpotInfo: WorkSpotReq): Call<BaseResponse<SearchBelongWorkSpotRes>>

//  休暇申込
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_acquire")
    fun HolidayAcquire(@Body holidayAcquireReq: HolidayAcquireReq): Call<BaseResponse<UserHolidayAcquireRes>>

//  休暇記録
    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_record")
    fun HolidayRecord(@Body holidayRecordReq: HolidayRecordReq): Call<BaseResponse<List<HolidayAcquire>>>


    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_review")
    fun HolidayReview(@Body holidayReviewReq: HolidayReviewReq): Call<BaseResponse<List<HolidayAcquire>>>

    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_review_accept")
    fun HolidayReviewAccept(@Body holidayReviewAcceptReq: HolidayReviewAcceptReq): Call<BaseResponse<String>>

    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_review_denied")
    fun HolidayReviewDenied(@Body holidayReviewDeniedReq: HolidayReviewDeniedReq): Call<BaseResponse<String>>

    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_final_review")
    fun HolidayFinalReview(): Call<BaseResponse<List<HolidayAcquire>>>

    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_final_review_accept")
    fun HolidayFinalReviewAccept(@Body holidayReviewAcceptReq: HolidayReviewAcceptReq): Call<BaseResponse<String>>

    @Headers("Content-type:application/json;charset=utf-8","Accept:application/json")
    @POST("user/holiday_final_review_denied")
    fun HolidayFinalReviewDenied(@Body holidayReviewFinalDeniedReq: HolidayFinalReviewDeniedReq): Call<BaseResponse<String>>

}
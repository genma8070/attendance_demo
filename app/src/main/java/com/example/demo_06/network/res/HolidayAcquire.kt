package com.example.demo_06.network.res

//data class UserLoginRes (
//    val user: Int,
//    val account: String,
//    val password: String,
//    val nickname: String,
//)

data class HolidayAcquire (
    val holidayAcquireNo: String="",
    val workSpotDepart: String="",
    val selectedWorkSpot: String="",
    val startYear: String="",
    val startMonth: String="",
    val startDay: String="",
    val startTime: String="",
    val endYear: String="",
    val endMonth: String="",
    val endDay: String="",
    val endTime: String="",
    val vacationDays: Int=0,
    val vacationNo: String="",
    val reason: String="",
    val approvalCtg: String="",
    val refusal: String="",
    val comment: String="",
    val regYear: String="",
    val regMonth: String="",
    val regDay: String="",
    val regAuthor: String="",
    val uptYear: String="",
    val uptMonth: String="",
    val uptDay: String="",
    val uptAuthor: String="",
    val delFlg: String="",


)
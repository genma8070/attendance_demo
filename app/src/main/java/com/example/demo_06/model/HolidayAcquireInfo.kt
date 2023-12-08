package com.example.demo_06.model

import org.json.JSONArray

data class HolidayAcquireInfo (
    val personalNo: String="",
    val selectedWorkSpot: Array<String>,
    val startDate: String="",
    val startTime: String="",
    val endDate: String="",
    val endTime: String="",
    val leaveType: String="",
    val reason: String="",

)
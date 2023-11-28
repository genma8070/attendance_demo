package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveRecordBinding
import com.example.demo_06.mainnav.accountPublic
import com.example.demo_06.model.HolidayAcquireInfo
import com.example.demo_06.model.HolidayRecordInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.demo_06.network.res.UserHolidayRecordRes
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeLeaveRecordFragment: BaseFragment<FragmentEmployeeLeaveRecordBinding, PublicViewModel>(
    FragmentEmployeeLeaveRecordBinding::inflate,
    PublicViewModel::class.java,
    true
){
    private fun showAlertDialog(start: String, end: String, status: String, update: String) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()
        alertDialog.setTitle("詳細")

        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(20, 20, 20, 20)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val startText = SpannableString("開始時刻     : $start\n")
        startText.setSpan(ForegroundColorSpan(Color.BLACK), 0, startText.length, 0)

        val startTextView = TextView(requireContext())
        startTextView.textSize = 20F
        startTextView.text = startText
        linearLayout.addView(startTextView, params)

        val endText = SpannableString("終了時刻     : $end\n")
        endText.setSpan(ForegroundColorSpan(Color.BLACK), 0, endText.length, 0)

        val endTextView = TextView(requireContext())
        endTextView.textSize = 20F
        endTextView.text = endText
        linearLayout.addView(endTextView, params)

        val statusText = SpannableString("ステータス : $status\n")
        statusText.setSpan(ForegroundColorSpan(Color.BLACK), 0, statusText.length, 0)

        val statusTextView = TextView(requireContext())
        statusTextView.textSize = 20F
        statusTextView.text = statusText
        linearLayout.addView(statusTextView, params)

        val updateText = SpannableString("更新時刻     : $update")
        updateText.setSpan(ForegroundColorSpan(Color.BLACK), 0, updateText.length, 0)

        val updateTextView = TextView(requireContext())
        updateTextView.textSize = 20F
        updateTextView.text = updateText
        linearLayout.addView(updateTextView, params)

        alertDialog.setView(linearLayout)

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, _ ->
            dialog.dismiss()
        }

        alertDialog.show()
    }



    override fun initFragment(
        binding: FragmentEmployeeLeaveRecordBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {


        binding.tableLayout1.let {
            it.isStretchAllColumns = true

            var regAuthor = accountPublic

            RequestBuilder().getAPI(User::class.java).holidayRecord(HolidayRecordInfo(regAuthor))
                .enqueue(object : Callback<BaseResponse<List<UserHolidayRecordRes>>> {
                    @SuppressLint("ResourceType")
                    override fun onResponse(
                        call: Call<BaseResponse<List<UserHolidayRecordRes>>>?,
                        response: Response<BaseResponse<List<UserHolidayRecordRes>>>?
                    ) {
                        response?.let {
                            if(it.body().data != null) {
                                if(it.body().status == "200"){
//                                    Toast.makeText(
//                                        requireContext(),
//                                        "${it.body().message}",
//                                        Toast.LENGTH_SHORT).show()

                                    if(it.body().data != null) {
                                        it.body().data.let {
                                            var counter = 0
//                                            var itTemp: UserHolidayRecordRes? = null
                                            it?.forEach {item ->
//                                                itTemp = it
                                                val row = TableRow(requireContext())
                                                row.setPadding(0, 40, 0, 40)

                                                val member1 = TextView(requireContext())
                                                member1.textSize = 20F
                                                member1.width = 90
                                                member1.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                row.addView(member1)

                                                val member2 = TextView(requireContext())
                                                member2.textSize = 20F
                                                member2.width = 90
                                                member2.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                row.addView(member2)

                                                val member3 = TextView(requireContext())
                                                member3.textSize = 20F
                                                member3.width = 90
                                                member3.text = "?"
                                                row.addView(member3)

                                                val member4 = TextView(requireContext())
                                                member4.textSize = 20F
                                                member4.width = 90
                                                member4.text = item.uptYear.substring(2) + "/" + item.uptMonth + "/" + item.uptDay
                                                row.addView(member4)

                                                row.tag = counter
                                                counter++

                                                member1.setOnClickListener {
                                                    showAlertDialog(
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
                                                        "?",
                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
                                                    )
                                                }

                                                member2.setOnClickListener {
                                                    showAlertDialog(
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
                                                        "?",
                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
                                                    )
                                                }

                                                member3.setOnClickListener {
                                                    showAlertDialog(
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
                                                        "?",
                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
                                                    )
                                                }

                                                member4.setOnClickListener {
                                                    showAlertDialog(
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
                                                        "?",
                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
                                                    )
                                                }

                                                binding.tableLayout1.addView(row)
                                            }


                                        }
                                    }

                                }

                            }else {
                                Toast.makeText(
                                    requireContext(),
                                    "${it.body().message}",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<List<UserHolidayRecordRes>>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })

        }

    }

}
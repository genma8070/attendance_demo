package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
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
    override fun initFragment(
        binding: FragmentEmployeeLeaveRecordBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {
//        viewModel!!.testValue.observe(requireActivity()){
//            binding.value.text=it
//        }
//        binding.btnValue.setOnClickListener {
////            requireActivity().findNavController(R.id.app_nav).navigate(R.id.action_mainNavFragment_to_otherFragment)
//            viewModel.testValue.value = "你好 homeViewModel"
//        }
//        binding.btnPubValue.setOnClickListener {
//            publicViewModel?.apply {
//                this.testValue.value="home改變了public的值"
//            }
//        }
//        binding.btnRecordDetail01.setOnClickListener {
//            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_employeeLeaveRecordFragment_to_leaveRecordDetail01Fragment)
//        }
//        binding.btnRecordDetail02.setOnClickListener {
//            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_employeeLeaveRecordFragment_to_leaveRecordDetail02Fragment)
//        }

        binding.tableLayout1.let {
            it.isStretchAllColumns = true

//            for(i in 1..30) {
//
//                val row = TableRow(requireContext())
//                row.setPadding(0,40,0,40)
//
//                val member1 = TextView(requireContext())
//                member1.textSize = 20F
//                member1.text = "23/01/01"
//                row.addView(member1)
//
//                val member2 = TextView(requireContext())
//                member2.textSize = 20F
//                member2.text = "23/01/01"
//                row.addView(member2)
//
//                val member3 = TextView(requireContext())
//                member3.textSize = 20F
//                member3.text = "現場審査中"
//                row.addView(member3)
//
//                val member4 = TextView(requireContext())
//                member4.textSize = 20F
//                member4.text = "23/01/01"
//                row.addView(member4)
//
//                it.addView(row)
//            }

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
                                            it?.forEach {

                                                val row = TableRow(requireContext())
                                                row.setPadding(0, 40, 0, 40)

                                                val buttonRow = TableRow(requireContext())
                                                buttonRow.setPadding(0, 40, 0, 40)

                                                val member0 = Button(requireContext())
                                                member0.width = 40
                                                member0.text = "test" + counter
                                                member0.id = counter
                                                counter++
                                                buttonRow.addView(member0)

                                                val member1 = TextView(requireContext())
                                                member1.textSize = 20F
                                                member1.width = 90
                                                member1.text = it.startYear.substring(2) + "/" + it.startMonth + "/" + it.startDay
                                                row.addView(member1)

                                                val member2 = TextView(requireContext())
                                                member2.textSize = 20F
                                                member2.width = 90
                                                member2.text = it.endYear.substring(2) + "/" + it.endMonth + "/" + it.endDay
                                                row.addView(member2)

                                                val member3 = TextView(requireContext())
                                                member3.textSize = 20F
                                                member3.width = 90
                                                member3.text = "?"
                                                row.addView(member3)

                                                val member4 = TextView(requireContext())
                                                member4.textSize = 20F
                                                member4.width = 90
                                                member4.text = it.uptYear.substring(2) + "/" + it.uptMonth + "/" + it.uptDay
                                                row.addView(member4)

                                                binding.tableLayout1.addView(buttonRow)
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
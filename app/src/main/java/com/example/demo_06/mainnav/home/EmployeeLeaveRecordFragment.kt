package com.example.demo_06.mainnav.home

import android.os.Bundle
import android.widget.TableRow
import android.widget.TextView
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveRecordBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel


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

            for(i in 1..30) {

                val row = TableRow(requireContext())
                row.setPadding(0,40,0,40)

                val member1 = TextView(requireContext())
                member1.textSize = 20F
                member1.text = "23/01/01"
                row.addView(member1)

                val member2 = TextView(requireContext())
                member2.textSize = 20F
                member2.text = "23/01/01"
                row.addView(member2)

                val member3 = TextView(requireContext())
                member3.textSize = 20F
                member3.text = "現場審査中"
                row.addView(member3)

                val member4 = TextView(requireContext())
                member4.textSize = 20F
                member4.text = "23/01/01"
                row.addView(member4)

                it.addView(row)
            }


        }

    }

}
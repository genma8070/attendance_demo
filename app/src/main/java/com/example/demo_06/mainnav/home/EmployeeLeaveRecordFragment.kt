package com.example.demo_06.mainnav.home

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.demo_06.databinding.FragmentEmployeeLeaveRecordBinding
import com.example.demo_06.databinding.FragmentHomeBinding
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
        binding.btnRecordDetail01.setOnClickListener {
            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_employeeLeaveRecordFragment_to_leaveRecordDetail01Fragment)
        }
    }

}
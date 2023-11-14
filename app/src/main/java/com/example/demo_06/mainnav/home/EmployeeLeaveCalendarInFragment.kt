package com.example.demo_06.mainnav.home

import android.os.Bundle
import androidx.navigation.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveCalendarBinding
import com.example.demo_06.databinding.FragmentEmployeeLeaveCalendarInBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel

class EmployeeLeaveCalendarInFragment: BaseFragment<FragmentEmployeeLeaveCalendarInBinding, PublicViewModel>(
    FragmentEmployeeLeaveCalendarInBinding::inflate,
    PublicViewModel::class.java,
    true
){
    override fun initFragment(
        binding: FragmentEmployeeLeaveCalendarInBinding,
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
//        binding.employeeCalendarIn.setOnClickListener {
//            requireActivity().findNavController(R.id.app_nav).navigate(R.id.action_mainNavFragment_to_otherFragment)
//        }
    }

}
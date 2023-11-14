package com.example.demo_06.mainnav.home

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel


class EmployeeLeaveApplicationFragment: BaseFragment<FragmentEmployeeLeaveApplicationBinding, PublicViewModel>(
    FragmentEmployeeLeaveApplicationBinding::inflate,
    PublicViewModel::class.java,
    true
){
    override fun initFragment(
        binding: FragmentEmployeeLeaveApplicationBinding,
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
//        binding.datePicker.setOnClickListener {
//
//        }

//        binding.btnLogout.setOnClickListener{
//            findNavController().navigate(
//                com.example.demo_06.R.id.loginFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(com.example.demo_06.R.id.manageNavFragment,true).build())
//        }
    }

}
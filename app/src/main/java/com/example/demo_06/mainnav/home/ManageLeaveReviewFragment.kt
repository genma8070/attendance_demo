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
import com.example.demo_06.databinding.FragmentManageLeaveRecordBinding
import com.example.demo_06.databinding.FragmentManageLeaveReviewBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel

class ManageLeaveReviewFragment: BaseFragment<FragmentManageLeaveReviewBinding, PublicViewModel>(
    FragmentManageLeaveReviewBinding::inflate,
    PublicViewModel::class.java,
    true
){
    override fun initFragment(
        binding: FragmentManageLeaveReviewBinding,
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
        binding.btnReviewSecond.setOnClickListener {
            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_manageLeaveReviewFragment_to_manageLeaveReviewSecondFragment)
        }
        binding.btnReviewFirst02.setOnClickListener {
            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_manageLeaveReviewFragment_to_manageLeaveReviewFirst02Fragment)
        }
        binding.btnReviewFirst001.setOnClickListener {
            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_manageLeaveReviewFragment_to_manageLeaveReviewFirstFragment)
        }
        binding.btnReviewFirst002.setOnClickListener {
            requireActivity().findNavController(R.id.employee_view_nav).navigate(R.id.action_manageLeaveReviewFragment_to_manageLeaveReviewFirst03Fragment)
        }
    }

}
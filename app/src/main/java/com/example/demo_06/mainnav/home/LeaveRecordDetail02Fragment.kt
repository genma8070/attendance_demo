package com.example.demo_06.mainnav.home

import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.demo_06.databinding.FragmentEmployeeLeaveRecordBinding
import com.example.demo_06.databinding.FragmentHomeBinding
import com.example.demo_06.databinding.FragmentLeaveRecordDetail01Binding
import com.example.demo_06.databinding.FragmentLeaveRecordDetail02Binding
import com.example.demo_06.databinding.FragmentManageLeaveRecordBinding
import com.example.demo_06.databinding.FragmentManageLeaveReviewBinding
import com.example.demo_06.databinding.FragmentManageLeaveReviewFirst02Binding
import com.example.demo_06.databinding.FragmentManageLeaveReviewFirstBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel

class LeaveRecordDetail02Fragment: BaseFragment<FragmentLeaveRecordDetail02Binding, PublicViewModel>(
    FragmentLeaveRecordDetail02Binding::inflate,
    PublicViewModel::class.java,
    true
){
    override fun initFragment(
        binding: FragmentLeaveRecordDetail02Binding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {
        binding.backList.setOnClickListener {
            // 处理按钮点击事件，例如返回上一层
            findNavController().navigateUp()
        }
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
    }

}
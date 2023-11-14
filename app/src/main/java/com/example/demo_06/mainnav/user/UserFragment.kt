package com.example.demo_06.mainnav.user

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentUserBinding

class UserFragment: BaseFragment<FragmentUserBinding, ViewModel>(
    FragmentUserBinding::inflate,
    viewModelClass = null,
    true
) {
    override fun initFragment(
        binding: FragmentUserBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
        publicViewModel!!.testValue.observe(requireActivity()){
            binding.pubValue.text=it
        }
    }
}
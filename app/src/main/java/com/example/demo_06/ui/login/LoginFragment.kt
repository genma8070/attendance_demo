package com.example.demo_06.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentLoginBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel


class LoginFragment: BaseFragment<FragmentLoginBinding,ViewModel>(
    FragmentLoginBinding::inflate,
    null
) {
    override fun initFragment(
        binding: FragmentLoginBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
        binding.loginBtnEmployee.setOnClickListener{
            findNavController().navigate(
                R.id.employeeNavFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
        }
        binding.loginBtnManage.setOnClickListener{
            findNavController().navigate(
                R.id.manageNavFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
        }
    }

}
package com.example.demo_06.mainnav

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeNavBinding

var accountPublic=""

class EmployeeNavFragment: BaseFragment<FragmentEmployeeNavBinding, ViewModel>(
    FragmentEmployeeNavBinding::inflate,
    viewModelClass = null
){
    override fun initFragment(
        binding: FragmentEmployeeNavBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {

        arguments?.let {
            val value = it.getString("account")
            binding.account.setText(value)
//            accountPublic.let {
//                value
//            }
            if (value != null) {
                accountPublic = value
            }
        }

        (childFragmentManager.findFragmentById(R.id.employee_view_nav) as NavHostFragment).apply {
            binding.employeeBottomNav.setupWithNavController(this.navController)
        }

//        binding.btnLogout.setOnClickListener {
//            requireActivity().findNavController(R.id.app).navigate(R.id.action_employeeNavFragment_to_loginFragment)
//        }

        binding.btnLogout.setOnClickListener{
            accountPublic = ""
            findNavController().navigate(
                R.id.loginFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.employeeNavFragment,true).build())
        }

    }
}
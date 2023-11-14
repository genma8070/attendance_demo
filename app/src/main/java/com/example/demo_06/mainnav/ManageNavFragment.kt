package com.example.demo_06.mainnav

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeNavBinding
import com.example.demo_06.databinding.FragmentManageNavBinding

class ManageNavFragment: BaseFragment<FragmentManageNavBinding, ViewModel>(
    FragmentManageNavBinding::inflate,
    viewModelClass = null
){
    override fun initFragment(
        binding: FragmentManageNavBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
        (childFragmentManager.findFragmentById(R.id.employee_view_nav) as NavHostFragment).apply {
            binding.manageBottomNav.setupWithNavController(this.navController)
        }

        binding.btnLogout.setOnClickListener{
            findNavController().navigate(
                R.id.loginFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.manageNavFragment,true).build())
        }
    }
}
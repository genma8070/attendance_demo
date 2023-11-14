package com.example.mvvm_learning.setruth.mvvmlearn.ui.view.other

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentOtherBinding

class OtherFragment: BaseFragment<FragmentOtherBinding, ViewModel>(
    FragmentOtherBinding::inflate,
    viewModelClass = null
) {
    override fun initFragment(
        binding: FragmentOtherBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {

    }
}
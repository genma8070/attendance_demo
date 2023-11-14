package com.example.demo_06.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel


abstract class BaseFragment<VB:ViewBinding,VM:ViewModel>(
    private val inflate:(LayoutInflater, ViewGroup?, Boolean)->VB,
    private val viewModelClass: Class<VM>?,
    private val publicViewModelTag: Boolean=false
): Fragment() {
    private val viewModel by lazy {
        val viewModelProvider = ViewModelProvider(this)
        viewModelClass?.let {
            viewModelProvider[it]
        }
    }
    public val publicViewModel: PublicViewModel? by lazy {
        if(publicViewModelTag) {
            ViewModelProvider(requireActivity())[PublicViewModel::class.java]
        }
        else {
            null
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflate(inflater, container, false)
        initFragment(binding,viewModel,savedInstanceState)
        return binding.root
    }
    abstract fun initFragment(binding:VB, viewModel:VM?, savedInstanceState: Bundle?)
}
package com.example.demo_06.mainnav.home

import android.renderscript.Sampler.Value
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    val testValue by lazy {
        MutableLiveData("")
    }
}
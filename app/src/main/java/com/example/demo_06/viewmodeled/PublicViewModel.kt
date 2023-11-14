package com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PublicViewModel:ViewModel() {
    val testValue by lazy {
        MutableLiveData("")
    }
}
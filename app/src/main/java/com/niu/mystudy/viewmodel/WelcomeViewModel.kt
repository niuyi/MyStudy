package com.niu.mystudy.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.niu.mystudy.DataLoader

class WelcomeViewModel : ViewModel() {

    private val info: MutableLiveData<String> by lazy {
        MutableLiveData<String>().also {
            loadUsers()
        }
    }

    fun getInfo(): LiveData<String> {
        return info
    }

    private fun loadUsers() {
        Thread{
            if(info != null){
                info.postValue("正在初始化")
                DataLoader.loadData()
                info.postValue("初始化结束")
            }
        }.start()
    }
}
package com.example.mycinema.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycinema.AppState
import com.example.mycinema.Model.DB
import java.lang.Thread.sleep

class MainViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveData
    }

    fun getFilmsData() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        Thread {
            for (film in DB.getFilmsFromServer()){
                liveData.postValue(AppState.Loading)
                sleep(1000)
                liveData.postValue(AppState.Success(film))
                sleep(1000)
            }
        }.start()
    }
}
package com.example.mycinema.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycinema.AppState
import com.example.mycinema.model.DB
import com.example.mycinema.model.Repository
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = DB
) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        getFilmsFromRemoteSource()
        return liveData
    }

    fun getFilmsFromLocalSourceRus() = getDataFromLocalSource()

    fun getFilmsFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        Thread {
            liveData.postValue(AppState.Loading)
            sleep(500)
            liveData.postValue(AppState.Success(db.getFilmsFromLocalStorage()))
            sleep(500)
        }.start()
    }
}
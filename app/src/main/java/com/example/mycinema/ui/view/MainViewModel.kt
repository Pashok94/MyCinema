package com.example.mycinema.ui.view

import android.util.Log
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

    fun getFilmsFromLocalSourceRus() = db.getFilmsFromLocalStorage()

    private fun getFilmsFromRemoteSource() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        Thread {
            db.getFilmsFromServer()
            liveData.postValue(AppState.Loading)
            while (db.getFilmsFromLocalStorage().isEmpty()) {
                sleep(500)
            }
            liveData.postValue(AppState.Success(db.getFilmsFromLocalStorage()))
        }.start()
    }
}
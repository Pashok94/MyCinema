package com.example.mycinema.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycinema.AppState
import com.example.mycinema.model.ListFilms
import com.example.mycinema.model.repository.DB
import com.example.mycinema.model.repository.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = DB
) : ViewModel() {

    private val callback = object : Callback<ListFilms> {
        override fun onResponse(call: Call<ListFilms>, response: Response<ListFilms>) {
            val serverResponse: ListFilms? = response.body()
            Log.d("mayTag", "${serverResponse?.results?.size}   =====   ${response.message()}")
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    db.getFilmsFromLocalStorage().clear()
                    db.getFilmsFromLocalStorage().addAll(serverResponse.results)
                    AppState.Success(serverResponse.results)
                } else {
                    AppState.Error(Throwable())
                }
            )
        }

        override fun onFailure(call: Call<ListFilms>, t: Throwable) {
            liveData.postValue(
                AppState.Error(Throwable(t.message))
            )
        }

    }

    fun getLiveData(): LiveData<AppState> {
        getFilmsFromRemoteSource()
        return liveData
    }

    fun getFilmsFromLocalSourceRus() = db.getFilmsFromLocalStorage()

    private fun getFilmsFromRemoteSource() {
        liveData.value = AppState.Loading
        db.getFilmsFromServer(1, callback)
    }
}
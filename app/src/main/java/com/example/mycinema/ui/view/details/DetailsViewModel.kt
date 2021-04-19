package com.example.mycinema.ui.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycinema.AppState
import com.example.mycinema.MainActivity
import com.example.mycinema.model.Result
import com.example.mycinema.model.repository.Repository
import com.example.mycinema.model.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = RepositoryImpl(MainActivity.getFilmsDao())
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    private val callback = object :Callback<Result> {
        override fun onResponse(call: Call<Result>, response: Response<Result>) {
            val serverResponse: Result? = response.body()
            liveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    AppState.SuccessLoadDetails(serverResponse)
                } else {
                    AppState.Error(Throwable())
                }
            )
        }

        override fun onFailure(call: Call<Result>, t: Throwable) {
            liveData.postValue(
                AppState.Error(Throwable(t.message))
            )
        }

    }

    fun loadFilmById(id: Int){
        db.getFilmById(id, callback)
    }

    fun addFilmToFavorites(film: Result) {
        db.saveEntity(film)
    }
}
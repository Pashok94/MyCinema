package com.example.mycinema.ui.view.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mycinema.AppState
import com.example.mycinema.MainActivity
import com.example.mycinema.model.repository.Repository
import com.example.mycinema.model.repository.RepositoryImpl

class FavoritesViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val db: Repository = RepositoryImpl(MainActivity.getFilmsDao())
) : ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        getFavoriteListFilmFromDb()
        return liveData
    }

    private fun getFavoriteListFilmFromDb(){
        liveData.postValue(AppState.Success(db.getFilmsFromLocalStorage()))
    }
}
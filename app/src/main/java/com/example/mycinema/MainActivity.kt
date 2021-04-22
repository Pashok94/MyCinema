package com.example.mycinema

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.mycinema.databinding.MainActivityBinding
import com.example.mycinema.model.room.FilmsDao
import com.example.mycinema.model.room.FilmsDataBase
import com.example.mycinema.ui.view.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContext = this.applicationContext
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        private lateinit var appContext: Context
        private var db: FilmsDataBase? = null
        private const val DB_NAME = "Films.db"

        fun getFilmsDao(): FilmsDao {
            if (db == null) {
                synchronized(FilmsDataBase::class.java) {
                    db = Room.databaseBuilder(
                        appContext,
                        FilmsDataBase::class.java,
                        DB_NAME)
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return db!!.filmsDao()
        }
    }
}
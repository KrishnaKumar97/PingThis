package com.example.githubexplorer

import android.app.Application
import androidx.room.Room
import com.example.githubexplorer.database.RepositoryDatabase

class ApplicationClass : Application() {

    companion object {
        lateinit var repositoryDatabase: RepositoryDatabase
    }

    /**
     * Builds the database object using Room databaseBuilder
     */
    override fun onCreate() {
        super.onCreate()
        repositoryDatabase = Room.databaseBuilder(
            applicationContext,
            RepositoryDatabase::class.java,
            "RepositoryDB"
        ).fallbackToDestructiveMigration().build()

    }
}
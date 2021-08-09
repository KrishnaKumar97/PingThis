package com.example.githubexplorer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails

@Database(entities = [RepositoryDetails::class], version = 2)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract val repositoryDAO: RepositoryDAO
}
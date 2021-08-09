package com.example.githubexplorer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails

@Dao
interface RepositoryDAO {
    @Insert
    fun insertRepositoriesList(listOfRepositories: List<RepositoryDetails>)

    @Query("SELECT * FROM RepositoryWatchList")
    fun fetchAllRepositories(): LiveData<List<RepositoryDetails>>
}
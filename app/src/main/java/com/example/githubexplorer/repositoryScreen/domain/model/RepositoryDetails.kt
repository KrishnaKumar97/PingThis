package com.example.githubexplorer.repositoryScreen.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("items")
    val items: List<RepositoryDetails>?
)

@Entity(tableName = "RepositoryWatchList")
data class RepositoryDetails(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("pulls_url")
    val pullsUrl: String?,
    @SerializedName("open_issues_count")
    val openIssuesCount: String?,
    var isSelected: Boolean = false
)
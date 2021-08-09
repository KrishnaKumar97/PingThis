package com.example.githubexplorer.repositoryScreen.domain.usecase.insertRepositories

import com.example.githubexplorer.repositoryScreen.domain.model.RepositoryDetails

interface InsertRepositoriesUseCase {
    fun invoke(listOfRepositories: List<RepositoryDetails>)
}
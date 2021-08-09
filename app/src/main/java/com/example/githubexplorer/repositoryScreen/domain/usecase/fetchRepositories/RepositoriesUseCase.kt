package com.example.githubexplorer.repositoryScreen.domain.usecase.fetchRepositories

interface RepositoriesUseCase {
    fun invoke(searchText: String)
}
package kost.romi.repocommittimeline.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kost.romi.repocommittimeline.api.GitHubService
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGitHubService(): GitHubService {
        return GitHubService.create()
    }

}
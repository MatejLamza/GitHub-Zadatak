package com.example.git_zadatak.dependency

import android.content.Context
import androidx.room.Room
import com.example.git_zadatak.base.BaseApp
import com.example.git_zadatak.data.network.GitDataSource
import com.example.git_zadatak.data.network.GitDataSourceImpl
import com.example.git_zadatak.data.database.GitDAO
import com.example.git_zadatak.data.database.GitDatabase
import com.example.git_zadatak.data.network.ConnectivityDetection
import com.example.git_zadatak.data.network.ConnectivityDetectionImpl
import com.example.git_zadatak.data.network.service.GitService
import com.example.git_zadatak.data.repositories.GitRepo
import com.example.git_zadatak.data.repositories.GitRepoImpl
import com.example.git_zadatak.utils.MyConsts
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: BaseApp):Context{
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGitService(interceptor:ConnectivityDetection): GitService {

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(MyConsts.GITHUB_API_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitService::class.java)
    }

    @Provides
    @Singleton
    fun provideConnectivityDetection(conDetectionImpl:ConnectivityDetectionImpl):ConnectivityDetection{
        return conDetectionImpl
    }

    @Provides
    @Singleton
    fun provideGitDataSource(gitDataSourceImpl: GitDataSourceImpl): GitDataSource {
        return gitDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideGitRepo(gitRepoImpl: GitRepoImpl):GitRepo{
        return gitRepoImpl
    }

    @Provides
    @Singleton
    fun provideGitDatabase(context: Context):GitDatabase{
        return Room.databaseBuilder(
            context,
            GitDatabase::class.java,
            MyConsts.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideGitDAO(db:GitDatabase):GitDAO{
        return db.getGitDAO()
    }

}
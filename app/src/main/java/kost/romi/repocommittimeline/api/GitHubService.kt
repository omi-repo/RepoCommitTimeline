package kost.romi.repocommittimeline.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//interface GitHubService {
//
//    /*
//    * Find repositories via various criteria.
//    * This method returns up to 100 results per page.
//    */
//    @GET("/search/users")
//    suspend fun searchForRepo(
//        @Query("q") q: String
//    ): String
//
////    companion object {
////        private const val BASE_URL = "https://api.unsplash.com/"
////
////        fun create(): UnsplashService {
////            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }
////
////            val client = OkHttpClient.Builder()
////                .addInterceptor(logger)
////                .build()
////
////            return Retrofit.Builder()
////                .baseUrl(BASE_URL)
////                .client(client)
////                .addConverterFactory(GsonConverterFactory.create())
////                .build()
////                .create(UnsplashService::class.java)
////        }
////    }
//
//
//}
package com.codebros.eripple.data.network

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.model.sample.Post
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    /*@GET("todos/1/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") id : Int
    ): Response<List<Post>>

    @GET("photos")
    suspend fun getPhotos() : Response<List<SamplePhotoEntity>>*/


    @GET("loginAccount")
    suspend fun loginAccount(
        @Query("email") id: String,
        @Query("psw") psw: String
    ): Response<Int>


    @POST("joinAccount")
    suspend fun joinAccount(
        @Field("name") name :String,
        @Field("phone") phone : String,
        @Field("password") password : String,
        @Field("email") email : String,
    )


}
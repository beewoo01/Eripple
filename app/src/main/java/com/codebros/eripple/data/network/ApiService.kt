package com.codebros.eripple.data.network

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.model.sample.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("todos/1/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("comments")
    suspend fun getComments(
        @Query("postId") id : Int
    ): Response<List<Post>>

    @GET("photos")
    suspend fun getPhotos() : Response<List<SamplePhotoEntity>>
}
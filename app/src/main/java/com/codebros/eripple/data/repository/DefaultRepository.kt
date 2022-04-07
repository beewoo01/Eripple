package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.model.sample.Post
import retrofit2.Response

interface DefaultRepository {

    suspend fun getComments(id: Int): Response<List<Post>>

    suspend fun getPosts(): Response<List<Post>>

    suspend fun getPhotos() : Response<List<SamplePhotoEntity>>

}
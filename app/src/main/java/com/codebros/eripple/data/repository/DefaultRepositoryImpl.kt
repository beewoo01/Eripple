package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.model.sample.Post
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response

class DefaultRepositoryImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : DefaultRepository {

    override suspend fun getComments(id: Int): Response<List<Post>> = apiService.getComments(id)

    override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()

    override suspend fun getPhotos(): Response<List<SamplePhotoEntity>> = apiService.getPhotos()


}
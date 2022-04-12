package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.data.url.RetrofitGenerator
import com.codebros.eripple.model.sample.Post
import retrofit2.Response

class SampleRepository : ApiService {

    /*private val apiService: ApiService = RetrofitGenerator.getApiService()

    override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()

    override suspend fun getComments(id: Int): Response<List<Post>> = apiService.getComments(id = id)

    override suspend fun getPhotos(): Response<List<SamplePhotoEntity>> = apiService.getPhotos()*/

}
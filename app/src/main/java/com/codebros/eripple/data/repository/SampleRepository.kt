package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.data.url.RetrofitGenerator
import com.codebros.eripple.model.sample.Post
import retrofit2.Response

class SampleRepository /*: ApiService*/ {

    private val apiService: ApiService = RetrofitGenerator.getApiService()
    /*private val apiService: ApiService = RetrofitGenerator.getApiService()

    override suspend fun getPosts(): Response<List<Post>> = apiService.getPosts()

    override suspend fun getComments(id: Int): Response<List<Post>> = apiService.getComments(id = id)

    override suspend fun getPhotos(): Response<List<SamplePhotoEntity>> = apiService.getPhotos()*/
    /*override suspend fun loginAccount(id: String, psw: String): Response<Int> = apiService.loginAccount(id, psw)

    override suspend fun joinAccount(
        name: String,
        phone: String,
        password: String,
        email: String
    ): Response<Int> = apiService.joinAccount(name, phone, password, email)

    override suspend fun getMyCurrentPoint(account_idx: Int): Response<Int> {

    }

    override suspend fun getErippleInBookmark(account_idx: Int): Response<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getEventForHomeFragment(): Response<List<???>> {
        TODO("Not yet implemented")
    }*/

}
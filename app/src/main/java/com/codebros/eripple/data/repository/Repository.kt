package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.EventWithThumbnailEntity
import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.data.entity.SimpleErippleInfoWithBookmarkEntity
import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.data.url.RetrofitGenerator
import com.codebros.eripple.model.sample.Post
import retrofit2.Response

class Repository : ApiService {

    private val apiService: ApiService = RetrofitGenerator.getApiService()

    override suspend fun loginAccount(id: String, psw: String): Response<Int> =
        apiService.loginAccount(id, psw)


    override suspend fun joinAccount(
        name: String,
        phone: String,
        password: String,
        email: String
    ): Response<Int> = apiService.joinAccount(name, phone, password, email)

    override suspend fun getMyCurrentPoint(account_idx: Int): Response<Int> =
        apiService.getMyCurrentPoint(account_idx)

    override suspend fun getErippleInBookmark(account_idx: Int): Response<List<SimpleErippleInfoWithBookmarkEntity>> =
        apiService.getErippleInBookmark(account_idx)

    override suspend fun getEventForHomeFragment(): Response<List<EventWithThumbnailEntity>> =
        apiService.getEventForHomeFragment()


}
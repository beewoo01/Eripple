package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.SamplePhotoEntity
import com.codebros.eripple.data.network.ApiService
import com.codebros.eripple.data.url.RetrofitGenerator
import com.codebros.eripple.model.sample.Post
import retrofit2.Response

class Repository() : ApiService {

    private val apiService: ApiService = RetrofitGenerator.getApiService()

    override suspend fun loginAccount(id: String, psw: String): Response<Int> =
        apiService.loginAccount(id, psw)


    override suspend fun joinAccount(name: String, phone: String, password: String, email: String) =
        apiService.joinAccount(name, phone, password, email)


}
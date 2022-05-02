package com.codebros.eripple.data.repository

import com.codebros.eripple.data.entity.*
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

    override suspend fun getSimpleErippleInBookmark(account_idx: Int): Response<List<SimpleErippleInfoWithBookmarkEntity>> =
        apiService.getSimpleErippleInBookmark(account_idx)

    override suspend fun getErippleInBookmark(account_idx: Int): Response<List<SimpleErippleInfoWithBookmarkEntity>> =
        apiService.getErippleInBookmark(account_idx)

    override suspend fun getEventForHomeFragment(): Response<List<EventWithThumbnailEntity>> =
        apiService.getEventForHomeFragment()

    override suspend fun getAllEripple(account_idx: Int): Response<List<ErippleEntity>> =
        apiService.getAllEripple(account_idx)

    override suspend fun addBookMark(account_idx: Int, eripple_idx: Int) : Response<ErippleEntity> =
        apiService.addBookMark(account_idx, eripple_idx)

    override suspend fun removeBookMark(bookmark_idx: Int) : Response<Int> =
        apiService.removeBookMark(bookmark_idx)

    override suspend fun getPointHistory(account_idx: Int): Response<List<PointSavedEntity>> =
        apiService.getPointHistory(account_idx)

    override suspend fun getPointSituation(account_idx: Int): Response<HashMap<String, Int>> =
        apiService.getPointSituation(account_idx)

    override suspend fun getExchangeHistory(account_idx: Int): Response<List<AccountExchangeHistoryEntity>> =
        apiService.getExchangeHistory(account_idx)


}
package com.codebros.eripple.data.network

import com.codebros.eripple.data.entity.*
import com.codebros.eripple.model.sample.Post
import retrofit2.Response
import retrofit2.http.*

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

    @FormUrlEncoded
    @POST("joinAccount")
    suspend fun joinAccount(
        @Field("name") name: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
        @Field("email") email: String,
    ): Response<Int>

    @FormUrlEncoded
    @POST("getMyCurrentPoint")
    suspend fun getMyCurrentPoint(
        @Field("account_idx") account_idx: Int
    ): Response<Int>

    @GET("getSimpleErippleInBookmark")
    suspend fun getSimpleErippleInBookmark(
        @Query("account_idx") account_idx: Int
    ): Response<List<SimpleErippleInfoWithBookmarkEntity>>

    @GET("getErippleInBookmark")
    suspend fun getErippleInBookmark(
        @Query("account_idx") account_idx: Int
    ): Response<List<SimpleErippleInfoWithBookmarkEntity>>

    @GET("getEventForHomeFragment")
    suspend fun getEventForHomeFragment(): Response<List<EventWithThumbnailEntity>>

    @GET("getAllErippleInfo")
    suspend fun getAllEripple(@Query("account_idx") account_idx: Int): Response<List<ErippleEntity>>

    @POST("addBookMark")
    suspend fun addBookMark(
        @Query("account_idx") account_idx: Int,
        @Query("eripple_idx") eripple_idx: Int
    ): Response<ErippleEntity>

    @POST("removeBookMark")
    suspend fun removeBookMark(
        @Query("bookmark_idx") bookmark_idx: Int
    ): Response<Int>

    @GET("getPointHistory")
    suspend fun getPointHistory(
        @Query("account_idx") account_idx: Int
    ): Response<List<PointSavedEntity>>

    @GET("getPointSituation")
    suspend fun getPointSituation(
        @Query("account_idx") account_idx: Int
    ): Response<HashMap<String, Int>>

    @GET("getExchangeHistory")
    suspend fun getExchangeHistory(
        @Query("account_idx") account_idx: Int
    ): Response<List<AccountExchangeHistoryEntity>>

    @GET("getNotice")
    suspend fun getNotice(): Response<List<NoticeEntity>>

    @GET("getFAQ")
    suspend fun getFAQ(): Response<List<QuestionEntity>>

    @FormUrlEncoded
    @POST("updatePassword")
    suspend fun updatePassword(
        @Field("account_idx") account_idx: Int,
        @Field("account_password") account_password: String
    ): Response<Int>

    @GET("getAccountBank")
    suspend fun getAccountBank(
        @Query("account_idx") account_idx: Int
    ): Response<String>

    @GET("getBankList")
    suspend fun getBankList() : Response<List<BankEntity>>

    @FormUrlEncoded
    @POST("getAccountBankExists")
    suspend fun registerAccountBank(
        @Field("account_idx") account_idx : Int,
        @Field("bank_idx") bank_idx : Int,
        @Field("bank_account_number") bank_account_number : String
    ) : Response<Int>

}
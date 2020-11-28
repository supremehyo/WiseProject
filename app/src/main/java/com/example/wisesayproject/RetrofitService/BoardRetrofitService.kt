package com.example.wisesayproject.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BoardRetrofitService {

    @FormUrlEncoded
    @POST("board/write")
    fun wiseSayingWrite(@Field("BobjJson1") BobjJson1: String) : Call<ResponseBody>

}
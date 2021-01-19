package com.example.wisesayproject.RetrofitService
import com.example.wisesayproject.DTO.Board
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BoardRetrofitService {

    @FormUrlEncoded
    @POST("board/userSay/write")
    fun wiseSayingWrite(@Field("BobjJson1") BobjJson1: String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("board/userSay/oneget")
    fun wiseSayingOntGet(@Field("BobjJson2") BobjJson2: String) : Call<Board>

    @FormUrlEncoded
    @POST("board/userSay/listget")
    fun wiseSayingListGet(@Field("BobjJson3") BobjJson3: String) : Call<List<Board>>

    @FormUrlEncoded
    @POST("board/userSay/likeupdate")
    fun likewiseSaying(@Field("BobjJson4") BobjJson4: String) : Call<ResponseBody>
}
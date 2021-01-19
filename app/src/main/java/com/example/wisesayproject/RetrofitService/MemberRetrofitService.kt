package com.example.wisesayproject.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface MemberRetrofitService {

    @FormUrlEncoded
    @POST("member/join")
    fun memberJoin(@Field("MobjJson1") MobjJson1: String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("member/login")
    fun memberLogin(@Field("MobjJson2") MobjJson2: String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("member/like")
    fun getmemberLikeCount(@Field("MobjJson3") MobjJson2: String) : Call<Int>
}
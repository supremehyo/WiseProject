package com.example.wisesayproject.RetrofitService
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface WiseSayRetrofitService {


    @FormUrlEncoded
    @POST("wise/wiseSaying")
    fun getWise(@Field("WobjJson1") WobjJson1: String) : Call<WiseSaying>

    // 지금 뜬 wise를 저장
    @FormUrlEncoded
    @POST("wise/saveWise")
    fun saveWise(@Field("WobjJson2") WobjJson2: String) : Call<ResponseBody>

    @FormUrlEncoded
    @POST("wise/getsaveWiseList")
    fun getsaveWiseList(@Field("WobjJson3") WobjJson3: String) : Call<List<SaveWise>>

    @FormUrlEncoded
    @POST("wise/getUserWise")
    fun getUserWise(@Field("WobjJson4") WobjJson4: String) : Call<WiseSaying>
}
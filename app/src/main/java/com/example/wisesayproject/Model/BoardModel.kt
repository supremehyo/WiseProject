package com.example.wisesayproject.Model

import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.RetrofitService.BoardRetrofitService
import com.example.wisesayproject.RetrofitService.MemberRetrofitService
import com.example.wisesayproject.RetrofitService.NetRetrofit
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BoardModel {
    private  var retrofit : Retrofit = NetRetrofit.getInstance()
    private  var bservice : BoardRetrofitService = retrofit.create(BoardRetrofitService::class.java)


    fun wiseSayingWrite(userNickname : String ,content :String){

        var dto =  Board(0,"아이디",content)
        var gson = Gson()
        var objJson : String = gson.toJson(dto)

        bservice.wiseSayingWrite(objJson).enqueue(object  : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }

        })



    }



}
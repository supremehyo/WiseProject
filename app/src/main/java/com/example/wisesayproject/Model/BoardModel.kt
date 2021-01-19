package com.example.wisesayproject.Model

import android.util.Log
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.DTO.User
import com.example.wisesayproject.RetrofitService.BoardRetrofitService
import com.example.wisesayproject.RetrofitService.MemberRetrofitService
import com.example.wisesayproject.RetrofitService.NetRetrofit
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BoardModel() : BoardContract.InfoDataSource {
    private  var retrofit : Retrofit = NetRetrofit.getInstance()
    private  var bservice : BoardRetrofitService = retrofit.create(BoardRetrofitService::class.java)


    fun wiseSayingWrite(userNickname: String, content: String,temp_ImageName:String , feel : String , param: BoardContract.InfoDataSource.LoadInfoCallback){


        var dto =  Board(null,userNickname,content ,temp_ImageName, feel)
        var gson = Gson()
        var objJson : String = gson.toJson(dto)

        bservice.wiseSayingWrite(objJson).enqueue(object  : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                param.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                param.onDataNotAvailable("성공")
            }

        })
    }

    fun wiseSayingListGet(userNickname: String , param: BoardContract.InfoDataSource.LoadInfoCallback){

        var dto =  User(userNickname , "")
        var gson = Gson()
        var objJson : String = gson.toJson(dto)

        bservice.wiseSayingListGet(objJson).enqueue(object  : Callback<List<Board>>{
            override fun onFailure(call: Call<List<Board>>, t: Throwable) {
                param.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<List<Board>>, response: Response<List<Board>>) {
                if(response.body() == null){
                    param.onInfoLoaded("적었던 글이 없어요.")
                }else{
                    var list : List<Board> = response.body()!!
                    Log.v("wegwb",list[0].content)
                    param.onListInfoLoaded(list)
                }
            }

        })
    }

    fun likewiseSaying(bno : Int , userid : String , param: BoardContract.InfoDataSource.LoadInfoCallback){
        Log.v("wowowoooo" , bno.toString() +"  " + userid)
        var dto =  Board(bno , userid , "" , "","")
        var gson = Gson()
        var objJson : String = gson.toJson(dto)

        bservice.likewiseSaying(objJson).enqueue(object  : Callback<ResponseBody>{
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                param.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    param.onInfoLoaded("작성자분께 감사함을 전했습니다.")
                }
            }
        })
    }



}
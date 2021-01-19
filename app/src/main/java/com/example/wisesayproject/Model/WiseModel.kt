package com.example.wisesayproject.Model

import android.util.Log
import com.example.wisesayproject.Contract.WiseContract
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.User
import com.example.wisesayproject.DTO.WiseSaying
import com.example.wisesayproject.RetrofitService.NetRetrofit
import com.example.wisesayproject.RetrofitService.WiseSayRetrofitService
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class WiseModel (presenter : WiseContract.Presenter) : WiseContract.InfoDataSource {

    private var retrofit : Retrofit = NetRetrofit.getInstance()
    private var wservice = retrofit.create(WiseSayRetrofitService::class.java)


    fun getUserWise(callback : WiseContract.InfoDataSource.LoadInfoCallback){
        wservice.getUserWise("0").enqueue(object  : Callback<WiseSaying>{
            override fun onFailure(call: Call<WiseSaying>, t: Throwable) {
                callback.onDataNotAvailable("no")
            }
            override fun onResponse(call: Call<WiseSaying>, response: Response<WiseSaying>) {
                callback.onInfoLoaded(response.body()!!)
            }

        })
    }

    fun getWiseSaying(feel : String ,callback : WiseContract.InfoDataSource.LoadInfoCallback){
        var gson = Gson()
        val jsonobject = JsonObject()
        jsonobject.addProperty("feel", feel)
        val objJson: String = gson.toJson(jsonobject)

        wservice.getWise(objJson).enqueue(object  : Callback<WiseSaying>{
            override fun onFailure(call: Call<WiseSaying>, t: Throwable) {
                callback.onDataNotAvailable("no")
            }

            override fun onResponse(call: Call<WiseSaying>, response: Response<WiseSaying>) {
                callback.onInfoLoaded(response.body()!!)
            }

        })
    }

    fun saveWise(writeuserNickname: String, content: String, userNickname: String, myquestion: String , callback : WiseContract.InfoDataSource.LoadInfoCallback){
        var dto = SaveWise(writeuserNickname, content, userNickname, "",myquestion) //  user dto 만든거임
        var gson = Gson()
        Log.v("weeebeb",writeuserNickname)
        var objJson : String = gson.toJson(dto)
        wservice.saveWise(objJson).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("@@@","실패 : {$t}")
                callback.onDataNotAvailable("실패")
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                callback.onsaveInfoLoaded("보관함에 저장을 완료했습니다")
            }
        })
    }

    fun getsaveWiseList(userNickname : String , callback : WiseContract.InfoDataSource.LoadInfoCallback , callback2: WiseContract.InfoDataSource.LoadInfoCallback2){
        var dto = User(userNickname , "")
        var gson = Gson()
        var objJson : String = gson.toJson(dto)

        wservice.getsaveWiseList(objJson).enqueue(object  : Callback<List<SaveWise>>{
            override fun onFailure(call: Call<List<SaveWise>>, t: Throwable) {
                callback.onDataNotAvailable("실패")
            }

            override fun onResponse(call: Call<List<SaveWise>>, response: Response<List<SaveWise>>) {
                if(response.body() == null) {
                    callback.onsaveInfoLoaded("아직 저장한 글귀가 없어요.")
                }else{
                    callback2.onsaveListInfoLoaded(response.body()!!)
                }
            }

        })
    }
}
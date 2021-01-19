package com.example.wisesayproject.Presenter

import com.example.wisesayproject.Contract.MemberContract
import com.example.wisesayproject.Contract.WiseContract
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying
import com.example.wisesayproject.Model.MemberModel
import com.example.wisesayproject.Model.WiseModel

class WisePresenter : WiseContract.Presenter , WiseContract.InfoDataSource {

    private var wiseView : WiseContract.View? = null
    private var wiseModel : WiseModel? =null

    override fun setView(view: WiseContract.View) {
        wiseView = view
        wiseModel = WiseModel(this)
    }

    override fun getWise(feel : String) {
        wiseModel?.getWiseSaying(feel , object : WiseContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(wise: WiseSaying) {
                wiseView?.returnGetWise(wise) // f2 로 wise 가져온거 보냄
            }
            override fun onDataNotAvailable(ff: String) {

            }
            override fun onsaveInfoLoaded(ss: String) {
            }

        })
    }

    override fun getUserWise() {
        wiseModel?.getUserWise(object  : WiseContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(wise: WiseSaying) {
                wiseView?.returnGetWise(wise) // f2 로 wise 가져온거 보냄
            }
            override fun onDataNotAvailable(ff: String) {
            }
            override fun onsaveInfoLoaded(ss: String) {
            }

        })
    }

    override fun saveWise(writeuserNickname: String, content: String, userNickname: String, myquestion: String) {
        wiseModel?.saveWise(writeuserNickname, content, userNickname, myquestion , object  : WiseContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(wise: WiseSaying) {
            }

            override fun onDataNotAvailable(ff: String) {

            }
            override fun onsaveInfoLoaded(ss: String) {
                wiseView?.showToast(ss)
            }
        })
    }

    override fun getsaveWiseList(userNickname: String) {
        wiseModel?.getsaveWiseList(userNickname , object : WiseContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(wise: WiseSaying) {
            }
            override fun onDataNotAvailable(ff: String) {
            }
            override fun onsaveInfoLoaded(ss: String) {
                wiseView?.showToast(ss)
            }
          }
            , object  : WiseContract.InfoDataSource.LoadInfoCallback2{
            override fun onsaveListInfoLoaded(list: List<SaveWise>) {
                wiseView?.returnsaveWiseList(list)
            }

        })

    }
}
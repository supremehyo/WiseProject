package com.example.wisesayproject.Presenter

import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.Model.BoardModel

class BoardPresenter : BoardContract.Presenter , BoardContract.InfoDataSource{

    private var BoardView : BoardContract.View? = null
    private var BoardModel : BoardModel? =null

    override fun setView(view : BoardContract.View){ // view랑 붙이는 작업
        BoardView = view
        BoardModel = BoardModel()
    }

    override fun wiseSayingWrite(userNickname : String ,content: String , temp_ImageName : String, feel : String) {
        BoardModel?.wiseSayingWrite(userNickname, content, temp_ImageName , feel, object : BoardContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(ss: String) {
                BoardView?.waringShackBar(ss) // 실패
            }
            override fun onDataNotAvailable(ff: String) {
                BoardView?.waringShackBar(ff) // 성공하면 성공했다고
            }

            override fun onListInfoLoaded(list: List<Board>) {

            }

        })
    }

    override fun wiseSayingListGet(userNickname: String) {
        BoardModel?.wiseSayingListGet(userNickname , object  : BoardContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(ss: String) {
                BoardView?.waringShackBar(ss)
            }
            override fun onDataNotAvailable(ff: String) {

            }
            override fun onListInfoLoaded(list: List<Board>) {
                BoardView?.goUserSayBoxList(list) // 반응이 나오면 view 로 리스트를 던져주고 거기서 adapter를 이어줌
            }
        })
    }

    override fun likewiseSaying(bno: Int, userid: String) {
        BoardModel?.likewiseSaying(bno, userid , object  : BoardContract.InfoDataSource.LoadInfoCallback{
            override fun onInfoLoaded(ss: String) {
                BoardView?.waringShackBar(ss)
            }
            override fun onDataNotAvailable(ff: String) {

            }
            override fun onListInfoLoaded(list: List<Board>) {

            }

        })
    }

}
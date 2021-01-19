package com.example.wisesayproject.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.*
import android.view.View.OnTouchListener
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.wisesayproject.Activity.UserSayingBoxActivity
import com.example.wisesayproject.Activity.UserSayingSaveBoxActivity
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.Contract.WiseContract
import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying
import com.example.wisesayproject.Presenter.BoardPresenter
import com.example.wisesayproject.Presenter.WisePresenter
import com.example.wisesayproject.R
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_1.*
import kotlinx.android.synthetic.main.fragment_1.view.*
import kotlinx.android.synthetic.main.fragment_1.view.f1ContentText
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_2.view.*
import kotlinx.android.synthetic.main.fragment_3.*
import java.util.Random

class Fragment1 : Fragment() , BottomSheetListener , BoardContract.View, WiseContract.View  {


    private  lateinit  var bpresenter : BoardPresenter
    private  lateinit  var  wpresenter: WisePresenter
    lateinit var prefs3 : SharedPreferences
    lateinit var Lottieheart : LottieAnimationView
    var bno : Int  =0;
    var likecheck : Boolean = false

    var touchCountFlag : Int = 0;
    var firestTouchCount : Int =0;
    val  intani : Animation = AlphaAnimation(0.0f, 1.0f);
    val outani : Animation = AlphaAnimation(1.0f, 0.0f);
    lateinit var view2 : View
    lateinit var  writeuserNicnname : String
    lateinit var content : String
    lateinit var tempquestion : String

    lateinit var backgroundImage : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view2 = inflater.inflate(R.layout.fragment_1, container, false)

        writeuserNicnname = ""
        bpresenter = BoardPresenter()
        bpresenter.setView(this)
        wpresenter = WisePresenter()
        wpresenter.setView(this)

        backgroundImage = view2.findViewById(R.id.backgroundImagef1)

        val prefs2 :SharedPreferences = requireContext().getSharedPreferences("first", Context.MODE_PRIVATE)
        prefs3 = requireContext().getSharedPreferences("LoginNickname", Context.MODE_PRIVATE)
        if(prefs2.getInt("first",1) == 1){
            intani.setDuration(3000);
            outani.setDuration(3000);
            view2.f1tempText3.startAnimation(outani)
            view2.f1tempText3.setText("반가워요.\n우리 혹시 초면인가요?")
            view2.f1tempText3.startAnimation(intani)
            firestTouchCount++
        }else{
            view2.f1tempText3.setText("이곳은 다른 사람들이 적은 글을 볼 수 있어요!\n마찬가지로 저를 부르려면 화면을\n꾹 눌러주세요.")
        }
        var dialog : BottomSheetMenuDialogFragment.Builder = BottomSheetMenuDialogFragment.Builder(context =view2.context,
            sheet = R.menu.f1grid_sheet,
            isGrid = true,
            title = "메뉴",
            listener = this,
            `object` = "some object")
        view2.rootfragment1.setOnClickListener {
            if(firestTouchCount == 1){ // 첫 등장 텍스트
                TextAni("이곳은 " + prefs3.getString("LoginNickname" , "").toString()+"님처럼\n" +
                "고민을 가지고 왔다가 답을\n남기고 가신 분들의 이야기를 들을 수 있어요.")
                firestTouchCount++
            }else if(firestTouchCount==2){
                firestTouchCount = 0
            }
            if(touchCountFlag == 1){ // 질문 누르고 나서 동작
                TextAni("라고 질문해주셨네요.")
                touchCountFlag++
            }else if(touchCountFlag == 2){
                TextAni("조금만 기다려주세요.\nOO이 방문자님들의 지혜를 찾아보고 있어요!")
                touchCountFlag =0

                wpresenter.getUserWise() // 요청
            }
        }
        view2.rootfragment1.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                dialog.show(parentFragmentManager)
                Log.d("cccclink" , "롱클릭")

                return true
            }

        })


        Lottieheart = view2.findViewById(R.id.Lottie_heart)
        Lottieheart.setAnimation("lf30_editor_yjixlnlv.json")
        Lottieheart.progress = 0.0F
        Lottieheart.visibility = View.INVISIBLE

        Lottieheart.setOnClickListener {
            Lottieheart.playAnimation()
            if(likecheck == false){
                bpresenter.likewiseSaying(bno , prefs3.getString("LoginNickname" , "").toString())
            }
        }

        return view2
    }

    override fun onSheetDismissed(
        bottomSheet: BottomSheetMenuDialogFragment,
        `object`: Any?,
        dismissEvent: Int
    ) {

    }

    override fun onSheetItemSelected(
        bottomSheet: BottomSheetMenuDialogFragment,
        item: MenuItem,
        `object`: Any?
    ) {

        if(item.title.toString().equals("질문하기")){
            f1ContentText.text = ""
            f1tempText3.text = ""
            Lottieheart.progress = 0.0F
            Lottieheart.visibility = View.VISIBLE
            val builder = AlertDialog.Builder(requireContext()) // 코틀린의 경우 Fragment의 Context? 를 반환하기 때문에 requireContext를 통해 Context를 반환 가능하다.
            val dialogView : View = layoutInflater.inflate(R.layout.custom_view, null)
            val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
            builder.setView(dialogView)
                .setPositiveButton("완료") { dialogInterface, i ->
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                    f1ContentText.visibility = View.VISIBLE
                    f1tempText3.visibility = View.VISIBLE
                    tempquestion = dialogText.text.toString()
                    TextAni(dialogText.text.toString())
                    touchCountFlag++

                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                    f1ContentText.text = ""
                    f1tempText3.visibility = View.VISIBLE
                    val random = Random()
                    val num = random.nextInt(2)
                    if(num ==0){
                        TextAni("고민 하지마세요.\n어떤 질문이든 괜찮아요. 정말 :)")
                    }else if(num==1){
                        TextAni("속시원하게 털어놓는게\n오히려 마음이 편할거예요!")
                    }
                }
                .show()
        }else if(item.title.toString().equals("저장하기")){
            //여기서 그냥 바로 인텐트 시키고
            if(writeuserNicnname != ""){
                wpresenter.saveWise(writeuserNicnname ,content ,prefs3.getString("LoginNickname" , "").toString(),tempquestion)
               // Toast.makeText(requireContext(),"보관함에 저장을 완료했습니다",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context, "아직 아무런 질문을 하지 않았어요.", Toast.LENGTH_SHORT).show()
            }
        }
     //   else if(item.title.toString().equals("공유하기")){
         //   val intent = Intent(context, UserSayingSaveBoxActivity::class.java) // 이 액티비티에서 oncreate 될때
        //    intent.putExtra("name",prefs3.getString("LoginNickname" , "").toString())
         //   startActivity(intent)
       // }
    }


    fun TextAni (string : String){
        intani.setDuration(3000)
        outani.setDuration(3000)
        f1tempText3.startAnimation(outani)
        f1tempText3.setText(string)
        f1tempText3.startAnimation(intani)
    }


    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
    }
    override fun waringShackBar(string: String) {
        Toast.makeText(requireContext(),string,Toast.LENGTH_LONG).show()
        likecheck = true
    }
    override fun goUserSayBoxList(list: List<Board>) {
    }
    override fun returnGetWise(wise: WiseSaying) { // 유저가 작성한 wise 가져오는 코드
        Thread.sleep(1000)
        f1ContentText.text = wise.content
        f1tempText3.text = wise.userNickname
        Log.v("wgewgw",wise.imagename)
        Picasso.get().load("http://121.181.189.167:8090/upload/"+wise.imagename).into(backgroundImage)
        writeuserNicnname = wise.userNickname
        content = wise.content

        bno = wise.wno
    }
    override fun showToast(text: String) {
    }
    override fun returnsaveWiseList(list: List<SaveWise>) {
    }
}
package com.example.wisesayproject.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.wisesayproject.Contract.WiseContract
import com.example.wisesayproject.DTO.SaveWise
import com.example.wisesayproject.DTO.WiseSaying
import com.example.wisesayproject.Presenter.MemberPresenter
import com.example.wisesayproject.Presenter.WisePresenter
import com.example.wisesayproject.R
import com.google.android.material.chip.ChipGroup
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_2.view.*
import java.util.*

class Fragment2 : Fragment(), WiseContract.View, BottomSheetListener {

    var touchCountFlag : Int = 0;
    var firestTouchCount : Int =0;
    val  intani : Animation = AlphaAnimation(0.0f, 1.0f);
    val outani : Animation = AlphaAnimation(1.0f, 0.0f);
    private  lateinit var writeuserNicnname :String
    private  lateinit var content : String
    private  lateinit  var  wpresenter: WisePresenter
    private lateinit var prefs3 :SharedPreferences
    lateinit var  tempquestion : String
    lateinit var  feel : String
    lateinit var backgroundImagef2 : ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        wpresenter = WisePresenter()
        wpresenter.setView(this)

        writeuserNicnname = ""
        backgroundImagef2 = view.findViewById(R.id.backgroundImagef2)

        val prefs2 :SharedPreferences = requireContext().getSharedPreferences("first", Context.MODE_PRIVATE)
        prefs3 = requireContext().getSharedPreferences("LoginNickname", Context.MODE_PRIVATE)
        if(prefs2.getInt("first",1) == 1){
            intani.setDuration(3000);
            outani.setDuration(3000);
            view.tempText3.startAnimation(outani)
            view.tempText3.setText("반가워요.\n우리 혹시 초면인가요?")
            view.tempText3.startAnimation(intani)
            firestTouchCount++
        }else{
            view.tempText3.setText("저를 부르려면 화면을\n꾹 눌러주세요.")
        }


        var dialog : BottomSheetMenuDialogFragment.Builder = BottomSheetMenuDialogFragment.Builder(context =view.context,
            sheet = R.menu.grid_sheet,
            isGrid = true,
            title = "메뉴",
            listener = this,
            `object` = "some object")

        view.f2Rootlayout.setOnClickListener {
            if(firestTouchCount == 1){ // 첫 등장 텍스트
                TextAni("저는 OO 입니다.\n모두에게 해답을 주고 있죠.")
                firestTouchCount++
            }else if(firestTouchCount==2){
                TextAni("질문할게 있다면 화면 꾹 눌러보세요.\n그리고 뭐든 질문하세요.")
                firestTouchCount = 0
                prefs2.edit().putInt("first",0).apply()
            }
            
            if(touchCountFlag == 1){ // 질문 누르고 나서 동작
                TextAni("라고 질문해주셨네요.")
                touchCountFlag++
            }else if(touchCountFlag == 2){
                TextAniend("조금만 기다려주세요.\nOO이 책을 찾아보고 있어요!")
                touchCountFlag =0
                wpresenter.getWise(feel) // 요청z
                
            }
            

        }
        view.f2Rootlayout.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                dialog.show(parentFragmentManager)
                Log.d("cccclink" , "롱클릭")

                return true
            }
        })
        return  view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/*"
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, check out the BottomSheet library https://github.com/Kennyc1012/BottomSheet")
                BottomSheetMenuDialogFragment.createShareBottomSheet(requireContext(), intent, "Share")?.show(parentFragmentManager, null)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSheetDismissed(
        bottomSheet: BottomSheetMenuDialogFragment,
        `object`: Any?,
        dismissEvent: Int
    ) {
        Log.v("dia", "onSheetDismissed $dismissEvent")
    }

    override fun onSheetItemSelected(
        bottomSheet: BottomSheetMenuDialogFragment,
        item: MenuItem,
        `object`: Any?
    ) {

        if(item.title.toString().equals("질문하기")){
            ContentText.text = ""
            tempText3.visibility = View.INVISIBLE
            val builder = AlertDialog.Builder(requireContext()) // 코틀린의 경우 Fragment의 Context? 를 반환하기 때문에 requireContext를 통해 Context를 반환 가능하다.
            val dialogView : View = layoutInflater.inflate(R.layout.custom_view, null)
            val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
            val chips = dialogView.findViewById<ChipGroup>(R.id.defaultChipGroup)
            chips.setOnCheckedChangeListener { group, checkedId ->
                if(checkedId == R.id.defaultChip1){
                    feel = "행복"
                }else if(checkedId == R.id.defaultChip2){
                    feel ="슬픔"
                }else if(checkedId == R.id.defaultChip3){
                    feel ="분노"
                }else if(checkedId == R.id.defaultChip4){
                    feel ="혼란"
                }
            }
            builder.setView(dialogView)
                .setPositiveButton("완료") { dialogInterface, i ->
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                    ContentText.visibility = View.VISIBLE
                    tempText3.visibility = View.VISIBLE
                    tempquestion = dialogText.text.toString()
                    TextAni(dialogText.text.toString())
                    touchCountFlag++

                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                    ContentText.text = ""
                    tempText3.visibility = View.VISIBLE
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
            if(writeuserNicnname != ""){
                wpresenter.saveWise(writeuserNicnname ,content ,prefs3.getString("LoginNickname" , "").toString(),tempquestion)
            }else{
                Toast.makeText(context, "아직 아무런 질문을 하지 않았어요.", Toast.LENGTH_SHORT).show()
            }
        }// if(item.title.toString().equals("공유하기")){
         //   Toast.makeText(context, item.title.toString() + " Clicked", Toast.LENGTH_SHORT).show()
      //  }
    }
    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
        Log.v("dia", "onSheetShown with Object " + `object`!!)
    }

    fun TextAni (string : String){
        intani.setDuration(3000)
        outani.setDuration(3000)
        tempText3.startAnimation(outani)
        tempText3.setText(string)
        tempText3.startAnimation(intani)
    }

    fun TextAniend(string : String){
        intani.setDuration(3000)
        outani.setDuration(3000)
        tempText3.startAnimation(outani)
        tempText3.setText(string)
        tempText3.startAnimation(intani)
        Thread.sleep(1000)
    }

    override fun returnGetWise(wise: WiseSaying) {
        //최근 //여기로 wise 가져와서 화면에 띄우면 된다.
     //   tempText3.visibility = View.INVISIBLE
        ContentText.visibility = View.VISIBLE
        ContentText.text = wise.content
        content = wise.content
        Picasso.get().load("http://121.181.189.167:8090/upload/"+wise.imagename).into(backgroundImagef2)
        tempText3.text = wise.userNickname
        writeuserNicnname = wise.userNickname
    }

    override fun showToast(text: String) {
        Toast.makeText(requireContext(), text,Toast.LENGTH_LONG).show()
    }

    override fun returnsaveWiseList(list: List<SaveWise>) {

    }

}
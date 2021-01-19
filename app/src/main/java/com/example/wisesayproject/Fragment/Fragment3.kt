package com.example.wisesayproject.Fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.wisesayproject.Activity.JoinActivity
import com.example.wisesayproject.Activity.UserSayingBoxActivity
import com.example.wisesayproject.Activity.UserSayingSaveBoxActivity
import com.example.wisesayproject.Contract.BoardContract
import com.example.wisesayproject.DTO.Board
import com.example.wisesayproject.Presenter.BoardPresenter
import com.example.wisesayproject.R
import com.google.android.material.chip.ChipGroup
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_1.view.*
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_3.*
import kotlinx.android.synthetic.main.fragment_3.view.*
import kotlinx.android.synthetic.main.fragment_3.view.myWriteContent
import java.util.*


class Fragment3 : Fragment() , BottomSheetListener ,BoardContract.View {
    lateinit var  feel : String
    private  lateinit  var bpresenter : BoardPresenter
    lateinit var prefs2 : SharedPreferences
    lateinit var image : ImageView
     var  num : Int = 0
    lateinit var temp_ImageName :String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_3, container, false)
        prefs2  = requireContext().getSharedPreferences("LoginNickname", Context.MODE_PRIVATE)

        bpresenter = BoardPresenter()
        bpresenter.setView(this)

        view.myWriteContent.setOnClickListener {
            Log.d("cccclink" , "짧은 클릭")
        }

        var dialog : BottomSheetMenuDialogFragment.Builder = BottomSheetMenuDialogFragment.Builder(context =view.context,
            sheet = R.menu.f3grid_sheet,
            isGrid = true,
            title = "메뉴",
            listener = this,
            `object` = "some object")

        view.myWriteContent.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                dialog.show(parentFragmentManager)
                Log.d("cccclink" , "롱클릭")
                return true
            }
        })
        val random = Random()
        num = random.nextInt(2)
        val array = resources.getStringArray(R.array.background_image_name)
        temp_ImageName = array[num]

        image = view.findViewById(R.id.backgroundImage)
        Picasso.get().load("http://121.181.189.167:8090/upload/"+temp_ImageName).fit().into(image)
        return  view
    }

    override fun onSheetDismissed(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?, dismissEvent: Int) {

    }

    override fun onSheetItemSelected(bottomSheet: BottomSheetMenuDialogFragment, item: MenuItem, `object`: Any?) {

        if(item.title.toString().equals("글작성")){

            val builder = AlertDialog.Builder(requireContext()) // 코틀린의 경우 Fragment의 Context? 를 반환하기 때문에 requireContext를 통해 Context를 반환 가능하다.
            val dialogView : View = layoutInflater.inflate(R.layout.custom_view2, null)
            val chips = dialogView.findViewById<ChipGroup>(R.id.defaultChipGroup2)
            chips.setOnCheckedChangeListener { group, checkedId ->
                if(checkedId == R.id.defaulChip1){
                    feel = "행복"
                }else if(checkedId == R.id.defaulChip2){
                    feel ="슬픔"
                }else if(checkedId == R.id.defaulChip3){
                    feel ="분노"
                }else if(checkedId == R.id.defaulChip4){
                    feel ="혼란"
                }
            }
            builder.setView(dialogView)
                .setPositiveButton("완료") { dialogInterface, i ->
                    bpresenter.wiseSayingWrite(prefs2.getString("LoginNickname" , "").toString(), myWriteContent.text.toString(),temp_ImageName, feel) // 글써지는거 확인함
                }
                .setNegativeButton("취소") { dialogInterface, i ->

                }
                .show()
        }else if(item.title.toString().equals("내가 쓴 글")){
            //여기서 그냥 바로 인텐트 시키고
            activity?.let{
                val intent = Intent(context, UserSayingBoxActivity::class.java) // 이 액티비티에서 oncreate 될때
                intent.putExtra("name",prefs2.getString("LoginNickname" , "").toString())
                startActivity(intent)
            }
        }
        else if(item.title.toString().equals("보관함")){
            val intent = Intent(context, UserSayingSaveBoxActivity::class.java) // 이 액티비티에서 oncreate 될때
            intent.putExtra("name",prefs2.getString("LoginNickname" , "").toString())
            startActivity(intent)
        }else if(item.title.toString().equals("배경변경")){
            val random = Random()
            num = random.nextInt(2)
            val array = resources.getStringArray(R.array.background_image_name)
            temp_ImageName = array[num]
            Picasso.get().load("http://121.181.189.167:8090/upload/"+temp_ImageName).fit().into(image)
        }
    }
    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {

    }
    override fun waringShackBar(string :String) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
    }
    override fun goUserSayBoxList(list: List<Board>) {

    }
}
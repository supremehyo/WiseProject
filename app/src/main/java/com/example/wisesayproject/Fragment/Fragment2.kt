package com.example.wisesayproject.Fragment

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.wisesayproject.R
import com.kennyc.bottomsheet.BottomSheetListener
import com.kennyc.bottomsheet.BottomSheetMenuDialogFragment
import kotlinx.android.synthetic.main.custom_view.*
import kotlinx.android.synthetic.main.custom_view.view.*
import kotlinx.android.synthetic.main.fragment_2.*
import kotlinx.android.synthetic.main.fragment_2.view.*
import kotlinx.android.synthetic.main.fragment_2.view.tkv_animate_xml


class Fragment2 : Fragment(), BottomSheetListener {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)

        var dialog : BottomSheetMenuDialogFragment.Builder = BottomSheetMenuDialogFragment.Builder(context =view.context,
            sheet = R.menu.grid_sheet,
            isGrid = true,
            title = "메뉴",
            listener = this,
            `object` = "some object")


        view.iv_fragment2img.setOnClickListener {
            Log.d("cccclink" , "짧은 클릭")
        }
        view.iv_fragment2img.setOnLongClickListener(object  : View.OnLongClickListener{
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
            val builder = AlertDialog.Builder(requireContext()) // 코틀린의 경우 Fragment의 Context? 를 반환하기 때문에 requireContext를 통해 Context를 반환 가능하다.
            val dialogView : View = layoutInflater.inflate(R.layout.custom_view, null)
            val dialogText = dialogView.findViewById<EditText>(R.id.dialogEt)
            builder.setView(dialogView)
                .setPositiveButton("완료") { dialogInterface, i ->
                    tkv_animate_xml.text = dialogText.text.toString()
                    // Animate by characters

                    tempText.text = "라고 질문해주셨군요.\n 곧 답을 찾아드리겠습니다."
                    tempText.animateText()
                    /* 확인일 때 main의 View의 값에 dialog View에 있는 값을 적용 */
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                }
                .show()


        }else if(item.title.toString().equals("저장하기")){
            Toast.makeText(context, item.title.toString() + " Clicked", Toast.LENGTH_SHORT).show()
        }else if(item.title.toString().equals("공유하기")){
            Toast.makeText(context, item.title.toString() + " Clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onSheetShown(bottomSheet: BottomSheetMenuDialogFragment, `object`: Any?) {
        Log.v("dia", "onSheetShown with Object " + `object`!!)
    }

    override fun onResume() {
        super.onResume()
        if(tkv_animate_xml.isAnimating()){
            tkv_animate_xml.animateText()
        }
    }

    override fun onPause() {
        super.onPause()
        // Pause animation
        tkv_animate_xml.skipAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Remove callbacks
        tkv_animate_xml.removeAnimation()
    }
}
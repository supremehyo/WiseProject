package com.example.wisesayproject.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import com.example.wisesayproject.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_saying_save_box_detail.*


class UserSayingSaveBoxDetailActivity : AppCompatActivity() {

    lateinit var  saveDetail_backgroundImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_saying_save_box_detail)

        saveDetail_backgroundImage= findViewById(R.id.saveDetail_backgroundImage)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)//캡처방지 소스

        val intent = intent.extras
        saveContentText.text = intent!!.getString("content")
        saveusername.text = intent!!.getString("writeusernickname")
        Picasso.get().load("http://121.181.189.167:8090/upload/"+intent!!.getString("backgroundname")).into(saveDetail_backgroundImage)

        DetailRootlayout.setOnClickListener {
            Toast.makeText(this,"캡쳐하려면 화면을 꾹누르고 광고 시청 후 캡쳐권을 획득해주세요.",
                Toast.LENGTH_LONG).show()
        }

        DetailRootlayout.setOnLongClickListener(object  : View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
                if (p0 != null) {
                    Toast.makeText(p0.context,"이제 캡쳐를 할 수 있습니다.", Toast.LENGTH_LONG).show()
                } // 이걸 광고보고나서 하도록 해야함
                return true
            }

        })

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }



}
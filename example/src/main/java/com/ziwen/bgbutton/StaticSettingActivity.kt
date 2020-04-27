package com.ziwen.bgbutton

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_static.*

/**
 * PackageName : com.ziwen.bgbutton
 * Author : Ziwen Lan
 * Date : 2020/4/27
 * Time : 13:51
 * Introduction :
 */
class StaticSettingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_static)

        btn1.setOnClickListener {}
        btn2.setOnClickListener {}
        btn3.setOnClickListener {}
        btn4.setOnClickListener {}
        btn5.setOnClickListener {
            btn5.setEnabledStyle(false)
        }
        btn6.setOnClickListener {}
        btn7.setOnClickListener {}
    }
}
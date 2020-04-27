package com.ziwen.bgbutton

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_example.*

/**
 * PackageName : com.ziwen.bgbutton
 * Author : Ziwen Lan
 * Date : 2020/4/27
 * Time : 13:52
 * Introduction :
 */
class ExampleActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        btn_start_dynamic.setOnClickListener {
            startActivity(Intent(this@ExampleActivity, DynamicSettingActivity::class.java))
        }
        btn_start_static.setOnClickListener {
            startActivity(Intent(this@ExampleActivity, StaticSettingActivity::class.java))
        }
    }
}
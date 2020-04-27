package com.ziwen.bgbutton

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_dynamic.*

/**
 * PackageName : com.ziwen.bgbutton
 * Author : Ziwen Lan
 * Date : 2020/4/27
 * Time : 13:51
 * Introduction :
 */
class DynamicSettingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic)

        btn_example.setOnClickListener {}

        cb_change_enabled.setOnCheckedChangeListener { buttonView, isChecked ->
            btn_example.isEnabled = !isChecked
        }

        btn_confirm.setOnClickListener {

            //背景色和按压色设置
            val backgroundColor = cpv_background.color
            val startColor = cpv_background_start.color
            val endColor = cpv_background_end.color
            val unableColor = cpv_background_unable.color
            val pressedColor = cpv_pressed.color
            val isAutoPressedColor = cb_auto_pressed_color.isChecked
            if (cb_background_color_gradient.isChecked) {
                btn_example.setBGBackgroundColor(startColor, endColor)
            } else {
                btn_example.setBGBackgroundColor(backgroundColor)
            }
            btn_example.setUnableColor(unableColor)
            if (!isAutoPressedColor) {
                btn_example.setPressedColor(pressedColor)
            }

            //圆角设置
            val radius: Int = if (et_radius.text.toString() == "") {
                0
            } else {
                et_radius.text.toString().toInt()
            }
            val topLeftRadius: Int = if (et_top_left_radius.text.toString() == "") {
                0
            } else {
                et_top_left_radius.text.toString().toInt()
            }
            val topRightRadus = if (et_top_right_radius.text.toString() == "") {
                0
            } else {
                et_top_right_radius.text.toString().toInt()
            }
            val bottomLeftRadius = if (et_bottom_left_radius.text.toString() == "") {
                0
            } else {
                et_bottom_left_radius.text.toString().toInt()
            }
            val bottomRightRadius = if (et_bottom_right_radius.text.toString() == "") {
                0
            } else {
                et_bottom_right_radius.text.toString().toInt()
            }
            if (radius == 0) {
                btn_example.setRadius(
                        topLeftRadius.toFloat(), topRightRadus.toFloat(), bottomLeftRadius.toFloat(),
                        bottomRightRadius.toFloat()
                )
            } else {
                btn_example.setRadius(radius.toFloat())
            }

            //描边设置
            val strokeWidth = if (et_stroke_width.text.toString() == "") {
                0
            } else {
                et_stroke_width.text.toString().toInt()
            }
            val storeColor = cpv_stroke.color
            val storeColorPressed = cpv_stroke_pressed.color
            val storeColorUnable = cpv_stroke_unable.color
            btn_example.setStroke(
                    strokeWidth.toFloat(),
                    storeColor,
                    storeColorPressed,
                    storeColorUnable
            )
        }
    }
}
package com.ziwen.bgbutton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.ziwen.library.widget.BGButton;

/**
 * PackageName : com.ziwen.bgbutton
 * Author : Ziwen Lan
 * Date : 2018/6/13
 * Time : 17:23
 * Introduction :
 */

public class MainActivity extends Activity {
    private BGButton mBtn1;
    private BGButton mBtn2;
    private BGButton mBtn3;
    private BGButton mBtn4;
    private BGButton mBtn5;
    private BGButton mBtn6;
    private BGButton mBtn7;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn1:
                    break;
                case R.id.btn2:
                    break;
                case R.id.btn3:
                    break;
                case R.id.btn4:
                    break;
                case R.id.btn5:
                    mBtn5.setUnClickStyle(false);
                    break;
                case R.id.btn6:
                    break;
                case R.id.btn7:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn1 = findViewById(R.id.btn1);
        mBtn2 = findViewById(R.id.btn2);
        mBtn3 = findViewById(R.id.btn3);
        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);

        mBtn1.setOnClickListener(mOnClickListener);
        mBtn2.setOnClickListener(mOnClickListener);
        mBtn3.setOnClickListener(mOnClickListener);
        mBtn4.setOnClickListener(mOnClickListener);
        mBtn5.setOnClickListener(mOnClickListener);
        mBtn6.setOnClickListener(mOnClickListener);
        mBtn7.setOnClickListener(mOnClickListener);

    }
}

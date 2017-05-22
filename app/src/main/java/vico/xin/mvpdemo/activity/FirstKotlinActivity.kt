package vico.xin.mvpdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first_kotlin.*
import vico.xin.mvpdemo.R

class FirstKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_kotlin)

        //替代java中的findviewbyid settext
        tv_test.text = "hello kotlin"


    }
}

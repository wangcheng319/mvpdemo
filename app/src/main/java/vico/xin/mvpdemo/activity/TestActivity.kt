package vico.xin.mvpdemo.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View

import vico.xin.mvpdemo.R

/**
 * Created by wangc on 2017/5/22
 * E-MAIL:274281610@QQ.COM
 */

class TestActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        findViewById(R.id.btn1).setOnClickListener { Log.e("", "this is btn1") }

        startActivity(Intent(this@TestActivity, LoginActivity::class.java))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn1 -> Log.i("", "this is btn1")
            R.id.btn2 -> Log.i("", "this is btn2")
            R.id.btn3 -> Log.i("", "this is btn3")
        }
    }
}

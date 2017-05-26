package vico.xin.mvpdemo.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_rxbinding_test.*
import vico.xin.mvpdemo.R
import java.util.concurrent.TimeUnit


/**
 * rxbinding常用方法
 */
class RxbindingTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rxbinding_test)

        //rxbinding ,button防抖处理，一秒只响应一次
        RxView.clicks(btn)
                .throttleFirst(1, TimeUnit.MILLISECONDS)
                .subscribe( { Toast.makeText(this,"只响应一次",Toast.LENGTH_LONG).show() })

        //edittext 文本变化监听,将输入的文字转换
        RxTextView.textChanges(et)
                .map({ StringBuilder(it).reverse().toString() })
                .subscribe( {  Toast.makeText(this,"输入的文字："+it,Toast.LENGTH_LONG).show() })


    }
}

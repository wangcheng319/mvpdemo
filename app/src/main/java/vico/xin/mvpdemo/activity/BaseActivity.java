package vico.xin.mvpdemo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import vico.xin.mvpdemo.R;

/**
 * Created by wangc on 2017/5/18
 * E-MAIL:274281610@QQ.COM
 */

public class BaseActivity extends AppCompatActivity {
    public  ProgressDialog progressDialog;

    public TextView none;
    public TextView error;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        progressDialog = new ProgressDialog(this);
        none = (TextView) findViewById(R.id.tv_none);
        error = (TextView) findViewById(R.id.tv_error);
    }
}

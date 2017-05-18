package vico.xin.mvpdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import vico.xin.mvpdemo.R;
import vico.xin.mvpdemo.dto.LoginDto;
import vico.xin.mvpdemo.dto.UserAllInfo;
import vico.xin.mvpdemo.presenter.LoginContract;
import vico.xin.mvpdemo.presenter.LoginPresenter;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements LoginContract.View{

    private TextView textView;

    private LoginContract.Presenter presenter;

    private LoginContract.View loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = (TextView) findViewById(R.id.text);
        loginView = this;

        presenter = new LoginPresenter(this,loginView);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDto login = new LoginDto();
                login.deviceModel = android.os.Build.MODEL;
                login.deviceToken = "";
                login.isEm = false;
                login.latitude = "";
                login.longitude = "";
                login.networkType= "4G";
                login.location = "上海";
                login.systemVersion = "Android-" + android.os.Build.VERSION.RELEASE;
                login.password = "111111";
                login.phone = "15659926163";


                presenter.doLogin(login);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.presenter.unsubscribe();
    }

    @Override
    public void showProgressDialog(boolean isShow) {
        if (isShow){
            progressDialog.show();
        }else{
            if (progressDialog.isShowing() && !isFinishing()){
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onSuccess(UserAllInfo userAllInfo) {
        textView.setText("登录结果："+userAllInfo.toString());

    }

    @Override
    public void onError(String s) {
        textView.setText("登录失败："+s);

    }
}

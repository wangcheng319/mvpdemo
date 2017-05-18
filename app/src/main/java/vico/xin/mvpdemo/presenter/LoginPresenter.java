package vico.xin.mvpdemo.presenter;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import vico.xin.mvpdemo.crypto.RRCrypto;
import vico.xin.mvpdemo.dto.LoginDto;
import vico.xin.mvpdemo.dto.UserAllInfo;
import vico.xin.mvpdemo.http.ApiExecutor;
import vico.xin.mvpdemo.http.HttpResultSubscriber;

/**
 * Created by wangc on 2017/5/18
 * E-MAIL:274281610@QQ.COM
 */

public  class LoginPresenter implements  LoginContract.Presenter {

    private  Context mContext;
    private  LoginContract.View loginView;

    //CompositeDisposable用于存放RxJava中的订阅关系
    private CompositeDisposable compositeDisposable;

    public LoginPresenter(Context context,LoginContract.View loginView){
        this.mContext  = context;
        this.loginView = loginView;
        this.compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void subscribe(Object o) {
        /*处理业务逻辑，网络请求*/
        doLogin((LoginDto) o);
    }

    /*解除订阅关系，避免内存泄漏*/
    @Override
    public void unsubscribe() {

        compositeDisposable.clear();
    }

    @Override
    public void doLogin(LoginDto loginDto) {

        Gson gson = new Gson();
        String json =  gson.toJson(loginDto);

        //显示进度条
        loginView.showProgressDialog(true);

        ApiExecutor.getInstance()
                .Login(RRCrypto.encryptAES(json, "fsgjernfjwnfjeft"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<UserAllInfo>(loginView) {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(UserAllInfo userAllInfo) {
                        loginView.onSuccess(userAllInfo);
                    }

                    @Override
                    public void _onError(Throwable e) {
                        loginView.onError(e.toString());
                    }
                });


    }
}

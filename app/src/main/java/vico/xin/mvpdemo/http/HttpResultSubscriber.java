package vico.xin.mvpdemo.http;

import android.view.View;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import vico.xin.mvpdemo.dto.BaseDto;
import vico.xin.mvpdemo.presenter.BaseView;

/**
 * Created by wangc on 2017/5/18
 * E-MAIL:274281610@QQ.COM
 */

public abstract class HttpResultSubscriber<T> implements Observer<BaseDto<T>> {

    public BaseView view;

    public HttpResultSubscriber(BaseView view) {
        this.view =  view;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseDto<T> t) {
        //隐藏进度条
        this.view.showProgressDialog(false);

        if (t.code ==10000){
            onSuccess(t.response);
        }else if (t.code == 10003){
            _onError(new Throwable("error=" + t.info));
        }else if (t.code == 10401){
            _onError(new Throwable("error=" + t.info));
        }else if (t.code == 10403){
            _onError(new Throwable("error=" + t.info));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        //隐藏进度条
        this.view.showProgressDialog(false);

        _onError(e);
    }

    @Override
    public void onComplete() {

    }



    public abstract void onSuccess(T t);

    public abstract void _onError(Throwable e);
}

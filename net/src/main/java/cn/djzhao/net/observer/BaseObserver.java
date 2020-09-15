package cn.djzhao.net.observer;

import cn.djzhao.net.error.ErrorHandler;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 只需要关心 成功/失败
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure((ErrorHandler.ResponseThrowable) e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(ErrorHandler.ResponseThrowable e);
}

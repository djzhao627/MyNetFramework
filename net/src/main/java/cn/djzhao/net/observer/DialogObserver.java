package cn.djzhao.net.observer;

import io.reactivex.disposables.Disposable;

public abstract class DialogObserver<T> extends BaseObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

        super.onSubscribe(d);
    }

    @Override
    public void onComplete() {
        super.onComplete();
    }
}

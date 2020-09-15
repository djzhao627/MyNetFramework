package cn.djzhao.net.error;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * 异常统一处理
 * @param <T>
 */
public class HttpError<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) {
        return Observable.error(ErrorHandler.handleException(throwable));
    }
}

package com.retrofit.workingschedule.base;

/**
 *
 */
public interface OnSubscriber {

    /**
     * 请求完成
     * @param what
     */
    void onCompleted(int what);

    /**
     * 请求错误
     * @param e
     * @param what
     */
    void onError(Throwable e, int what);

    /**
     * 请求成功处理请求数据
     * @param object
     * @param what
     */
    void onNext(Object object, int what);

}

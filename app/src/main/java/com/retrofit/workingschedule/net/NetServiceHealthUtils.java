package com.retrofit.workingschedule.net;

import android.support.annotation.NonNull;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2016/8/24.
 */

public class NetServiceHealthUtils <C> {
    public static final String APPLYFOR_URI = "http://www.podsfun.com:22309/fenji/interfaces/";//公网
//    public static final String APPLYFOR_URI = "http://192.168.1.201:9080/fenji/interfaces/";

    /**
     * 获取Observable<T></>
     * @param serviceCls service class</>
     * @param <T> Service实体
     * @return
     */
    public static <T> T getService(@NonNull final Class<T> serviceCls) {
        return getRetrofit().create(serviceCls);
    }

    //    public static <T> T getService(@NonNull final Class<T> serviceCls) {
//        return getRetrofit().create(serviceCls);
//    }
    public void invoke(@NonNull final Observable<C> observable, @NonNull final Action1<C> dataHandlers, @NonNull final Subscriber<C> subscribe){
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(dataHandlers)//数据二次加工处理
                .subscribe(subscribe);//请求回调
    }


    /**
     * 配置Retrofit并获取Retrofit对象
     * @return
     */
    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        return new Retrofit.Builder().client(okHttpClient).baseUrl(APPLYFOR_URI)
                .addConverterFactory(GsonConverterFactory.create())//解析器
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

    public static Retrofit getRetrofit(@NonNull String Url) {
        return new Retrofit.Builder().baseUrl(Url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }

}

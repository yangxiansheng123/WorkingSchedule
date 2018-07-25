package com.retrofit.workingschedule.manager;

import android.support.annotation.NonNull;
import android.util.Log;


import com.alibaba.fastjson.JSON;
import com.retrofit.workingschedule.api.RxApi;
import com.retrofit.workingschedule.bean.LoginPostBean;
import com.retrofit.workingschedule.bean.SelectTimeBean;
import com.retrofit.workingschedule.bean.UpdateTimeBean;
import com.retrofit.workingschedule.net.NetServiceUtils;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by admin on /9/26/0026.
 * 管理类
 */

public class Manager {
    private static Manager instance;

    public static Manager getInstance() {
        if (null == instance) {
            instance = new Manager();
        }
        return instance;
    }
    private Manager() {

    }

    /**
     * 用户登录
     *
     * @param account
     * @param subscribe
     * @return
     */
    public Subscriber<LoginPostBean> loginPost(@NonNull String account, @NonNull Subscriber<LoginPostBean> subscribe) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), account);
        Observable<LoginPostBean> observable = NetServiceUtils.getService(RxApi.class).login(body);
        new NetServiceUtils<LoginPostBean>().invoke(observable, loginData, subscribe);
        return subscribe;

    }

    /**
     * 登录数据处理
     */
    private Action1<LoginPostBean> loginData = LoginPostDate -> {
        Log.e("========== ",""+ JSON.toJSONString(LoginPostDate));
    };

    /**
     * 跟新时间表
     */
    public Subscriber<UpdateTimeBean> update_doctor_outpatient_time(@NonNull String account, @NonNull Subscriber<UpdateTimeBean> subscribe) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), account);
        Observable<UpdateTimeBean> observable = NetServiceUtils.getService(RxApi.class).update_doctor_outpatient_time(body);
        new NetServiceUtils<UpdateTimeBean>().invoke(observable, update_doctor_outpatient_time, subscribe);
        return subscribe;
    }


    /**
     * 查询教育经历、过往工作经历
     */
    private Action1<UpdateTimeBean> update_doctor_outpatient_time = UpdateDoctorOutpatientBean -> {
        Log.e("========== ",""+ JSON.toJSONString(UpdateDoctorOutpatientBean));
    };


    /**
     * 查询时间表
     */
    public Subscriber<SelectTimeBean> select_doctor_clinic_time(@NonNull String account, @NonNull Subscriber<SelectTimeBean> subscribe) {
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), account);
        Observable<SelectTimeBean> observable = NetServiceUtils.getService(RxApi.class).select_doctor_clinic_time(body);
        new NetServiceUtils<SelectTimeBean>().invoke(observable, select_doctor_clinic_time, subscribe);
        return subscribe;
    }


    /**
     * 查询时间表
     */
    private Action1<SelectTimeBean> select_doctor_clinic_time = SelectTimeBean -> {
        Log.e("========== ",""+ JSON.toJSONString(SelectTimeBean));
    };

}

package com.retrofit.workingschedule.api;


import com.retrofit.workingschedule.bean.LoginPostBean;
import com.retrofit.workingschedule.bean.SelectTimeBean;
import com.retrofit.workingschedule.bean.UpdateTimeBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import rx.Observable;

/**
 * Created by SEED_DEV2_USER on 2016/8/16.
 */

public interface RxApi {

    /**
     * 登录
     * @param data
     * @return
     */
    @retrofit2.http.POST(Service.LoginURL)
    Observable<LoginPostBean> login(@Body RequestBody data);

    /**
     * 跟新时间表
     * @param data
     * @return
     */
    @retrofit2.http.POST(Service.DOCTOROUTPATIENTTIME)
    Observable<UpdateTimeBean> update_doctor_outpatient_time(@Body RequestBody data);

    /**
     * 查询时间表
     * @param data
     * @return
     */
    @retrofit2.http.POST(Service.SELECTDOCTORCLINICTIME)
    Observable<SelectTimeBean> select_doctor_clinic_time(@Body RequestBody data);


}


package com.retrofit.workingschedule;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.retrofit.workingschedule.adapter.AddTimeAdapter;
import com.retrofit.workingschedule.base.BaseActivity;
import com.retrofit.workingschedule.bean.IsChecked;
import com.retrofit.workingschedule.bean.SelectTimeBean;
import com.retrofit.workingschedule.bean.UpdateTimeBean;
import com.retrofit.workingschedule.constants.Constants;
import com.retrofit.workingschedule.manager.Manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 添加门诊时间表
 *
 * @author yang
 */
public class OutpatientServiceActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private GridView gv_list_show_name1;
    private String token;
    private String selectPeople;
    private AddTimeAdapter adapter;
    private int i = 0;
    private List<IsChecked> list;
    private IsChecked checked;
    private TextView save_work_outpatient_service;

    private String[] mondyarr1 = new String[3];
    private String[] tuesdayarr1 = new String[3];
    private String[] wednesday1 = new String[3];
    private String[] thursday1 = new String[3];
    private String[] friday1 = new String[3];
    private String[] saturdayarr1 = new String[3];
    private String[] sundayarr1 = new String[3];
    AddTimeAdapter.ViewHolder holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outpatient_service);
        SharedPreferences sp = this.getSharedPreferences("UserData", Activity.MODE_PRIVATE);
        token = sp.getString("token", null);
        adapter = new AddTimeAdapter(this);
        list = new ArrayList<>();
        initView();
        initCtrl();
        queryData();

        adapter.setListData(list);
        gv_list_show_name1.setAdapter(adapter);
        gv_list_show_name1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                holder = (AddTimeAdapter.ViewHolder) view.getTag();
                checked = list.get(position);
                if (list.get(position).isChecked()) {
                    checked.setChecked(false);
                    adapter.notifyDataSetChanged();

                } else {
                    checked.setChecked(true);
                    adapter.notifyDataSetChanged();
                }

            }
        });


    }


    private void initView() {
        //头部Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar_outpatient_service1);
        gv_list_show_name1 = (GridView) this.findViewById(R.id.gv_list_show_name1);
        save_work_outpatient_service = (TextView) this.findViewById(R.id.save_work_outpatient_service);
        save_work_outpatient_service.setOnClickListener(this);
    }

    private void initCtrl() {
        //头部toolbar的  设置
        toolbar.setNavigationIcon(R.mipmap.left_m);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    private void queryData() {
        /**
         * 查询时间表
         */
        SelectTimeBean clinicbean = new SelectTimeBean();
        clinicbean.setToken(token);
        Gson gson = new Gson();
        String data = gson.toJson(clinicbean);
        Manager.getInstance().select_doctor_clinic_time(data, getSubscriber(Constants.SELECTDOCTORCLINICTIME));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //保存
            case R.id.save_work_outpatient_service:
                List<String> l2 = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    if (i % 3 == 0) {
                        if (list.get(i).isChecked()) {
                            l2.add("0");
                        } else {
                            l2.add("3");
                        }

                    } else if (i % 3 == 1) {
                        if (list.get(i).isChecked()) {
                            l2.add("1");
                        } else {
                            l2.add("3");
                        }
                    } else if (i % 3 == 2) {
                        if (list.get(i).isChecked()) {
                            l2.add("2");
                        } else {
                            l2.add("3");
                        }
                    }
                }

                for (int i = 0; i < l2.size(); i++) {
                    if (i < 3) {
                        mondyarr1[i] = l2.get(i);
                    } else if (i < 6) {
                        tuesdayarr1[0] = l2.get(3);
                        tuesdayarr1[1] = l2.get(4);
                        tuesdayarr1[2] = l2.get(5);
                    } else if (i < 9) {
                        wednesday1[0] = l2.get(6);
                        wednesday1[1] = l2.get(7);
                        wednesday1[2] = l2.get(8);
                    } else if (i < 12) {
                        thursday1[0] = l2.get(9);
                        thursday1[1] = l2.get(10);
                        thursday1[2] = l2.get(11);
                    } else if (i < 15) {
                        friday1[0] = l2.get(12);
                        friday1[1] = l2.get(13);
                        friday1[2] = l2.get(14);
                    } else if (i < 18) {
                        saturdayarr1[0] = l2.get(15);
                        saturdayarr1[1] = l2.get(16);
                        saturdayarr1[2] = l2.get(17);
                    } else if (i < 21) {
                        sundayarr1[0] = l2.get(18);
                        sundayarr1[1] = l2.get(19);
                        sundayarr1[2] = l2.get(20);
                    }
                }
                UpdateTimeBean bean = new UpdateTimeBean();
                bean.setToken(token);
                bean.setMonday_plan(mondyarr1[0] + "," + mondyarr1[1] + "," + mondyarr1[2]);
                bean.setTuesday_plan(tuesdayarr1[0] + "," + tuesdayarr1[1] + "," + tuesdayarr1[2]);
                bean.setWednesday_plan(wednesday1[0] + "," + wednesday1[1] + "," + wednesday1[2]);
                bean.setThursday_plan(thursday1[0] + "," + thursday1[1] + "," + thursday1[2]);
                bean.setFriday_plan(friday1[0] + "," + friday1[1] + "," + friday1[2]);
                bean.setSaturday_plan(saturdayarr1[0] + "," + saturdayarr1[1] + "," + saturdayarr1[2]);
                bean.setSunday_plan(sundayarr1[0] + "," + sundayarr1[1] + "," + sundayarr1[2]);
                Gson gson = new Gson();
                String data = gson.toJson(bean);
                Manager.getInstance().update_doctor_outpatient_time(data, getSubscriber(Constants.DOCTOROUTPATIENTTIME));
                finish();
                break;
            default:
        }

    }

    @Override
    public void onCompleted(int what) {

    }

    @Override
    public void onError(Throwable e, int what) {

    }

    @Override
    public void onNext(Object object, int what) {
        if (what == Constants.SELECTDOCTORCLINICTIME) {
            SelectTimeBean dbean = (SelectTimeBean) object;
            if (dbean.getCode().equals("0")) {

                String[] mondyarr = dbean.getWorkplan().getMonday_plan().split(",");
                String[] tuesdayarr = dbean.getWorkplan().getTuesday_plan().split(",");
                String[] wednesday = dbean.getWorkplan().getWednesday_plan().split(",");
                String[] thursday = dbean.getWorkplan().getThursday_plan().split(",");
                String[] friday = dbean.getWorkplan().getFriday_plan().split(",");
                String[] saturdayarr = dbean.getWorkplan().getSaturday_plan().split(",");
                String[] sundayarr = dbean.getWorkplan().getSunday_plan().split(",");
                List<String> l = new ArrayList<>();
                l.addAll(Arrays.asList(mondyarr));
                l.addAll(Arrays.asList(tuesdayarr));
                l.addAll(Arrays.asList(wednesday));
                l.addAll(Arrays.asList(thursday));
                l.addAll(Arrays.asList(friday));
                l.addAll(Arrays.asList(saturdayarr));
                l.addAll(Arrays.asList(sundayarr));


                for (int i = 0; i < l.size(); i++) {
                    if (l.get(i).equals("3")) {
                        checked = new IsChecked(false);
                    } else {
                        checked = new IsChecked(true);
                    }

                    list.add(checked);
                }
                adapter.setListData(list);
                adapter.notifyDataSetChanged();
            }

            if (dbean.getCode().equals("99999")) {
//                Toast.makeText(this, dbean.getMessage() + "", Toast.LENGTH_LONG).show();
                for (int i = 0; i <= 20; i++) {
                    checked = new IsChecked(false);
                    list.add(checked);
                }
                adapter.setListData(list);
                adapter.notifyDataSetChanged();
            }
        }

        if (what == Constants.DOCTOROUTPATIENTTIME) {
            UpdateTimeBean bean = (UpdateTimeBean) object;
            if (bean.getCode().equals("0")) {
                Toast.makeText(this, bean.getMessage() + "", Toast.LENGTH_LONG).show();
            }
        }
    }


}

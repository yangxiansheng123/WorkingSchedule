package com.retrofit.workingschedule;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.retrofit.workingschedule.adapter.ShowListAdapter;
import com.retrofit.workingschedule.base.BaseActivity;
import com.retrofit.workingschedule.bean.LoginPostBean;
import com.retrofit.workingschedule.bean.SelectTimeBean;
import com.retrofit.workingschedule.constants.Constants;
import com.retrofit.workingschedule.manager.Manager;
import com.retrofit.workingschedule.utils.ONLYID;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    protected LinearLayout action1;
    protected ListView listViewClinicInfo;
    protected Button button;
    protected EditText editText;
    private int inType;
    private String onlyId;
    private List<String[]> list;
    private ShowListAdapter outPatientAdapter;
    private String token;
    String[] am;
    String[] pm;
    String[] night;
    String[] noData;
    String[] noData1;
    String[] noData2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        SharedPreferences sp = this.getSharedPreferences("UserData", Activity.MODE_PRIVATE);
        token = sp.getString("token", null);
        initView();

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.action1) {

            Intent intent = new Intent(this, OutpatientServiceActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.button) {

            LoginPost(editText.getText().toString(), "123456", 0);
        }
    }

    private void initView() {
        outPatientAdapter = new ShowListAdapter(this);
        list = new ArrayList<>();
        action1 = (LinearLayout) findViewById(R.id.action1);
        action1.setOnClickListener(MainActivity.this);
        listViewClinicInfo = (ListView) findViewById(R.id.listView_clinic_info);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(MainActivity.this);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void LoginPost(String telephone, String mypassword, int type) {
        inType = type;
        //实现登录
        LoginPostBean data = new LoginPostBean();
        data.setTelePhone(telephone);
        data.setPassword(mypassword);
        ONLYID ID = new ONLYID();
        onlyId = ONLYID.onlyId(this);
        data.setAlias(onlyId);
        data.setPlatform("1");
        Gson gson = new Gson();
        String login = gson.toJson(data);
        Manager.getInstance().loginPost(login, getSubscriber(Constants.USERLOGIN_REQUEST));
    }

    /**
     * 查询时间表
     */
    private void queryData() {
        list.clear();
        SelectTimeBean clinicbean = new SelectTimeBean();
        clinicbean.setToken(token);
        Gson gson = new Gson();
        String data = gson.toJson(clinicbean);
        Manager.getInstance().select_doctor_clinic_time(data, getSubscriber(Constants.SELECTDOCTORCLINICTIME));
    }

    @Override
    public void onCompleted(int what) {

    }

    @Override
    public void onError(Throwable e, int what) {

    }

    @Override
    public void onNext(Object object, int what) {
        //登录调接口成功
        if (what == Constants.USERLOGIN_REQUEST) {

            LoginPostBean return1 = (LoginPostBean) object;
            String code = return1.getCode();
            if (code.equals("0")) {
                String phone = return1.getPhone();
                String token = return1.getToken();
                SharedPreferences sp = this.getSharedPreferences("UserData", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("phone", phone);
                editor.putString("token", token);
                editor.commit();


            }
        }

        if (what == Constants.SELECTDOCTORCLINICTIME) {
            SelectTimeBean dbean = (SelectTimeBean) object;
            if (dbean.getCode().equals("0")) {
                String[] mondyarr = new String[3];
                String[] tuesdayarr = new String[3];
                String[] wednesday = new String[3];
                String[] thursday = new String[3];
                String[] friday = new String[3];
                String[] saturdayarr = new String[3];
                String[] sundayarr = new String[3];
                for (int i = 0; i <= 2; i++) {
                    mondyarr = dbean.getWorkplan().getMonday_plan().split(",");
                    tuesdayarr = dbean.getWorkplan().getTuesday_plan().split(",");
                    wednesday = dbean.getWorkplan().getWednesday_plan().split(",");
                    thursday = dbean.getWorkplan().getThursday_plan().split(",");
                    friday = dbean.getWorkplan().getFriday_plan().split(",");
                    saturdayarr = dbean.getWorkplan().getSaturday_plan().split(",");
                    sundayarr = dbean.getWorkplan().getSunday_plan().split(",");

                }

                am = new String[]{mondyarr[0], tuesdayarr[0], wednesday[0], thursday[0], friday[0], saturdayarr[0], sundayarr[0]};
                list.add(am);
                pm = new String[]{mondyarr[1], tuesdayarr[1], wednesday[1], thursday[1], friday[1], saturdayarr[1], sundayarr[1]};
                list.add(pm);
                night = new String[]{mondyarr[2], tuesdayarr[2], wednesday[2], thursday[2], friday[2], saturdayarr[2], sundayarr[2]};
                list.add(night);
                outPatientAdapter.setList(list);
                listViewClinicInfo.setAdapter(outPatientAdapter);
                outPatientAdapter.notifyDataSetChanged();

            } else if (dbean.getCode().equals("99999")) {
//                当前用户没有排班表
                noData = new String[]{"", "", "", "", "", "", ""};
                list.add(noData);
                noData1 = new String[]{"", "", "", "", "", "", ""};
                list.add(noData1);
                noData2 = new String[]{"", "", "", "", "", "", ""};
                list.add(noData2);
                listViewClinicInfo.setAdapter(outPatientAdapter);
                outPatientAdapter.setList(list);
                outPatientAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryData();
    }
}

package com.retrofit.workingschedule.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Subscriber;

/**
 * Created by ZHJH on 2016/5/19.
 */
public abstract class BaseActivity extends AppCompatActivity implements OnSubscriber {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public MySubscriber subscriber;

    public BaseActivity.MySubscriber getSubscriber(){
        return subscriber = new MySubscriber();
    }

    public MySubscriber getSubscriber(int what){
        return subscriber = new MySubscriber(what);
    }

//    @Override
//    public abstract void onCreate(@Nullable Bundle savedInstanceState);

    public class  MySubscriber extends Subscriber{

        private int what = -1;

        public  MySubscriber(){
        }

        public  MySubscriber(int what){
            this.what  = what;
        }

        @Override
        public void onCompleted() {
            Log.e("baseActivity","----------completed");
            BaseActivity.this.onCompleted(what);
        }

        @Override
        public void onError(Throwable e) {
            BaseActivity.this.onError(e, what);
        }

        @Override
        public void onNext(Object object) {

            Log.e("baseActivity","----------onNext");
            BaseActivity.this.onNext(object, what);
        }
    }


}

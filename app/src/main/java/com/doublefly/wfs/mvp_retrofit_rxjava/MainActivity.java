package com.doublefly.wfs.mvp_retrofit_rxjava;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.doublefly.wfs.mvp_retrofit_rxjava.annotation.AnnotationParse;
import com.doublefly.wfs.mvp_retrofit_rxjava.annotation.UserBean;
import com.doublefly.wfs.mvp_retrofit_rxjava.view.MyView;

import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private TextView mTv;
    private MyView mView;
    private Subscriber<SchoolListModel> subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.show);
        mView = (MyView) findViewById(R.id.myview);
        subscriber = new Subscriber<SchoolListModel>() {

            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "completed", Toast.LENGTH_SHORT).show();
                UserBean user = new UserBean();
                user.setUsername("windflysun");
                user.setPassword("123456");
                Toast.makeText(MainActivity.this, AnnotationParse.getInstance(user).parse(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(SchoolListModel schoolListModel) {
                for (SchoolListModel.SchoolListBean bean : schoolListModel.getSchool_list()) {
                    Log.i(TAG, "onNext: " + bean.getSchool_name());
                    mTv.append(bean.getSchool_name() + "\n");
                }
//                Log.i(TAG, "onNext: " + schoolListModel);
//                mTv.setText(schoolListModel.toString());
            }
        };
        HttpMethods.getInstance().getSchoolList(subscriber);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "rotation", 0F, 200F);
                animator.setDuration(2000);
                animator.start();
            }
        });
    }
}

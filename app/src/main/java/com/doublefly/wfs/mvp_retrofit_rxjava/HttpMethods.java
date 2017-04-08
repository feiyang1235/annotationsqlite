package com.doublefly.wfs.mvp_retrofit_rxjava;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by WFS on 2017/4/5.
 */
public class HttpMethods {
    private static final String baseUrl = "http://syyxy.net/";
    private static HttpMethods instance;
    private Retrofit retrofit;
    private SyyxyService syyxyService;

    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(5, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(baseUrl).build();
        syyxyService = retrofit.create(SyyxyService.class);
    }

    public static HttpMethods getInstance() {
        if (instance == null) instance = new HttpMethods();
        return instance;
    }
    public void getSchoolList(Subscriber<SchoolListModel> subscriber){
        syyxyService.getSchoolList().subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}

package com.doublefly.wfs.mvp_retrofit_rxjava;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by WFS on 2017/3/20.
 */
public interface SyyxyService {
    @GET("/default/get_school_list.json")
    Observable<SchoolListModel> getSchoolList();
//    android.database.Observable

}

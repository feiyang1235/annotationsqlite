package com.doublefly.wfs.mvp_retrofit_rxjava;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by LJF on 2016/8/8.
 */
public class SchoolListModel {
    @Override
    public String toString() {
        return "SchoolListModel{" +
                "school_list=" + school_list +
                '}';
    }

    /**
     * school_id : 1
     * school_abbrev : jtzx
     * school_name : 天府新区籍田中学
     */

    private List<SchoolListBean> school_list;

    public List<SchoolListBean> getSchool_list() {
        return school_list;
    }

    public void setSchool_list(List<SchoolListBean> school_list) {
        this.school_list = school_list;
    }

    public static class SchoolListBean implements Comparable<SchoolListBean>, Parcelable {
        private int school_id;
        private String school_abbrev;
        private String school_name;

        @Override
        public String toString() {
            return "SchoolListBean{" +
                    "school_id=" + school_id +
                    ", school_abbrev='" + school_abbrev + '\'' +
                    ", school_name='" + school_name + '\'' +
                    '}';
        }

        public int getSchool_id() {
            return school_id;
        }

        public void setSchool_id(int school_id) {
            this.school_id = school_id;
        }

        public String getSchool_abbrev() {
            return school_abbrev;
        }

        public void setSchool_abbrev(String school_abbrev) {
            this.school_abbrev = school_abbrev;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        @Override
        public int compareTo(SchoolListBean another) {
            return this.getSchool_abbrev().compareTo(another.getSchool_abbrev());
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.school_id);
            dest.writeString(this.school_abbrev);
            dest.writeString(this.school_name);
        }

        public SchoolListBean() {
        }

        protected SchoolListBean(Parcel in) {
            this.school_id = in.readInt();
            this.school_abbrev = in.readString();
            this.school_name = in.readString();
        }

        public static final Parcelable.Creator<SchoolListBean> CREATOR = new Parcelable.Creator<SchoolListBean>() {
            @Override
            public SchoolListBean createFromParcel(Parcel source) {
                return new SchoolListBean(source);
            }

            @Override
            public SchoolListBean[] newArray(int size) {
                return new SchoolListBean[size];
            }
        };
    }
}

package com.renyu.administrator.myapplication.util;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.renyu.administrator.myapplication.BR;

/**
 * Created by Administrator on 2018/11/7.
 */

public class NewslistBean extends BaseObservable {

    private String ctime;
    private String title;
    private String description;
    public String picUrl = "http://img0.imgtn.bdimg.com/it/u=2183314203,562241301&fm=26&gp=0.jpg";

    public NewslistBean(String ctime, String title, String description) {

        this.ctime = ctime;
        this.title = title;
        this.description = description;
    }
     @Bindable
    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
        notifyPropertyChanged(BR.ctime);
    }
    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        notifyPropertyChanged(BR.picUrl);
    }




  @BindingAdapter("android:name=")
    public static void setNewname(TextView view , CharSequence text) {
        final CharSequence oldText = view.getText();
//      if(text=="hahah"){
//          view.setText("2222222222");
//      }else {
        view.setText(text);
//      }
    }



    public void onTtemClick(View pView) {
//        Toast.makeText(pView.getContext(),title, Toast.LENGTH_SHORT).show();
//        setCtime("renyu");
//        setTitle("999");
    }


 }



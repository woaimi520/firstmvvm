package com.renyu.administrator.myapplication;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/11/7.
 */

public class NewslistBean extends BaseObservable {

    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;

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



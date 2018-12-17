package com.renyu.administrator.myapplication.data;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Created by Administrator on 2018/12/15.
 */

public class WeatherDB extends LitePalSupport {

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private int id;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    @Column(unique = true, defaultValue = "unknown")
    private String city;
}

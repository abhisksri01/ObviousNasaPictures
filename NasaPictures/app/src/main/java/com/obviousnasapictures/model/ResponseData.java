package com.obviousnasapictures.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseData{

    @SerializedName("data")
    private List<DataItem> data;

    public void setData(List<DataItem> data){
        this.data = data;
    }

    public List<DataItem> getData(){
        return data;
    }
}
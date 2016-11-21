package com.nakoyagarden.ekapop.restaurant;

/**
 * Created by ekapop on 9/19/2016.
 */
public class ItemData {

    String text;
    Integer imageId;
    public ItemData(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
    public String toString(){
        return text;
    }
}

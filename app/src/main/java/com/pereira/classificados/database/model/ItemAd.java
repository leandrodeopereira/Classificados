package com.pereira.classificados.database.model;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;

import com.pereira.classificados.database.MyStore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ItemAd implements Serializable{

    private String mImage;
    private String mTitle;
    private String mDescription;

    public ItemAd(Cursor c){
        //pegar informacao no banco
        this.mTitle = c.getString(c.getColumnIndex(MyStore.ItemAdTable.TITLE));
        this.mDescription = c.getString(c.getColumnIndex(MyStore.ItemAdTable.DESCRIPTION));

    }

    public ItemAd(String mImage, String mTitle, String mDescription) {
        this.mImage = mImage;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
    }

    public String getImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public static List<ItemAd> getList(Activity activity){
        List<ItemAd> items = new ArrayList<>();

        return items;
    }
}

package com.pereira.classificados.database.model;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pereira.classificados.App;
import com.pereira.classificados.activity.FormItemActivity;
import com.pereira.classificados.database.MyStore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ItemAd implements Serializable{
    private String mGuid;
    private String mImage;
    private String mTitle;
    private String mDescription;
    private BigDecimal mPrice;
    

    public ItemAd(Cursor c){
        //pegar informacao no banco
        this.mGuid = c.getString(c.getColumnIndex(MyStore.ItemAdTable.GUID));
        this.mTitle = c.getString(c.getColumnIndex(MyStore.ItemAdTable.TITLE));
        this.mDescription = c.getString(c.getColumnIndex(MyStore.ItemAdTable.DESCRIPTION));
        String price = c.getString(c.getColumnIndex(MyStore.ItemAdTable.PRICE));
        this.mPrice = new BigDecimal(price != null ? price : "0");
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

    public String getGuid() {
        return mGuid;
    }

    public void setGuid(String mGuid) {
        this.mGuid = mGuid;
    }

    public static ItemAd getByGuid(Activity activity, String guid) {
        if (guid == null) return null;
        //pesquisa no banco
        SQLiteDatabase db = App.getInstance(activity).getDbHelper().getReadableDatabase();
        try(
                Cursor c = db.query(MyStore.ItemAdTable.TABLE_NAME, null,
                        MyStore.ItemAdTable.GUID + " = ?"/*?-eh o proximo paramentro(guid)*/, new String[]{guid},
                        null, null, null, "1"/*limite da query, retornar 1 resultado*/)
                ){
            if(c.moveToNext()){
                return new ItemAd(c);
            }
        }
        return null; // caso nao encontre ninguem
    }

    public BigDecimal getPrice() {
        return mPrice;
    }

    public void setPrice(BigDecimal mPrice) {
        this.mPrice = mPrice;
    }
}

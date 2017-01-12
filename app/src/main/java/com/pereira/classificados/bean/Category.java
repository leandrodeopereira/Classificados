package com.pereira.classificados.bean;

import java.io.Serializable;

/**
 * Created by Aluno on 11/01/2017.
 */

public class Category  implements Serializable{

    private String mId;
    private String mDescription;

    public Category(String mId, String mDescription) {
        this.mId = mId;
        this.mDescription = mDescription;
    }

    public String getId() {
        return mId;
    }

    public void setId(String mId) {
        this.mId = mId;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @Override
    public String toString(){
        return mDescription;
    }
}

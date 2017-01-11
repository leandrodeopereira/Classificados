package com.pereira.classificados;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ItemAd {

    private String mImage;
    private String mTitle;
    private String mDescription;

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
}

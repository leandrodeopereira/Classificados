package com.pereira.classificados;

import android.app.Application;

/**
 * Created by Aluno on 12/01/2017.
 */

public class App extends Application {

    private Long mCurrentTime;

    public Long getCurrentTime() {
        if(mCurrentTime == null) {
            mCurrentTime = 0L;
        }
        return mCurrentTime;
    }

    public void setCurrentTime(Long mCurrentTime) {
        this.mCurrentTime = mCurrentTime;
    }
}

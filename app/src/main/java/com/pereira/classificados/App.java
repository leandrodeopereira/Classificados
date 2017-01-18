package com.pereira.classificados;

import android.app.Activity;
import android.app.Application;

import com.pereira.classificados.database.DBHelper;

/**
 * Created by Aluno on 12/01/2017.
 */

public class App extends Application {
    // para soh ter uma instancia do banco para a aplicacao inteira
    private DBHelper mDbHelper;

    private Long mCurrentTime;

    @Override
    public void onCreate() {
        super.onCreate();
        // nao importa a activity ele sempre usara o mesmo db
        mDbHelper = new DBHelper(getApplicationContext());
    }

    public static App getInstance(Activity activity){
        return (App) activity.getApplication();
    }

    public DBHelper getDbHelper(){
        return mDbHelper;
    }

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

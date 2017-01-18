package com.pereira.classificados.activity;

import android.os.Bundle;
import android.view.View;

import com.pereira.classificados.R;

/**
 * Created by Android on 17/01/2017.
 */

public class FormItemActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_item);
        setupToolbar(R.string.form_title_activity);
    }

    public void save(View view) {

    }
}

package com.pereira.classificados.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

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

        //nao abrir o teclado(esconde-lo) quando abrir o edit
        getWindow().setSoftInputMode((
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                ));
    }

    public void save(View view) {

    }
}

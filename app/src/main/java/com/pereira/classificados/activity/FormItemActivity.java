package com.pereira.classificados.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.pereira.classificados.App;
import com.pereira.classificados.R;
import com.pereira.classificados.database.MyStore;
import com.pereira.classificados.database.model.ItemAd;

import java.util.UUID;

/**
 * Created by Android on 17/01/2017.
 */

public class FormItemActivity extends BaseActivity {

    private EditText mEtTitle;
    private EditText mEtDescription;

    private ItemAd mItemAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_item);
        setupToolbar(R.string.form_title_activity);

        //nao abrir o teclado(esconde-lo) quando abrir o edit
        getWindow().setSoftInputMode((
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                ));

        init();

        Intent intent = getIntent();
        if (intent != null){
            String itemGuid = intent.getStringExtra(MyStore.ItemAdTable.GUID);
            Log.d(TAG, itemGuid);
            //pegar o objeto a partir da guid dele
            mItemAd = ItemAd.getByGuid(this);
            if(mItemAd != null){
                getSupportActionBar().setTitle(mItemAd.getTitle());

                mEtTitle.setText(mItemAd.getTitle());
                mEtDescription.setText(mItemAd.getDescription());

            }

        }
    }

    private void init(){
        mEtTitle = (EditText) findViewById(R.id.et_title);
        mEtDescription = (EditText) findViewById(R.id.et_description);
    }

    public void save(View view) {
        String title = mEtTitle.getText().toString();
        String description = mEtDescription.getText().toString();

        ContentValues values = new ContentValues();
        values.put(MyStore.ItemAdTable.TITLE, title);
        values.put(MyStore.ItemAdTable.DESCRIPTION, description);

        SQLiteDatabase db = App.getInstance(this).getDbHelper().getWritableDatabase();
        // estou criando um novo
        if(mItemAd == null){
            values.put(MyStore.ItemAdTable.GUID, UUID.randomUUID().toString());
            db.insert(MyStore.ItemAdTable.TABLE_NAME, null, values);
            // comeca uma nova
            startActivity(new Intent(this, FormItemActivity.class));
        } else {

        }
        // fecha a atual
        finish();
    }
}

package com.pereira.classificados.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pereira.classificados.App;
import com.pereira.classificados.R;
import com.pereira.classificados.database.MyStore;
import com.pereira.classificados.database.model.ItemAd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Android on 17/01/2017.
 */

public class FormItemActivity extends BaseActivity {

    private static final int CAPTURE_REQUEST = 0;

    private EditText mEtTitle;
    private EditText mEtDescription;
    private EditText mEtPrice;
    private ImageView mImImage;

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

            //pegar o objeto a partir da guid dele
            mItemAd = ItemAd.getByGuid(this, itemGuid);
            if(mItemAd != null){
                Log.d(TAG, itemGuid);
                getSupportActionBar().setTitle(mItemAd.getTitle());

                mEtTitle.setText(mItemAd.getTitle());
                mEtDescription.setText(mItemAd.getDescription());
                mEtPrice.setText(mItemAd.getPrice().toString());

            }

        }
    }

    private void init(){
        mEtTitle = (EditText) findViewById(R.id.et_title);
        mEtDescription = (EditText) findViewById(R.id.et_description);
        mEtPrice = (EditText) findViewById(R.id.et_price);
        mImImage = (ImageView) findViewById(R.id.iv_image);

        mImImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, CAPTURE_REQUEST);
                }
            }
        });
    }

    public void save(View view) {
        //pegando os dados da tela
        String title = mEtTitle.getText().toString();
        String description = mEtDescription.getText().toString();
        String price = mEtPrice.getText().toString();

        ContentValues values = new ContentValues();
        values.put(MyStore.ItemAdTable.TITLE, title);
        values.put(MyStore.ItemAdTable.DESCRIPTION, description);
        values.put(MyStore.ItemAdTable.PRICE, price);

        SQLiteDatabase db = App.getInstance(this).getDbHelper().getWritableDatabase();
        // estou criando um novo
        if(mItemAd == null){
            values.put(MyStore.ItemAdTable.GUID, UUID.randomUUID().toString());
            db.insert(MyStore.ItemAdTable.TABLE_NAME, null, values);
            // comeca uma nova
            startActivity(new Intent(this, FormItemActivity.class));
        } else {
            db.update(MyStore.ItemAdTable.TABLE_NAME, values,
                    MyStore.ItemAdTable.GUID + " = ?"/*proximo parametro*/, new String[]{mItemAd.getGuid()});
            ItemAd itemAd = ItemAd.getByGuid(this, mItemAd.getGuid());
            //retornando de parametro e depois ir em action edit em detail
            Intent intent = new Intent();
            intent.putExtra(DetailActivity.ITEM_KEY, itemAd);
            setResult(RESULT_OK, intent);

        }
        // fecha a atual
        finish();
    }

    //desparar quando voltar da camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // gravou no diretorio de imagens do android
        if(requestCode == CAPTURE_REQUEST && resultCode == RESULT_OK){
            Bitmap btm = (Bitmap) data.getExtras().get("data");
            if(btm == null) return;

            String imageName = UUID.randomUUID().toString() + ".jpg";
            File file = new File(getFilesDir().getAbsolutePath(),imageName);
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                btm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // pegando a imagem
            Bitmap btm2 = BitmapFactory.decodeFile(getFilesDir().getAbsolutePath() + "/"+ imageName);
            mImImage.setImageBitmap(btm2);

        }
    }
}

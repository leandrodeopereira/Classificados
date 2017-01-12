package com.pereira.classificados.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.pereira.classificados.R;
import com.pereira.classificados.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 11/01/2017.
 */

public class FilterActivity extends BaseActivity {

    public static final String CATEGORY_KEY = "CATEGORY_KEY";

    private Spinner mSpCategory;
    private String mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_filter);
        setupToolbar(R.string.filter_activity_title); // settando a toolbar
        init();

        List<Category> items = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            items.add(new Category(String.valueOf(i),
                    String.format("Categoria %s", i)));
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items);
        mSpCategory.setAdapter(adapter);

        String categoryId = getPref().getString(CATEGORY_KEY, null);
        if(categoryId != null){
            for (int i = 0; i < items.size(); i++){
                if(items.get(i).getId().equals(categoryId)) {
                    mSpCategory.setSelection(i);
                    break;
                }
            }
        }
    }

    private void init(){
        mSpCategory = (Spinner) findViewById(R.id.sp_category);
    }

    @Override
    protected void onStop() {

        Category category = (Category) mSpCategory.getSelectedItem();
        getPref().edit().putString(CATEGORY_KEY, category.getId()).apply(); // usando o apply, ele garate que vai salvar em alguma gora o commit nao

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(CATEGORY_KEY, (Category) mSpCategory.getSelectedItem());
        setResult(RESULT_OK, intent);

        super.onBackPressed();
    }

    //metodos para persistir o dado java, para caso de rotacao de tela
    //salva
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("MY_KEY", mText);
    }
    //restaurar
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mText = savedInstanceState.getString("MY_KEY");
    }

    public void show(View view) {
        Toast.makeText(this, mText, Toast.LENGTH_SHORT).show();
    }

    public void save(View view) {
        mText = "Meu texto";
    }


}

package com.pereira.classificados.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.pereira.classificados.bean.Category;
import com.pereira.classificados.bean.ItemAd;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ListActivity extends BaseActivity {

    private RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        init();

        List<ItemAd> itens = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            itens.add(new ItemAd(null, String.format("Item %s", i), String.format("Descrição do meu item da minha lista "
                    + "de Recycleview do Curso Android da PUCRS %s", i)));
        }

        ListAdapter adapter = new ListAdapter(this, itens);

        mRvList.setAdapter(adapter);
    }

    private void init() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Category category = (Category) data.getSerializableExtra(FilterActivity.CATEGORY_KEY);
        Toast.makeText(this, category.getDescription(),Toast.LENGTH_SHORT).show();
    }

    public void filter(View view) {
        startActivityForResult(new Intent(this, FilterActivity.class), 0);
    }
}

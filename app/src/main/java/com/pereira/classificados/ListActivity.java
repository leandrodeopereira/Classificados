package com.pereira.classificados;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
            itens.add(new ItemAd(null, String.format("Item %s", i), String.format("Descrição do meu item da minha lista " + "de Recycleview do Curso Android da PUCRS %s", i)));
        }

        ListAdapter adapter = new ListAdapter(itens);
        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.setAdapter(adapter);
    }

    private void init() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
    }
}

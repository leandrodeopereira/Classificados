package com.pereira.classificados.tasks;

import android.os.AsyncTask;
import android.view.View;

import com.pereira.classificados.activity.BaseActivity;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.bean.ItemAd;

import java.util.List;

/**
 * Created by Android on 17/01/2017.
 */
//AsyncTask< o q vai receber , valor que vai passar no nosso progresso , o q vai retornar>
public class LoadDataTask extends AsyncTask<Void, Integer,Boolean > {

    private List<ItemAd> mItems;
    private ListAdapter mAdapter;
    private BaseActivity mContext;
    private View mSpinner;
    private View mRvList;

    public LoadDataTask(List<ItemAd> mItems, ListAdapter mAdapter, BaseActivity mContext, View mSpinner, View mRvList) {
        this.mItems = mItems;
        this.mAdapter = mAdapter;
        this.mContext = mContext;
        this.mSpinner = mSpinner;
        this.mRvList = mRvList;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // simulacao de processo de informacao
        try {
            Thread.sleep(3 * 1000); // 3 seg (simular busca de banco)
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 0; i < 50; i++) {
            mItems.add(new ItemAd(null, String.format("Item %s", i), String.format("Descrição do meu item da minha lista "
                    + "de Recycleview do Curso Android da PUCRS %s", i)));
        }

        return true;
    }

    @Override
    protected void onPostExecute(Boolean sucess) {
        super.onPostExecute(sucess);

        if(sucess) {
            mAdapter.notifyDataSetChanged();
            mContext.replaceView(mSpinner, mRvList);
        }
    }
}

package com.pereira.classificados.tasks;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.pereira.classificados.R;
import com.pereira.classificados.activity.BaseActivity;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.database.model.ItemAd;

import java.util.List;

/**
 * Created by Android on 17/01/2017.
 */
// Substitui a new thread, é uma maneira mais organizada de chamar novas threads
//AsyncTask< o q vai receber , valor que vai passar no nosso progresso , o q vai retornar>
public class LoadDataTask extends AsyncTask<Void, Integer,Boolean > {

    private List<ItemAd> mItems;
    private ListAdapter mAdapter;
    private BaseActivity mContext;
    private View mSpinner;
    private View mRvList;
    private TextView mTvProgress;

    public LoadDataTask(List<ItemAd> mItems, ListAdapter mAdapter, BaseActivity mContext,
                        View mSpinner, View mRvList, TextView mTvProgress) {
        this.mItems = mItems;
        this.mAdapter = mAdapter;
        this.mContext = mContext;
        this.mSpinner = mSpinner;
        this.mRvList = mRvList;
        this.mTvProgress = mTvProgress;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        // simulacao de processo de informacao
        try {
            Thread.sleep(1 * 1000); // 1 seg (simular busca de banco)
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }

        for (int i = 1; i < 2; i++) {

            try {
                Thread.sleep(1); // (simular busca de banco)
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }

            mItems.add(new ItemAd(null, String.format("Item %s", i), String.format("Descrição do meu item da minha lista "
                    + "de Recycleview do Curso Android da PUCRS %s", i)));

            int progress = (i * 100) / 50;
            publishProgress(progress);

        }

        return true;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Fazer sempre que carregar os dados

        mTvProgress.setText(R.string.preparing_data);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        mTvProgress.setText(mContext.getString(R.string.progress_data, values[0]));
    }

    @Override
    protected void onPostExecute(Boolean sucess) {
        super.onPostExecute(sucess);

        if(sucess) {
            mAdapter.notifyDataSetChanged();
            mContext.replaceView(mSpinner, mRvList);

            if (mItems.size() > 0) {
                mContext.hideView(mTvProgress);
            } else {
                mTvProgress.setText(R.string.no_data);
            }
        }
    }
}

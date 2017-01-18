package com.pereira.classificados.tasks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import com.pereira.classificados.App;
import com.pereira.classificados.R;
import com.pereira.classificados.activity.BaseActivity;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.database.MyStore;
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

        //consumo do banco
        SQLiteDatabase db = App.getInstance(mContext).getDbHelper().getReadableDatabase();
        // quando usar o cursor, tem que fechar cursor.close()
        // depois da api 19 ele fexha sozinho
        try (
            Cursor cursor = db.query(MyStore.ItemAdTable.TABLE_NAME,
                    null, null, null, null, null, null);
        ) {
            int i = 0;
            int count = cursor.getCount(); // qnts tem para percorrer
            while (cursor.moveToNext()){
                ItemAd item = new ItemAd(cursor);
                mItems.add(item);

                int progress = (i * 100) / count;
                publishProgress(progress);

                i++;

                try {
                    Thread.sleep(100); // (simular busca de banco)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }


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

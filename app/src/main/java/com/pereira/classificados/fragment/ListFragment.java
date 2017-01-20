package com.pereira.classificados.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pereira.classificados.R;
import com.pereira.classificados.activity.BaseActivity;
import com.pereira.classificados.activity.FilterActivity;
import com.pereira.classificados.activity.FormItemActivity;
import com.pereira.classificados.activity.ListActivity;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.database.model.ItemAd;
import com.pereira.classificados.tasks.LoadDataTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 19/01/2017.
 */

public class ListFragment extends Fragment {

    public static final String IS_LOCAL = "IS_LOCAL";

    private RecyclerView mRvList;
    private ListAdapter mAdapter;
    private List<ItemAd> mItems;
    private ProgressBar mSpinner;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mTvProgress;
    private Button mBtnFilter;
    private FloatingActionButton mBtnAdd;

    private boolean mIsLocal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // equivale ao setcontentview do oncreate
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        init(view);

        mIsLocal = getArguments().getBoolean(IS_LOCAL);

        mItems = new ArrayList<>();
        mAdapter = new ListAdapter(getActivity()/*fragment nao eh um contexto, eh visual*/, mItems);
        mRvList.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new AddItemTask().execute("Novo item");
            }
        });

        loadData();
        //botao de filtar em fragment tem que ser via codigo java
        mBtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filter();
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newItem();
            }
        });

        return view;
    }

    private void init(View view){
        mRvList = (RecyclerView) view.findViewById(R.id.rv_list);
        mSpinner = (ProgressBar) view.findViewById(R.id.spinner);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mTvProgress = (TextView) view.findViewById(R.id.tv_progress);
        mBtnFilter = (Button) view.findViewById(R.id.btn_filter);
        mBtnAdd = (FloatingActionButton) view.findViewById(R.id.btn_add);
    }

    public void loadData(){
        mRvList.setVisibility(View.INVISIBLE);
        mSpinner.setVisibility(View.VISIBLE);
        mItems.clear();

        if (mIsLocal) {
            LoadDataTask loadDataTask =
                    new LoadDataTask(mItems, mAdapter, (BaseActivity) getActivity(), mSpinner, mRvList, mTvProgress);
            loadDataTask.execute();
        } else {
            loadServerData();
        }
    }

    private void loadServerData(){

    }

    public void filter() {
        startActivityForResult(new Intent(getActivity(), FilterActivity.class), 0);
    }

    class AddItemTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                Thread.sleep(2* 1000); // 2 seg
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }

            String title = strings[0];

            mItems.add(0, new ItemAd(null, title , "Minha descrição" +
                    "do meu item adicionado da minha aplicação"));
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess) {
            super.onPostExecute(sucess);
            if (sucess) {
                mAdapter.notifyItemRangeChanged(0, mItems.size());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    }

    public void newItem() {
        Intent intent = new Intent(getActivity(), FormItemActivity.class);
        intent.putExtra(IS_LOCAL, mIsLocal);
        startActivity(intent);
    }
}

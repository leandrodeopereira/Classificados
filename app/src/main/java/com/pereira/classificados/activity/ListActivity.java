package com.pereira.classificados.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.pereira.classificados.bean.Category;
import com.pereira.classificados.bean.ItemAd;
import com.pereira.classificados.adapter.ListAdapter;
import com.pereira.classificados.R;
import com.pereira.classificados.sevice.ToastService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ListActivity extends BaseActivity {

    private final int REQUEST_PERMISSION_CALL_PHONE = 0;
    private final int REQUEST_PERMISSION_SMS = 1;

    private RecyclerView mRvList;
    private ListAdapter mAdapter;
    private List<ItemAd> mItems;
    private ProgressBar mSpinner;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);
        setupToolbar(R.string.list_activity_title); // settando a toolbar
        init();

        mItems = new ArrayList<>();
        mAdapter = new ListAdapter(this, mItems);
        mRvList.setAdapter(mAdapter);

        mRvList.setVisibility(View.INVISIBLE);
        mSpinner.setVisibility(View.VISIBLE);

        // é necessário criar uma thread nova para carregar os dados do banco por exemplo
        // Thread secundário
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5 * 1000); // 5 seg (simular busca de banco)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Aqui seria onde colocar algo para pegar dados
                loadData();
            }
        }).start();

        // Thread UI(principal)
//        try {
//            Thread.sleep(5 * 1000); // 5 seg (simular busca de banco)
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2* 1000); // 2 seg
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        mItems.add(0, new ItemAd(null, "Novo Item " +  (mItems.size()-49) , "Minha descrição" +
                                "do meu item adicionado da minha aplicação"));

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyItemRangeChanged(0, mItems.size());
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });
                    }
                }).start();

            }
        });

        //
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra(ToastService.KEY_MSG);

                Toast.makeText(ListActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }, new IntentFilter(ToastService.ACTION_FILTER));
    }

    private void loadData(){
        for (int i = 0; i < 50; i++) {
            mItems.add(new ItemAd(null, String.format("Item %s", i), String.format("Descrição do meu item da minha lista "
                    + "de Recycleview do Curso Android da PUCRS %s", i)));
        }
        // Avisar para rodar na thread UI
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                replaceView(mSpinner,mRvList);
//                mSpinner.setVisibility(View.INVISIBLE);
//                mRvList.setVisibility(View.VISIBLE);
            }
        });
        //falar que os dados foram alterados
    }

    @Override
    protected void setupToolbar(int title) {
        super.setupToolbar(title);
        //botao de voltar nao aparecer na tela inicial
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Spinner spinner = (Spinner) findViewById(R.id.sp_category);
        List<Category> items = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            items.add(new Category(String.valueOf(i),
                    String.format("Categoria %s", i)));
        }
        ArrayAdapter adapter = new ArrayAdapter(getSupportActionBar().getThemedContext()/*pegar o contexto da nossa toolbar*/,
                                                            android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
    }

    private void init() {
        mRvList = (RecyclerView) findViewById(R.id.rv_list);
        mSpinner = (ProgressBar) findViewById(R.id.spinner);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //necessario baixar o pacote design do google para as opcoes do toolbar
        switch (item.getItemId()){
            case R.id.change_view:
                if (mRvList.getLayoutManager() instanceof GridLayoutManager){
                    mRvList.setLayoutManager(new LinearLayoutManager(this));
                } else {
                    mRvList.setLayoutManager(new GridLayoutManager(this, 2));
                }
                // animacao de troca de visualizacao
                mRvList.getAdapter().notifyItemRangeChanged(0, mRvList.getAdapter().getItemCount());
                break;

            case R.id.action_toast:
                Toast.makeText(this, R.string.show_toast, Toast.LENGTH_SHORT).show();
                break;

            case  R.id.action_snackbar:
                Snackbar snackbar = Snackbar.make(mToolbar, R.string.show_snackbar, Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction(R.string.ok, new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        showDialog();
                    }
                }).show();
                break;

            case R.id.action_call:
                makeCall();
                break;

            case  R.id.action_browser:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                startActivity(intent2);
                break;

           case R.id.action_request_sms:
                requestSMS();
                break;
        }
        // retorna para fazer o metodo da BaseActivity
        return super.onOptionsItemSelected(item);
    }

    // como é uma permicao perigosa, precisa fazer uma verificacao antes
    private void makeCall(){
        // se eu tenho a permissao faz a acao
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED)  {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:55051980488150"));
            startActivity(intent);
        }else // pedir pro usuario a permissao, se puder pedir, ou seja, ele ainda nao negou
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                String[]  permissions = new String[] {Manifest.permission.CALL_PHONE};
                ActivityCompat.requestPermissions(this, permissions ,  REQUEST_PERMISSION_CALL_PHONE );
        } else {// se ele jah negou, avisar para ele ativar
                Toast.makeText(this, R.string.request_permission, Toast.LENGTH_SHORT).show();
            }
    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name)
                .setMessage(R.string.my_msg)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) {
            switch (requestCode) {
                case REQUEST_PERMISSION_CALL_PHONE:
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        makeCall();
                    }
                    break;

                case REQUEST_PERMISSION_SMS:
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                            grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, R.string.request_sms_enable, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    private void requestSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                == PackageManager.PERMISSION_DENIED ) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)
                    && !ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                String[] permissions = new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS};
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSION_SMS);
            } else {// se ele jah negou, avisar para ele ativar
                Toast.makeText(this, R.string.request_permission, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

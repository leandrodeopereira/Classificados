package com.pereira.classificados;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aluno on 09/01/2017.
 */

public class DetailActivity extends BaseActivity {

    private ImageView mIvImage;
    private TextView mTvTitle;
    private TextView mTvDescription;
    private TextView mTvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail); // setar o contexto da view
        init();
        mIvImage.setImageResource(R.drawable.google_pixel);
        mTvTitle.setText("Google Pixel 64GB");
        mTvDescription.setText("Muito bom!!");
        mTvTotal.setText(getString(R.string.total_label,"3.800,90"));
    }

    private void init(){
        mIvImage = (ImageView) findViewById(R.id.iv_image);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mTvDescription = (TextView) findViewById(R.id.tv_description);
        mTvTotal = (TextView) findViewById(R.id.tv_total);

        //button in Java
        Button btnBuy = (Button) findViewById(R.id.btn_buy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Comprou!!", Toast.LENGTH_LONG).show();
            }
        });
    }
    //button in xml
    public void favor(View view){
        Toast.makeText(this, "Favoritou!!", Toast.LENGTH_LONG).show();
    }
}

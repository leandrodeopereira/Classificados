package com.pereira.classificados.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pereira.classificados.database.model.ItemAd;
import com.pereira.classificados.R;
import com.pereira.classificados.activity.DetailActivity;

import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    private List<ItemAd> mItens;
    private Context mContext;

    public ListAdapter(Context context, List<ItemAd> itens){
        mItens = itens;
        mContext = context;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        final ItemAd item = mItens.get(position);

        holder.ivImage.setImageResource(R.mipmap.ic_launcher);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_KEY, item);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder{


        ImageView ivImage;
        TextView tvTitle;
        TextView tvDescription;

        public ItemHolder(View itemView) {
            super(itemView);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}

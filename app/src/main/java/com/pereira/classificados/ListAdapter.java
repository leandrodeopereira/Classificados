package com.pereira.classificados;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aluno on 10/01/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {

    private List<ItemAd> mItens;

    public ListAdapter(List<ItemAd> itens){
        mItens = itens;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null));
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ItemAd item = mItens.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mItens.size();
    }

    static class ItemHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvDescription;

        public ItemHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        }
    }
}

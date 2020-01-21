package com.prashant.androidcodingexercise.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.prashant.androidcodingexercise.R;
import com.prashant.androidcodingexercise.data.FactModel;
import com.prashant.androidcodingexercise.databinding.ItemListFactsBinding;

import java.util.ArrayList;

public class FactsAdapter extends RecyclerView.Adapter<FactsAdapter.MyViewHolder> {
    private ArrayList<FactModel> factsList = new ArrayList<>();


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListFactsBinding itemListFactsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_facts, parent, false);
        return new MyViewHolder(itemListFactsBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindDataWithView(holder.getAdapterPosition());
    }


    @Override
    public int getItemCount() {
        return factsList == null ? 0 : factsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemListFactsBinding itemListFactsBinding;

        public MyViewHolder(ItemListFactsBinding ItemListFactsBinding) {
            super(ItemListFactsBinding.getRoot());
            this.itemListFactsBinding = ItemListFactsBinding;
        }

        private void bindDataWithView(int position) {
            itemListFactsBinding.setFactsModel(factsList.get(position));

        }
    }

    // setting data
    public void setData(ArrayList<FactModel> AllFactsList) {
        if (factsList != null)
            factsList.clear();
        else
            factsList = new ArrayList<>();

        // Don't add facts that are null
        for (FactModel factModel : AllFactsList) {
            if (factModel.getTitle() == null && factModel.getDescription() == null && factModel.getImageHref() == null)
                continue;
            else
                factsList.add(factModel);
        }

        notifyDataSetChanged();
    }
}

package com.example.mailapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MassageAdapter extends RecyclerView.Adapter<MassageAdapter.MassageViewHoder> implements Filterable {
    private Context mContext;
    private List<MassageModel> modelList;
    private List<MassageModel> modelListFull;

    public MassageAdapter(Context mContext, List<MassageModel> modelList) {
        this.mContext = mContext;
        this.modelList = modelList;
        modelListFull = new ArrayList<>(modelList);
    }




    public class MassageViewHoder extends RecyclerView.ViewHolder
    {
        public TextView gonderen,alici,konu,ileti;

        public MassageViewHoder(@NonNull View itemView) {
            super(itemView);
            gonderen = itemView.findViewById(R.id.gonderen);
            alici = itemView.findViewById(R.id.alici);
            konu = itemView.findViewById(R.id.konu);
            ileti = itemView.findViewById(R.id.ileti);


        }
    }
    @NonNull
    @Override
    public MassageViewHoder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.desgin_recycler_row,parent,false);
        return new MassageViewHoder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MassageAdapter.MassageViewHoder holder, int position) {
        MassageModel model = modelList.get(position);
        holder.gonderen.setText(model.gonderen);
        holder.alici.setText(model.alici);
        holder.konu.setText(model.konu);
        holder.ileti.setText(model.ileti);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public Filter getFilter() {
        return modelFilter;
    }
    private Filter modelFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<MassageModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0)
            {
                filteredList.addAll(modelListFull);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (MassageModel model : modelListFull)
                {
                    if (model.gonderen.toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(model);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            modelList.clear();
            modelList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };


}

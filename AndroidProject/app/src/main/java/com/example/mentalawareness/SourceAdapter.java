package com.example.mentalawareness;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.SourceViewHolder> {

    public static class SourceViewHolder extends RecyclerView.ViewHolder {
        public SourceViewHolder(View v) {
            super(v);
        }
    }
    private SourcesModel theModel;

    public SourceAdapter() {
        theModel = SourcesModel.getModel();
    }

    @NonNull
    @Override
    public SourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_list_view,parent,false);
        SourceViewHolder sh = new SourceViewHolder(v);
        return sh;
    }

    @Override
    public void onBindViewHolder(@NonNull SourceViewHolder holder, int position) {
        TextView sourceTV = holder.itemView.findViewById(R.id.sourceTV);
        sourceTV.setText(theModel.myCite.get(position).link);
    }

    @Override
    public int getItemCount() {
        return theModel.myCite.size();
    }
}

package com.example.mentalawareness;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cometchat.pro.models.Group;

import java.util.List;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder> {


    class GroupsViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameTextView;
        LinearLayout containerLayout;

        public GroupsViewHolder(View itemView){
            super(itemView);
            groupNameTextView = itemView.findViewById(R.id.groupNameTV);
            containerLayout = itemView.findViewById(R.id.containerLayout);
        }

        public void bind(Group group) {
            groupNameTextView.setText(group.getName());
            containerLayout.setOnClickListener(View -> ChatPageActivity.start(context, group.getGuid()));
        }
    }

    private List<Group> groups;
    private Context context;

    public GroupsAdapter(List<Group> groups, Context context){
        this.groups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupsAdapter.GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupsViewHolder (LayoutInflater.from(context).inflate(R.layout.group_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupsAdapter.GroupsViewHolder holder, int position) {
        holder.bind(groups.get(position));
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
}

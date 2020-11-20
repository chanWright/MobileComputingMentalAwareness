package com.example.mentalawareness.Chat_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.GroupsRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;
import com.example.mentalawareness.R;

import java.util.List;

public class GroupListActivity extends AppCompatActivity {
    GroupsAdapter groupsAdapter;
    RecyclerView groupRv;

    public static void start(Context context) {
        Intent starter = new Intent(context, GroupListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        TextView nameET = findViewById(R.id.nameET);
        nameET.setText(CometChat.getLoggedInUser().getName());

        ImageView imv = findViewById(R.id.backBTN);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Create group request
        Button createGroupBTN = findViewById(R.id.createGroupBTN);
        createGroupBTN.setOnClickListener(view -> {
            createGroup();
        });

        //Retrieve Groups
        getGroupList();
    }

    private void createGroup() {
        Log.d("Create Group: ", " I entered this function");
        CreateGroupActivity.start(this);
        updateAdapter();
    }

    private void getGroupList() {

        GroupsRequest groupsRequest = new GroupsRequest.GroupsRequestBuilder().build();
        fetchRequest(groupsRequest);
    }

    private void fetchRequest(GroupsRequest groupsRequest) {
        groupsRequest.fetchNext(new CometChat.CallbackListener<List<Group>>() {
            @Override
            public void onSuccess(List <Group> list) {
                Log.d("Group List: ", "I entered this function onSucess");
                Log.d("Group Request", "Groups list fetched successfully: " + list.size());
                updateUI(list);
            }
            @Override
            public void onError(CometChatException e) {
                Log.d("Group List: ", "I entered this function on fail");
                Log.d("Group Request", "Groups list fetching failed with exception: " + e.getMessage());
            }
        });
    }

    private void updateUI(List<Group> list) {
        Log.d("Update List: ", "I entered this function");
        groupRv = findViewById(R.id.groupRV);
        groupsAdapter = new GroupsAdapter(list, this);
        groupRv.setAdapter(groupsAdapter);
        groupRv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void updateAdapter(){
        Log.d("Update Adapter: ", "I entered this function");
        groupsAdapter.notifyItemInserted(groupsAdapter.getItemCount());
    }
}
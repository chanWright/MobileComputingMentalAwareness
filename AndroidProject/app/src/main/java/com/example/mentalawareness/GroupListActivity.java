package com.example.mentalawareness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.GroupsRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupListActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, GroupListActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        //Create group request
        //createGroup();
        //Retrieve Groups


        getGroupList();
    }

    /*private void createGroup() {
        String GUID = "GUID";
        String groupName = "Hello Group!";
        String groupType = CometChatConstants.GROUP_TYPE_PUBLIC;
        String password = "";

        Group group = new Group(GUID, groupName, groupType, password);

        CometChat.createGroup(group, new CometChat.CallbackListener<Group>(){
            @Override
            public void onSuccess(Group group) {
                Log.d("Create Group : ", "Group created successfully: " + group.toString());
            }
            @Override
            public void onError(CometChatException e) {
                Log.d("Create Group : ", "Group creation failed with exception: " + e.getMessage());
            }
        });
    }*/

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
        RecyclerView groupRv = findViewById(R.id.groupRV);
        GroupsAdapter groupsAdapter = new GroupsAdapter(list, this);
        groupRv.setAdapter(groupsAdapter);
        groupRv.setLayoutManager(new LinearLayoutManager(this));

    }
}
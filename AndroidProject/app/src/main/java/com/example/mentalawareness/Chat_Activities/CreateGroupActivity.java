package com.example.mentalawareness.Chat_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.Group;
import com.example.mentalawareness.R;

public class CreateGroupActivity extends AppCompatActivity {

    String groupType = CometChatConstants.GROUP_TYPE_PUBLIC;
    String password = "";
    String GUID;
    String groupName;

    public static void start(Context context) {
        Intent starter = new Intent(context, CreateGroupActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        TextView nameET = findViewById(R.id.nameET);
        nameET.setText(CometChat.getLoggedInUser().getName());

        ImageView imv = findViewById(R.id.backBTN);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //create Group
        Button newGroupBTN = findViewById(R.id.newGroupBTN);
        newGroupBTN.setOnClickListener(view -> {
            newGroup();
        });
    }

    private void newGroup() {
        try {
            EditText groupNameET = findViewById(R.id.groupNameET);
            EditText groupIDET = findViewById(R.id.groupIDET);
            GUID = groupIDET.getText().toString();
            groupName = groupNameET.getText().toString();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Could Not Create Group",Toast.LENGTH_LONG).show();
            finish();
        }

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
        finish();
    }
}
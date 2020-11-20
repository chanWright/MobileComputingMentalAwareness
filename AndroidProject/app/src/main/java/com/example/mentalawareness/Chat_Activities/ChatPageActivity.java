package com.example.mentalawareness.Chat_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cometchat.pro.constants.CometChatConstants;
import com.cometchat.pro.core.CometChat;
import com.cometchat.pro.core.MessagesRequest;
import com.cometchat.pro.exceptions.CometChatException;
import com.cometchat.pro.models.BaseMessage;
import com.cometchat.pro.models.CustomMessage;
import com.cometchat.pro.models.MediaMessage;
import com.cometchat.pro.models.TextMessage;
import com.example.mentalawareness.ChatActivity;
import com.example.mentalawareness.R;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;
import java.util.List;

import modules.MessageWrapper;

public class ChatPageActivity extends AppCompatActivity {

    private  String groupId;
    private MessagesListAdapter<IMessage> adapter;

    public static void start(Context context, String groupID) {
        Intent starter = new Intent(context, ChatPageActivity.class);
        starter.putExtra("group_ID", groupID);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_page);

        TextView nameET = findViewById(R.id.nameET);
        nameET.setText(CometChat.getLoggedInUser().getName());

        ImageView imv = findViewById(R.id.backBTN);
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Chat page implementation

        Intent intent = getIntent();
        if(intent != null) {
            groupId = intent.getStringExtra("group_ID");
        }
        initView();
        addListener();

        fetchPreviousMessages();
    }

    private void fetchPreviousMessages() {
        int limit = 50;
        String guid =  groupId;//Updated
        MessagesRequest messagesRequest = new MessagesRequest.MessagesRequestBuilder().setGUID(guid).setLimit(limit).build();

        messagesRequest.fetchPrevious(new CometChat.CallbackListener<List<BaseMessage>>() {
            @Override
            public void onSuccess(List<BaseMessage> list) {
                List<IMessage> prevList = new ArrayList<>();
                for (BaseMessage message: list) {
                    if (message instanceof TextMessage) {
                        Log.d("Fetch Message", "Text message received successfully: " +
                                ((TextMessage) message).toString());
                        prevList.add(new MessageWrapper((TextMessage) message));
                    } else if (message instanceof MediaMessage) {
                        Log.d("Fetch Message", "Media message received successfully: " +
                                ((MediaMessage) message).toString());
                    }
                }
                adapter.addToEnd(prevList, true);

            }
            @Override
            public void onError(CometChatException e) {
                Log.d("Fetch Message", "Message fetching failed with exception: " + e.getMessage());
            }
        });

    }

    private void addListener() {
        String listenerID = "Listener_ID";
        CometChat.addMessageListener(listenerID, new CometChat.MessageListener() {
            @Override
            public void onTextMessageReceived(TextMessage textMessage) {
                Log.d("Add Text Message: ", "Text message received successfully: " + textMessage.toString());
                addMessage(textMessage);
            }
            @Override
            public void onMediaMessageReceived(MediaMessage mediaMessage) {
                Log.d("Add Text Message: ", "Media message received successfully: " + mediaMessage.toString());
            }
            @Override
            public void onCustomMessageReceived(CustomMessage customMessage) {
                Log.d("Add Text Message: ", "Custom message received successfully: " +customMessage.toString());
            }
        });
    }

    private void initView() {
        MessageInput inputV = findViewById(R.id.input);
        MessagesList messagesList = findViewById(R.id.messagesList);
        inputV.setInputListener((MessageInput.InputListener) input -> {
            //validate and send message
            //adapter.addToStart(message, true);
            sendMessage(input.toString());
            return true;
        });

        String senderId = CometChat.getLoggedInUser().getUid();
        ImageLoader imageLoader = (imageView, url, payload) -> Picasso.get().load(url).into(imageView);
         adapter = new MessagesListAdapter<>(senderId, imageLoader);
        messagesList.setAdapter(adapter);
    }

    private void sendMessage(String message) {
        TextMessage textMessage = new TextMessage(groupId, message, CometChatConstants.RECEIVER_TYPE_GROUP);

        CometChat.sendMessage(textMessage, new CometChat.CallbackListener<TextMessage>() {
            @Override
            public void onSuccess(TextMessage textMessage) {
                Log.d("Send Message", "Message sent successfully: " + textMessage.toString());
                addMessage(textMessage);
            }
            @Override
            public void onError(CometChatException e) {
                Log.d("Send Message", "Message sending failed with exception: " + e.getMessage());
            }
        });
    }

    private void addMessage(TextMessage textMessage) {
        adapter.addToStart(new MessageWrapper(textMessage), true);
    }
}
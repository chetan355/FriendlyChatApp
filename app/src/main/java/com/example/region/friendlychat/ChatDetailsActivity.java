package com.example.region.friendlychat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.example.region.friendlychat.adapter.MessagesAdapter;
import com.example.region.friendlychat.databinding.ActivityChatDetailsBinding;
import com.example.region.friendlychat.models.FriendlyMessage;
import com.example.region.friendlychat.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailsActivity extends AppCompatActivity {
    ActivityChatDetailsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");
        binding.friendName.setText(user.getUser_name());
        Picasso.get().load(user.getProfile_pic()).placeholder(R.drawable.user).into(binding.profileImage);

        final String senderId = auth.getUid();
        final String receiverId = user.getUid();
        
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        final String senderRoom = senderId + receiverId;
        final String receiverRoom = receiverId + senderId;

        final ArrayList<FriendlyMessage> messagesList = new ArrayList<>();
        MessagesAdapter adapter = new MessagesAdapter(messagesList,this);


        binding.chatrecycler.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.chatrecycler.setLayoutManager(manager);

        database.getReference().child("chats")
                                .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        messagesList.clear();
                                        for(DataSnapshot snapshot1:snapshot.getChildren()){
                                            FriendlyMessage messageModel = snapshot1.getValue(FriendlyMessage.class);
                                            assert messageModel != null;
                                            Log.d("DEBUG",messageModel.getMessage());
                                            messagesList.add(messageModel);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = binding.edtMessage.getText().toString();
                final FriendlyMessage messageModel = new FriendlyMessage(message,senderId,new Date().getTime());
                binding.edtMessage.setText("");
                database.getReference().child("chats")
                                        .child(senderRoom)
                                        .push()
                                        .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        database.getReference().child("chats")
                                                .child(receiverRoom)
                                                .push()
                                                .setValue(messageModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        });
                    }
                });
            }
        });
    }
}
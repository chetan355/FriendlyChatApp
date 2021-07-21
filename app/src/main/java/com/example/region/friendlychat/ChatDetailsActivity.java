package com.example.region.friendlychat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.region.friendlychat.databinding.ActivityChatDetailsBinding;
import com.example.region.friendlychat.databinding.FragmentFriendsBinding;
import com.example.region.friendlychat.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

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

        String senderId = auth.getUid();
        String receiver = user.getUid();
        
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChatDetailsActivity.this, FragmentFriendsBinding.inflate(getLayoutInflater()));
//                startActivity(intent);
            }
        });
    }
}
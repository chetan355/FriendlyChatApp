package com.example.region.friendlychat.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.region.friendlychat.adapter.FriendsAdapter;
import com.example.region.friendlychat.databinding.FragmentFriendsBinding;
import com.example.region.friendlychat.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsFragment extends Fragment {

    FragmentFriendsBinding binding;
    ArrayList<User> usersList = new ArrayList<>();
    FirebaseDatabase database;
    FirebaseAuth auth;
    FriendsAdapter adapter;
    ProgressDialog dialog;
    public static String CURRENT_USER = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFriendsBinding.inflate(inflater,container,false);

        adapter = new FriendsAdapter(usersList,getContext());
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(layoutManager);

        dialog = new ProgressDialog(binding.getRoot().getContext());
        dialog.setTitle("Fetching");
        dialog.setTitle("We're fetching all friends");
        dialog.show();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        database.getReference().child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    user.setUid(dataSnapshot.getKey());
                    usersList.add(user);
                    if(user.getUid().equals(auth.getUid())){
                        CURRENT_USER = user.getUser_name();
                    }
                }
                dialog.dismiss();
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return binding.getRoot();
    }
}
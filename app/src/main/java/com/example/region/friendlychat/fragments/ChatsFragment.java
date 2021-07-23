package com.example.region.friendlychat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.region.friendlychat.R;
import com.example.region.friendlychat.adapter.GroupMessageAdapter;
import com.example.region.friendlychat.databinding.FragmentChatsBinding;
import com.example.region.friendlychat.models.GroupMessage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ChatsFragment extends Fragment {
    FragmentChatsBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(getLayoutInflater());

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        binding.sendGrpBtn.setEnabled(false);

        binding.edtMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().trim().length()>0){
                    binding.sendGrpBtn.setEnabled(true);
                }else{
                    binding.sendGrpBtn.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final ArrayList<GroupMessage> msgList = new ArrayList<>();

        GroupMessageAdapter adapter = new GroupMessageAdapter(msgList,getContext());
        binding.grpChatRecycler.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.grpChatRecycler.setLayoutManager(manager);

        database.getReference().child("grpchat")
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        msgList.clear();
                                        for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                            GroupMessage msgModel = dataSnapshot.getValue(GroupMessage.class);
                                            msgList.add(msgModel);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


        binding.sendGrpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message =  binding.edtMessage.getText().toString();
                String userName = FriendsFragment.CURRENT_USER;
                binding.edtMessage.setText("");
                GroupMessage model = new GroupMessage(userName,message,auth.getUid());
                database.getReference().child("grpchat")
                                        .push()
                                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
            }
        });
        return binding.getRoot();
    }
}
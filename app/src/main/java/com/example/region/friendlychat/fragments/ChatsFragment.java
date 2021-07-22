package com.example.region.friendlychat.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.region.friendlychat.R;
import com.example.region.friendlychat.databinding.FragmentChatsBinding;


public class ChatsFragment extends Fragment {
    FragmentChatsBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
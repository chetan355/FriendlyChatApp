package com.example.region.friendlychat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.region.friendlychat.R;
import com.example.region.friendlychat.models.GroupMessage;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class GroupMessageAdapter extends RecyclerView.Adapter {
    ArrayList<GroupMessage> messagelist;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public GroupMessageAdapter(ArrayList<GroupMessage> messagelist, Context context) {
        this.messagelist = messagelist;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_grpchat_item,parent,false);
            return new GroupMessageAdapter.SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.receiver_grpchat_item,parent,false);
            Log.d("DEBUG","Inside the RECEIVER");
            return new GroupMessageAdapter.ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messagelist.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GroupMessage messageModel = messagelist.get(position);
        if(holder.getClass()== GroupMessageAdapter.SenderViewHolder.class){
            ((SenderViewHolder) holder).sender_txt.setText(messageModel.getMessage());
//            ((SenderViewHolder) holder).sender_time.setText(formatted);
        }else{
            ((ReceiverViewHolder)holder).recei_name.setText(messageModel.getuName());
            ((ReceiverViewHolder)holder).recei_txt.setText(messageModel.getMessage());
        }
    }
    @Override
    public int getItemCount() {
        return messagelist.size();
    }

    static class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView recei_txt, recei_name;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            recei_name = itemView.findViewById(R.id.receiver_name);
            recei_txt = itemView.findViewById(R.id.receiver_txt_msg);
        }
    }
    static class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView sender_txt, sender_name;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sender_name = itemView.findViewById(R.id.sender_name);
            sender_txt = itemView.findViewById(R.id.sender_txt_msg);
        }
    }
}

package com.example.region.friendlychat.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.region.friendlychat.R;
import com.example.region.friendlychat.models.FriendlyMessage;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MessagesAdapter extends RecyclerView.Adapter{
    ArrayList<FriendlyMessage> messages;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public MessagesAdapter(ArrayList<FriendlyMessage> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_chat_item,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.receiver_chat_item,parent,false);
            Log.d("DEBUG","Inside the RECEIVER");
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(messages.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FriendlyMessage message = messages.get(position);
//        Log.d("DEBUG",message.getuId());
//        Log.d("DEBUG",""+message.getMsgTime());
//        Log.d("DEBUG",message.getMessage());
        long unix = message.getMsgTime();
        Log.d("DEBUG",""+unix);
        Date date = new Date(unix*1000L);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formatted = sdf.format(date);
        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder) holder).sender_txt.setText(message.getMessage());
            ((SenderViewHolder) holder).sender_time.setText(formatted);
        }else{
            ((ReceiverViewHolder)holder).recei_time.setText(formatted);
            ((ReceiverViewHolder)holder).recei_txt.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ReceiverViewHolder extends RecyclerView.ViewHolder{
        TextView recei_txt,recei_time;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            recei_time = itemView.findViewById(R.id.receiver_txt_time);
            recei_txt = itemView.findViewById(R.id.receiver_txt_msg);
        }
    }
    static class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView sender_txt,sender_time;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            sender_time = itemView.findViewById(R.id.sender_txt_time);
            sender_txt = itemView.findViewById(R.id.sender_txt_msg);
        }
    }
}



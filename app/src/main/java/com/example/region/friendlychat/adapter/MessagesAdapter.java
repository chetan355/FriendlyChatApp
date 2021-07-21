package com.example.region.friendlychat.adapter;

import android.content.Context;
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

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {
    Context context;
    List<FriendlyMessage> msglist;

    public MessagesAdapter(Context context, List<FriendlyMessage> msglist) {
        this.context = context;
        this.msglist = msglist;
    }
    @NonNull
    @Override
    public MessagesAdapter.MessagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessagesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesAdapter.MessagesViewHolder holder, int position) {
        FriendlyMessage msg = msglist.get(position);
        boolean isPhoto = msg.getPhotoUrl() != null;
        if(isPhoto){
            holder.text.setVisibility(View.GONE);
            holder.msgImg.setVisibility(View.VISIBLE);
            Glide.with(holder.msgImg.getContext()).load(msg.getPhotoUrl()).into(holder.msgImg);
        }else{
            holder.text.setVisibility(View.VISIBLE);
            holder.msgImg.setVisibility(View.GONE);
            holder.text.setText(msg.getText());
        }
        holder.user.setText(msg.getuName());
    }

    @Override
    public int getItemCount() {
        return msglist.size();
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder{
        TextView text,user;
        ImageView msgImg;
        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_msg);
            user = itemView.findViewById(R.id.username);
            msgImg = itemView.findViewById(R.id.msg_img);
        }
    }
}



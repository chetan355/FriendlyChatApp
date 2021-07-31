package com.example.region.friendlychat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.region.friendlychat.ChatDetailsActivity;
import com.example.region.friendlychat.MainActivity;
import com.example.region.friendlychat.R;
import com.example.region.friendlychat.models.User;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>
{

    ArrayList<User> users;
    Context context;

    public FriendsAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friends_item,parent,false);
        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        User user = users.get(position);
        Picasso.get().load(user.getProfile_pic()).placeholder(R.drawable.user).into(holder.uProfile);
        holder.uName.setText(user.getUser_name());
        holder.user_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatDetailsActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class FriendsViewHolder extends RecyclerView.ViewHolder{
        TextView uName;
        ImageView uProfile;
        LinearLayout user_item;
        public FriendsViewHolder(@NonNull View itemView) {
            super(itemView);
            uName = itemView.findViewById(R.id.txtUsername);
            uProfile = itemView.findViewById(R.id.profile_image);
            user_item = itemView.findViewById(R.id.user_item);
        }
    }
}

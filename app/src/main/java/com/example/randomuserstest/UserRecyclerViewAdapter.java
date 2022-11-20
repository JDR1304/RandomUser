package com.example.randomuserstest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.randomuserstest.model.User;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder> {

    private List<User> users;

    public UserRecyclerViewAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserRecyclerViewAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewAdapter.UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.firstName.setText(user.getName().getFirst());
        holder.lastName.setText(user.getName().getLast());
        holder.email.setText(user.getEmail());
        Glide.with(holder.userImage.getContext())
                .load(user.getPicture().getThumbnail())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView userImage;
        private TextView firstName;
        private TextView lastName;
        private TextView email;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            firstName = itemView.findViewById(R.id.user_first_name);
            lastName = itemView.findViewById(R.id.user_last_name);
            email = itemView.findViewById(R.id.user_email);
        }
    }
}

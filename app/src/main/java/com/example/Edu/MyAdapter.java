package com.example.Edu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(User user);
    }
    Context context;
    ArrayList<User> userArrayList;
    private OnItemClickListener listener;

    public MyAdapter(Context context, ArrayList<User> userArrayList, OnItemClickListener listener) {
        this.context = context;
        this.userArrayList = userArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);


        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onItemClick(user);
            }
        });

        holder.Date.setText(user.date);
        holder.Title.setText(user.titleAuthor);
        holder.Place.setText(user.place);

        if (user.imageUrl != null && !user.imageUrl.isEmpty()) {
            Glide.with(context).load(user.imageUrl).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.edunkar); // Replace with your default image
        }


        if (user.getLiked().equals("yes")) { // Check for "yes"
            holder.likeButton.setImageResource(R.drawable.heartred);
        } else {
            holder.likeButton.setImageResource(R.drawable.love);
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isLiked = user.getLiked();
                if (isLiked.equals("no")) {
                    isLiked = "yes";
                } else {
                    isLiked = "no";
                }
                user.setLiked(isLiked);
                updateLikeStatusInFirestore(user);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageButton likeButton;
        TextView Date, Place, Title;

        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Date= itemView.findViewById(R.id.datee);
            Place= itemView.findViewById(R.id.placee);
            Title= itemView.findViewById(R.id.titlee);
            likeButton = itemView.findViewById(R.id.likeButton);
            imageView = itemView.findViewById(R.id.imageView7);
        }
    }

    public void filterList(ArrayList<User> filteredList) {
        userArrayList = filteredList;
        notifyDataSetChanged();
    }

    private void updateLikeStatusInFirestore(User user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("user").document(user.getId())
                .update("liked", user.getLiked()) // Now pass the string value
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Firestore", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Firestore", "Error updating document", e);
                    }
                });
    }
}

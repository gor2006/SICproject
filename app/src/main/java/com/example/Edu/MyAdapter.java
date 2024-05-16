package com.example.Edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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



    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Date, Place, Title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Date= itemView.findViewById(R.id.datee);
            Place= itemView.findViewById(R.id.placee);
            Title= itemView.findViewById(R.id.titlee);
        }
    }
}

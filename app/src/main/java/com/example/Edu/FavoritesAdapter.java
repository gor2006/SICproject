//package com.example.Edu;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {
//    private final Context context;
//    private final List<User> favoritePosts;
//
//    public FavoritesAdapter(Context context, Set<User> favoritePostsSet) {
//        this.context = context;
//        this.favoritePosts = new ArrayList<>(favoritePostsSet);
//    }
//
//    @NonNull
//    @Override
//    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
//        return new FavoriteViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
//        User user = favoritePosts.get(position);
//        holder.dateTextView.setText(user.getDate());
//        holder.titleTextView.setText(user.getTitleAuthor());
//        holder.placeTextView.setText(user.getPlace());
//        holder.bind(user);
//    }
//
//    @Override
//    public int getItemCount() {
//        return favoritePosts.size();
//    }
//
//    class FavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView dateTextView, placeTextView, titleTextView;
//        User user;
//
//        FavoriteViewHolder(View itemView) {
//            super(itemView);
//            dateTextView = itemView.findViewById(R.id.datee);
//            placeTextView = itemView.findViewById(R.id.placee);
//            titleTextView = itemView.findViewById(R.id.titlee);
//            itemView.setOnClickListener(this);
//        }
//
//        void bind(User user) {
//            this.user = user;
//        }
//
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(context, DescriptionActivity.class);
//            intent.putExtra("user", user);
//            context.startActivity(intent);
//        }
//    }
//}

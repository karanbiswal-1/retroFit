package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.newssHolder> {
    private Context context;
    private ArrayList<Article> articles;

   public newsAdapter(Context context,ArrayList<Article> articles){
       this.context = context;
      this.articles = articles;
   }
    @NonNull
    @Override
    public newssHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new newssHolder(LayoutInflater.from(context).inflate(R.layout.cell_news,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull newssHolder holder, int position) {
        Article item  = articles.get(position);
        holder.mTvtitle.setText(item.title);
        holder.mTvdescription.setText(item.description);
        Glide.with(context).load(item.urlToImage).into(holder.mIvnewsimage);
      //  Picasso.get().load(item.urlToImage).into(holder.mIvnewsimage);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class newssHolder extends RecyclerView.ViewHolder{
        private ImageView mIvnewsimage;
        private TextView mTvtitle;
        private TextView mTvdescription;

        public newssHolder(@NonNull View itemView) {
            super(itemView);
            mIvnewsimage = itemView.findViewById(R.id.iv_newsArticle);
            mTvtitle = itemView.findViewById(R.id.tv_titleArticle);
            mTvdescription = itemView.findViewById(R.id.tv_description);
        }
    }
}

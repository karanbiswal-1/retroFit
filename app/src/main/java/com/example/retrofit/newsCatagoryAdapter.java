package com.example.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

public class newsCatagoryAdapter extends RecyclerView.Adapter<newsCatagoryAdapter.newsCatagoryHolder>{

    private Context context;
    private String[] newsCatagories;
    private newsCatagoryClickListener listener;
    private int selectedPosition =0;

    public  void  setListener(newsCatagoryClickListener listener){
        this.listener = listener;
    }

    public newsCatagoryAdapter(Context context){
        this.context = context;
        newsCatagories = context.getResources().getStringArray(R.array.news_catagory);
    }

    @NonNull
    @Override
    public newsCatagoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new newsCatagoryHolder(LayoutInflater.from(context).inflate(R.layout.cell_news_catagory,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull newsCatagoryHolder holder, int position) {
        String catagoryName = newsCatagories[position];
        holder.mTvnewsCatagory.setText(catagoryName);

        if(selectedPosition == position){
            holder.mRIRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_news_selected,null));
            holder.mTvnewsCatagory.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.colorWhite,null));
        }else{
            holder.mRIRoot.setBackground(ResourcesCompat.getDrawable(context.getResources(),R.drawable.bg_news_catagory,null));
            holder.mTvnewsCatagory.setTextColor(ResourcesCompat.getColor(context.getResources(),R.color.colorBlack,null));
        }

        holder.mRIRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onNewsCatagoryClicked(catagoryName);
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsCatagories.length;
    }

    class newsCatagoryHolder extends RecyclerView.ViewHolder{
         private RelativeLayout mRIRoot;
         private TextView mTvnewsCatagory;

        public newsCatagoryHolder(@NonNull View itemView) {
            super(itemView);
            mRIRoot = itemView.findViewById(R.id.rl_root);
            mTvnewsCatagory = itemView.findViewById(R.id.tv_news_catagory);
        }
    }

    public interface newsCatagoryClickListener{
        void onNewsCatagoryClicked(String catagoryName);
    }

}

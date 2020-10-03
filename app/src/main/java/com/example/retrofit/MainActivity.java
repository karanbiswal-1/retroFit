package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements newsCatagoryAdapter.newsCatagoryClickListener{
    private RecyclerView mRcnews;
    private ImageView mIvSingleImage;
    private String catagoryName = "business";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRcnewsCatagory = findViewById(R.id.rc_newsCatagory);
        mRcnewsCatagory.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.HORIZONTAL,false));

        mRcnews = findViewById(R.id.rc_news);
        mRcnews.setLayoutManager(new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL,false));

        newsCatagoryAdapter newsCatagoryAdapter = new newsCatagoryAdapter(MainActivity.this);
        newsCatagoryAdapter.setListener(this);
        mRcnewsCatagory.setAdapter(newsCatagoryAdapter);
          getSourceNews();
//        getNews();
    }
//    public void getNews(){
//        ProgressDialog pg = new ProgressDialog(MainActivity.this);
//        pg.setTitle("getting your news");
//        pg.show();
//
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//        HashMap<String,Object> queries = new HashMap<>();
//        queries.put("apiKey","3cd1c46c52a349cbad5653a022e90e5b");
//        queries.put("sources", "google-news");
//
//        Call<String> getNews = apiInterface.getAllNews(queries);
//
//        getNews.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                pg.hide();
//                String responseValue = response.body();
//                try{
//                    JSONObject responseObject = new JSONObject(responseValue);
//                    Result resultValue = Result.parseResultResponse(responseObject);
//                    ArrayList<Article> articles = resultValue.articles;
//                  //  Glide.with(MainActivity.this).load(articles.get(5).urlToImage).into(mIvnews);
//                    newsAdapter adapter = new newsAdapter(MainActivity.this,articles);
//                    mRcnews.setAdapter(adapter);
//
//                }catch (JSONException e){
//                    e.printStackTrace();
//                }
//                Toast.makeText(MainActivity.this,"success",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                pg.hide();
//                Toast.makeText(MainActivity.this,"failure",Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private void getSourceNews(){
        HashMap<String,Object> queries = new HashMap<>();
        queries.put("apikey","3cd1c46c52a349cbad5653a022e90e5b");
        queries.put("category",catagoryName);

        ProgressDialog pg0 = new ProgressDialog(MainActivity.this);
        pg0.setTitle("getting your news");
        pg0.show();

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> getSourceNews = apiInterface.getAllSources(queries);
        getSourceNews.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                pg0.hide();
                Toast.makeText(MainActivity.this,"succes",Toast.LENGTH_LONG).show();
                String responseValue = response.body();

                try{
                    JSONObject responseObject = new JSONObject(responseValue);
                    Result resultValue = Result.parseResultResponse(responseObject);
                    ArrayList<Article> articles = resultValue.articles;

                    newsAdapter newsAdapter = new newsAdapter(MainActivity.this,articles);
                    mRcnews.setAdapter(newsAdapter);
                }catch (JSONException E){
                    E.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                pg0.hide();
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onNewsCatagoryClicked(String catagoryName) {
        this.catagoryName = catagoryName;
        getSourceNews();
    }
}


package com.example.retrofit;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Result {
    public String status;
    public  int totalResults;
    public ArrayList<Article> articles;

    public static Result parseResultResponse(JSONObject jsonObject){
        Result result = new Result();
        result.status = jsonObject.optString("status");
        result.totalResults = jsonObject.optInt("totalResults");

        result.articles = new ArrayList<>();
        JSONArray articleArray = jsonObject.optJSONArray("articles");

        if(articleArray.length() > 0){
            for(int i =0;i < articleArray.length();i++){
                Article article = Article.parseArticleResponse(articleArray.optJSONObject(i));
                result.articles.add(article);
            }
        }

        return result;
    }
}

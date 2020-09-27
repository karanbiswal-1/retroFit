package com.example.retrofit;

import org.json.JSONObject;

public class Article {
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;
    public Source source;

    public static Article parseArticleResponse(JSONObject jsonObject){
        Article article = new Article();
        article.author = jsonObject.optString("author");
        article.title = jsonObject.optString("title");
        article.description = jsonObject.optString("description");
        article.url = jsonObject.optString("url");
        article.urlToImage = jsonObject.optString("urlToImage");
        article.publishedAt = jsonObject.optString("publishedAt");
        article.content = jsonObject.optString("content");
        article.source = Source.parseSourceResponce(jsonObject.optJSONObject("source"));

        return  article;
    }
}

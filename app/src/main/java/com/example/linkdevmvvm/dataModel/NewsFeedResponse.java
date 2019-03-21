package com.example.linkdevmvvm.dataModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by antonio on 1/16/19.
 */

public class NewsFeedResponse implements Parcelable {

    public final static Parcelable.Creator<NewsFeedResponse> CREATOR = new Creator<NewsFeedResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public NewsFeedResponse createFromParcel(Parcel in) {
            return new NewsFeedResponse(in);
        }

        public NewsFeedResponse[] newArray(int size) {
            return (new NewsFeedResponse[size]);
        }

    };
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("sortBy")
    @Expose
    private String sortBy;
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;

    protected NewsFeedResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.sortBy = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.articles, (Article.class.getClassLoader()));
    }

    public NewsFeedResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(source);
        dest.writeValue(sortBy);
        dest.writeList(articles);
    }

    public int describeContents() {
        return 0;
    }

}
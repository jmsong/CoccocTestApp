package com.coccoc.coccoctestapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by tungtm on 1/24/17.
 */

public class Movie {
    private String id;
    private String sku;

    @SerializedName("category_id")
    private String categoryId;
    private String category;
    private String name;
    private String thumbnail;

    @SerializedName("movie_trailer")
    private String movieTrailer;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("movie_endtime")
    private String movieEndtime;

    @SerializedName("rating_icon")
    private String ratingIcon;
    private String codes;

    @SerializedName("is_booking")
    private boolean isBooking;

    @SerializedName("is_new")
    private boolean isNew;
    private int position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMovieEndtime() {
        return movieEndtime;
    }

    public void setMovieEndtime(String movieEndtime) {
        this.movieEndtime = movieEndtime;
    }

    public String getRatingIcon() {
        return ratingIcon;
    }

    public void setRatingIcon(String ratingIcon) {
        this.ratingIcon = ratingIcon;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public boolean isBooking() {
        return isBooking;
    }

    public void setBooking(boolean booking) {
        isBooking = booking;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

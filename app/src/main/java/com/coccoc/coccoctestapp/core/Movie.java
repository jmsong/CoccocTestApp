package com.coccoc.coccoctestapp.core;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tungtm on 1/24/17.
 */

public class Movie extends RealmObject {
    @PrimaryKey
    private String id;
    private String sku;

    @SerializedName("category_id")
    private String categoryId;
    private String category;
    private String name;
    private String thumbnail;
    private String url;

    @SerializedName("full_description")
    private String fullDescription;

    private String genre;

    @SerializedName("rating_code")
    private String ratingCode;

    @SerializedName("review_percent")
    private Integer reviewPercent;

    @SerializedName("movie_trailer")
    private String movieTrailer;

    @SerializedName("movie_director")
    private String movieDirector;

    @SerializedName("movie_language")
    private String movieLanguage;

    @SerializedName("movie_actress")
    private String movieActress;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("movie_endtime")
    private String movieEndtime;

    @SerializedName("rating_icon")
    private String ratingIcon;
    private String codes;

    @SerializedName("is_booking")
    private Boolean isBooking;

    @SerializedName("is_new")
    private Boolean isNew;
    private Integer position;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public Integer getReviewPercent() {
        return reviewPercent;
    }

    public void setReviewPercent(Integer reviewPercent) {
        this.reviewPercent = reviewPercent;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieActress() {
        return movieActress;
    }

    public void setMovieActress(String movieActress) {
        this.movieActress = movieActress;
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

    public Boolean isBooking() {
        return isBooking;
    }

    public void setBooking(Boolean booking) {
        isBooking = booking;
    }

    public Boolean isNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}

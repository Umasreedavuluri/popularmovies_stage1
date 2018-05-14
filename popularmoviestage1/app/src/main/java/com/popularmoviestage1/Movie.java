package com.popularmoviestage1;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Umasree D on 1/5/2018.
 * Pojo for Movie
 */
public class Movie implements Parcelable {

   //example from vogella.com

    public String display_name;
    public float rating;
    public Double popularity;
    public String released_date;
    public String overview;
    public String poster_url;
    public int id;


    public static final Creator<Movie> CREATOR = new Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };


    private Movie(Parcel in){
        this.id = in.readInt();
        this.display_name = in.readString();
        popularity = in.readDouble();
        rating = in.readFloat();
        released_date = in.readString();
        overview = in.readString();
        poster_url = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.display_name);
        dest.writeDouble(popularity);
        dest.writeFloat(rating);
        dest.writeString(released_date);
        dest.writeString(overview);
        dest.writeString(poster_url);

    }

    @Override
    public int describeContents() {
        return 0;
    }
    public static Creator getCREATOR() {
        return CREATOR;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getReleased_date() {
        return released_date;
    }

    public void setReleased_date(String released_date) {
        this.released_date = released_date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




       public Movie(){
    }
}
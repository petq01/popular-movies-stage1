package com.android.popmoviessecond.sqllite;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Petya Marinova on 5/16/2018.
 */
public class FavoriteResult implements Parcelable {

    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    public FavoriteResult(String posterPath, String overview, String releaseDate, int id, String title, double voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.originalTitle = title;
        this.voteAverage = voteAverage;
    }

    public static FavoriteResult buildResult(Cursor cursor) {
        return new FavoriteResult(
                cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_POSTER_PATH)),
                cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_DATE)),
                Integer.valueOf(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_MOVIE_ID))),
                cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_TITLE)),
//                cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_BACKDROP_PATH)),
//                Double.valueOf(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_POPULARITY))),
                Double.valueOf(cursor.getString(cursor.getColumnIndex(MoviesContract.FavoriteEntry.COLUMN_RATING)))
        );
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * @param posterPath The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }


    /**
     * @return The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @param overview The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }


    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * @param originalTitle The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * @return The voteAverage
     */
    public double getVoteAverage() {
        return voteAverage;
    }

    /**
     * @param voteAverage The vote_average
     */
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    protected FavoriteResult(Parcel in) {
        posterPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        id = in.readInt();
        originalTitle = in.readString();
        voteAverage = in.readDouble();
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeInt(id);
        dest.writeString(originalTitle);
        dest.writeDouble(voteAverage);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<FavoriteResult> CREATOR = new Parcelable.Creator<FavoriteResult>() {
        @Override
        public FavoriteResult createFromParcel(Parcel in) {
            return new FavoriteResult(in);
        }

        @Override
        public FavoriteResult[] newArray(int size) {
            return new FavoriteResult[size];
        }
    };
}
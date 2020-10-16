package com.sega.gistexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.Map;

public class Gist implements Parcelable, Comparable<Gist> {

    private Owner owner;
    private Map<String, File> files;
    private String description;
    private Date created_at;
    private int comments;

    public Gist() {
    }

    protected Gist(Parcel in) {
        description = in.readString();
        comments = in.readInt();
    }

    public static final Creator<Gist> CREATOR = new Creator<Gist>() {
        @Override
        public Gist createFromParcel(Parcel in) {
            return new Gist(in);
        }

        @Override
        public Gist[] newArray(int size) {
            return new Gist[size];
        }
    };

    public Owner getOwner() {
        return owner;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreated() {
        return created_at;
    }

    public int getComments() {
        return comments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeInt(comments);
    }

    @Override
    public int compareTo(Gist gist) {
        return this.getCreated().compareTo(gist.getCreated());
    }
}

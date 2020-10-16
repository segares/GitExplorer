package com.sega.gistexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Owner implements Parcelable{

    private String login;
    private String avatar_url;

    protected Owner(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
    }

    public static final Creator<Owner> CREATOR = new Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(login);
        parcel.writeString(avatar_url);
    }
}

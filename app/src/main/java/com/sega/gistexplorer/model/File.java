package com.sega.gistexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class File implements Parcelable {

    private String fileName;
    private String type;
    private String language;
    private String raw_url;
    private int size;

    public File() {}


    protected File(Parcel in) {
        fileName = in.readString();
        type = in.readString();
        language = in.readString();
        raw_url = in.readString();
        size = in.readInt();
    }

    public static final Creator<File> CREATOR = new Creator<File>() {
        @Override
        public File createFromParcel(Parcel in) {
            return new File(in);
        }

        @Override
        public File[] newArray(int size) {
            return new File[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(fileName);
        parcel.writeString(type);
        parcel.writeString(language);
        parcel.writeString(raw_url);
        parcel.writeInt(size);
    }
}

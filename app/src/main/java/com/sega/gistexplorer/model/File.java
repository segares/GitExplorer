package com.sega.gistexplorer.model;

import android.os.Parcel;
import android.os.Parcelable;

public class File implements Parcelable {

    private String filename;
    private String type;
    private String language;
    private String raw_url;
    private int size;

    public File() {}

    protected File(Parcel in) {
        filename = in.readString();
        type = in.readString();
        language = in.readString();
        raw_url = in.readString();
        size = in.readInt();
    }

    public String getFilename() {
        return filename;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public String getRaw_url() {
        return raw_url;
    }

    public int getSize() {
        return size;
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
        parcel.writeString(filename);
        parcel.writeString(type);
        parcel.writeString(language);
        parcel.writeString(raw_url);
        parcel.writeInt(size);
    }
}

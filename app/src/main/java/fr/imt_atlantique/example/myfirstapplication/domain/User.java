package fr.imt_atlantique.example.myfirstapplication.domain;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class User implements Parcelable {
    private String lastName;
    private String firstName;
    private String birthday;
    private String county;
    private String city;
    private String email;
    private String phones;

    public User(String lastName, String firstName, String birthday, String county, String city, String email, String phones) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.county = county;
        this.city = city;
        this.email = email;
        this.phones = phones;
    }


    protected User(Parcel in) {
        lastName = in.readString();
        firstName = in.readString();
        birthday = in.readString();
        county = in.readString();
        city = in.readString();
        email = in.readString();
        phones = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(birthday);
        dest.writeString(county);
        dest.writeString(city);
        dest.writeString(email);
        dest.writeString(phones);
    }
}

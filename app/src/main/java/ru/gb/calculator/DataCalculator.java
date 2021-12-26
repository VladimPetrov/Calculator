package ru.gb.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class DataCalculator implements Parcelable {
    private String numberOnScreen;

    public DataCalculator() {
        numberOnScreen ="";
    }
    public DataCalculator(Parcel in) {
        numberOnScreen = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DataCalculator> CREATOR = new Creator<DataCalculator>() {
        @Override
        public DataCalculator createFromParcel(Parcel in) {
            return new DataCalculator(in);
        }

        @Override
        public DataCalculator[] newArray(int size) {
            return new DataCalculator[size];
        }
    };

    public String getNumberOnScreen() {
        return numberOnScreen;
    }

    public void setNumberOnScreen(String numberOnScreen) {
        this.numberOnScreen = numberOnScreen;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numberOnScreen);
    }

}

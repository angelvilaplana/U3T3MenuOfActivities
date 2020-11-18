package dam.android.angelvilaplana.u3t3menuofactivities.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.Year;

/**
 *  Ex2 - Add a new class
 */
public class Item implements Parcelable {

    private int idImage;

    private String version;

    private String versionName;

    private Year launchYear;

    private int apiNumber;

    private String urlWikipedia;

    public Item(int idImage, String version, String versionName, Year launchYear, int apiNumber, String urlWikipedia) {
        this.idImage = idImage;
        this.version = version;
        this.versionName = versionName;
        this.launchYear = launchYear;
        this.apiNumber = apiNumber;
        this.urlWikipedia = urlWikipedia;
    }

    protected Item(Parcel in) {
        idImage = in.readInt();
        version = in.readString();
        versionName = in.readString();
        launchYear = (Year) in.readSerializable();
        apiNumber = in.readInt();
        urlWikipedia = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getIdImage() {
        return idImage;
    }

    public String getVersion() {
        return version;
    }

    public String getVersionName() {
        return versionName;
    }

    public Year getLaunchYear() {
        return launchYear;
    }

    public int getApiNumber() {
        return apiNumber;
    }

    public String getUrlWikipedia() {
        return urlWikipedia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idImage);
        dest.writeString(version);
        dest.writeString(versionName);
        dest.writeSerializable(launchYear);
        dest.writeInt(apiNumber);
        dest.writeString(urlWikipedia);
    }

}

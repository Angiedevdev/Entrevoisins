package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable{

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /**Statut favoris*/
    private boolean favorite;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param favorite;
     */
    public Neighbour(Integer id, String name, String avatarUrl, Boolean favorite) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.favorite = favorite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public  boolean getFavorite(){
        return favorite;
    }

    public void setFavorite(boolean favori){
        this.favorite = favori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Facilitateur de transmission de données entre les deux activités.
     */
    public static final Parcelable.Creator<Neighbour> CREATOR = new Parcelable.Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel source) {
            return new Neighbour(source);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }

    };

    private Neighbour (Parcel source) {
        id = source.readInt();
        name = source.readString();
        avatarUrl = source.readString();
        favorite = source.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(avatarUrl);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }
}

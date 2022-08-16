package com.astix.rajtraderssfasmasales.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TblSubordinateListMaster implements Parcelable {


    public String getPersonNodeID() {
        return PersonNodeID;
    }

    public void setPersonNodeID(String personNodeID) {
        PersonNodeID = personNodeID;
    }

    public String getPersonName() {
        return PersonName;
    }

    public void setPersonName(String personName) {
        PersonName = personName;
    }

    public String getCoverageArea() {
        return CoverageArea;
    }

    public void setCoverageArea(String coverageArea) {
        CoverageArea = coverageArea;
    }

    public int getFlgPersonType() {
        return flgPersonType;
    }

    public void setFlgPersonType(int flgPersonType) {
        this.flgPersonType = flgPersonType;
    }

    public int getParentPersonID() {
        return ParentPersonID;
    }

    public void setParentPersonID(int parentPersonID) {
        ParentPersonID = parentPersonID;
    }

    public int getParentPersonType() {
        return ParentPersonType;
    }

    public void setParentPersonType(int parentPersonType) {
        ParentPersonType = parentPersonType;
    }

    public int getFlgMarketVisitStarted() {
        return flgMarketVisitStarted;
    }

    public void setFlgMarketVisitStarted(int flgMarketVisitStarted) {
        this.flgMarketVisitStarted = flgMarketVisitStarted;
    }

    public int getCoverageAreaNodeID() {
        return CoverageAreaNodeID;
    }

    public void setCoverageAreaNodeID(int coverageAreaNodeID) {
        CoverageAreaNodeID = coverageAreaNodeID;
    }

    public int getCoverageAreaNodeType() {
        return CoverageAreaNodeType;
    }

    public void setCoverageAreaNodeType(int coverageAreaNodeType) {
        CoverageAreaNodeType = coverageAreaNodeType;
    }

    String PersonNodeID = "0";
    String PersonName = "";
    String CoverageArea;

    int flgPersonType;
    int ParentPersonID;
    int ParentPersonType;
    int flgMarketVisitStarted;
    int CoverageAreaNodeID;
    int CoverageAreaNodeType;



    protected TblSubordinateListMaster(Parcel in) {
        PersonNodeID = in.readString();
        PersonName = in.readString();
        CoverageArea = in.readString();
        flgPersonType = in.readInt();
        ParentPersonID = in.readInt();
        ParentPersonType = in.readInt();
        flgMarketVisitStarted = in.readInt();
        CoverageAreaNodeID = in.readInt();
        CoverageAreaNodeType = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(PersonNodeID);
        dest.writeString(PersonName);
        dest.writeString(CoverageArea);
        dest.writeInt(flgPersonType);
        dest.writeInt(ParentPersonID);
        dest.writeInt(ParentPersonType);
        dest.writeInt(flgMarketVisitStarted);
        dest.writeInt(CoverageAreaNodeID);
        dest.writeInt(CoverageAreaNodeType);
    }

    @SuppressWarnings("unused")
    public static final Creator<TblSubordinateListMaster> CREATOR = new Creator<TblSubordinateListMaster>() {
        @Override
        public TblSubordinateListMaster createFromParcel(Parcel in) {
            return new TblSubordinateListMaster(in);
        }

        @Override
        public TblSubordinateListMaster[] newArray(int size) {
            return new TblSubordinateListMaster[size];
        }
    };
}
package com.astix.rajtraderssfasmasales.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TblRouteListMaster implements Serializable {
    @SerializedName("FlgTodayRoute")
    int FlgTodayRoute;

    @SerializedName("flgApproved")
    @Expose
    private int flgApproved;

    @SerializedName("DBNodeID")
    @Expose
    private int DBNodeID;


    @SerializedName("FlgStoreValidation")
    private int FlgStoreValidation;
    @SerializedName("OptionID")
    private int OptionID;

    @SerializedName("Active")
    private int Active;
    @SerializedName("CoverageAreaNodeID")
    private int CoverageAreaNodeID;
    @SerializedName("CoverageAreaNodeType")
    private int CoverageAreaNodeType;
    @SerializedName("PersonNodeID")
    private int PersonNodeID;
    @SerializedName("PersonNodetype")
    private int PersonNodetype;
    @SerializedName("Route")
    private String Route;
    @SerializedName("RouteNodeId")
    private int RouteNodeId;
    @SerializedName("Routenodetype")
    private int Routenodetype;

    @SerializedName("CoverageAreaName")
    private String CoverageAreaName;

    public int getFlgApproved() {
        return flgApproved;
    }

    public void setFlgApproved(int flgApproved) {
        this.flgApproved = flgApproved;
    }

    public int getDBNodeID() {
        return DBNodeID;
    }

    public void setDBNodeID(int DBNodeID) {
        this.DBNodeID = DBNodeID;
    }

    public int getFlgStoreValidation() {
        return FlgStoreValidation;
    }

    public void setFlgStoreValidation(int mFlgStoreValidation) {
        this.FlgStoreValidation = mFlgStoreValidation;
    }

    public int getOptionID() {
        return OptionID;
    }

    public void setOptionID(int mOptionID) {
        this.OptionID = mOptionID;
    }


    public int getFlgTodayRoute() {
        return FlgTodayRoute;
    }

    public void setFlgTodayRoute(int mFlgTodayRoute) {
        this.FlgTodayRoute = mFlgTodayRoute;
    }




    public String getCoverageAreaName() {
        return CoverageAreaName;
    }

    public void setCoverageAreaName(String coverageAreaName) {
        CoverageAreaName = coverageAreaName;
    }



    public int getActive() {
        return Active;
    }

    public void setActive(int active) {
        Active = active;
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

    public int getPersonNodeID() {
        return PersonNodeID;
    }

    public void setPersonNodeID(int personNodeID) {
        PersonNodeID = personNodeID;
    }

    public int getPersonNodetype() {
        return PersonNodetype;
    }

    public void setPersonNodetype(int personNodetype) {
        PersonNodetype = personNodetype;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public int getRouteNodeId() {
        return RouteNodeId;
    }

    public void setRouteNodeId(int routeNodeId) {
        RouteNodeId = routeNodeId;
    }

    public int getRoutenodetype() {
        return Routenodetype;
    }

    public void setRoutenodetype(int routenodetype) {
        Routenodetype = routenodetype;
    }

} /*{

    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("RouteType")
    @Expose
    private int routeType;
    @SerializedName("Descr")
    @Expose
    private String descr;
    @SerializedName("Active")
    @Expose
    private int active;
    @SerializedName("CoverageAreaNodeID")
    @Expose
    private int CoverageAreaNodeID;
    @SerializedName("CoverageAreaNodeType")
    @Expose
    private int CoverageAreaNodeType;

    public int getCoverageAreaNodeType() {
        return CoverageAreaNodeType;
    }

    public void setCoverageAreaNodeType(int coverageAreaNodeType) {
        CoverageAreaNodeType = coverageAreaNodeType;
    }

    public int getCoverageAreaNodeID() {
        return CoverageAreaNodeID;
    }

    public void setCoverageAreaNodeID(int coverageAreaNodeID) {
        CoverageAreaNodeID = coverageAreaNodeID;
    }

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public int getRouteType() {
        return routeType;
    }

    public void setRouteType(int routeType) {
        this.routeType = routeType;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

}*/

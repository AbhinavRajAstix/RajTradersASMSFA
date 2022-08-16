
package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubOrdinateModel {

    @SerializedName("tblSubordinateList")
    @Expose
    private List<TblSubordinateList> tblSubordinateList = null;



    public void setTblSubordinateList(List<TblSubordinateList> tblSubordinateList) {
        this.tblSubordinateList = tblSubordinateList;
    }

    public List<TblSubordinateList> getTblSubordinateList() {
        return tblSubordinateList;
    }
}

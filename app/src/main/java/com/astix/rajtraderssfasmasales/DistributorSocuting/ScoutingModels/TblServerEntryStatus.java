
package com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TblServerEntryStatus {

    @SerializedName("flgStatus")
    @Expose
    private int flgStatus;

    public int getFlgStatus() {
        return flgStatus;
    }

    public void setFlgStatus(int flgStatus) {
        this.flgStatus = flgStatus;
    }
}

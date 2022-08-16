
package com.astix.rajtraderssfasmasales.reports.models;

import com.google.gson.annotations.SerializedName;

public class TblRptDSMSPersonStoreSummary {

    @SerializedName("Description")
    private String Description;
    @SerializedName("Planned")
    private String Planned;
    @SerializedName("In Field_MTD")
    private String InFeildMTD;
    @SerializedName("In Field_Yesterday")
    private String InFeildYesterDay;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPlanned() {
        return Planned;
    }

    public void setPlanned(String planned) {
        Planned = planned;
    }

    public String getInFeildMTD() {
        return InFeildMTD;
    }

    public void setInFeildMTD(String inFeildMTD) {
        InFeildMTD = inFeildMTD;
    }

    public String getInFeildYesterDay() {
        return InFeildYesterDay;
    }

    public void setInFeildYesterDay(String inFeildYesterDay) {
        InFeildYesterDay = inFeildYesterDay;
    }
}


package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblSummaryReportDetails {

    @SerializedName("Route")
    @Expose
    private String Route;

    @SerializedName("Store")
    @Expose
    private String Store;


    public String getRoute() {
        return Route;
    }

    public void setRoute(String route) {
        Route = route;
    }

    public String getStore() {
        return Store;
    }

    public void setStore(String store) {
        Store = store;
    }
}

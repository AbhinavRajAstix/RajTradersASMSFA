package com.astix.rajtraderssfasmasales;

import android.widget.EditText;

import com.astix.rajtraderssfasmasales.model.TblSchemePerProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProductFilledDataModelGateEntry {

    //hmapCtgryPrdctDetail= key=prdctId,val=ProductFreeQty
    private LinkedHashMap<String, String> hmapPersonDistributionTarget = new LinkedHashMap<String, String>();

    LinkedHashMap<String, String> hmapPersonSalesTarget=new  LinkedHashMap<String, String>();


    private LinkedHashMap<String, String> hmapPersonFocusProductDistributionTarget = new LinkedHashMap<String, String>();
    private LinkedHashMap<String, String> hmapPersonFocusProductSalesTarget = new LinkedHashMap<String, String>();


    public LinkedHashMap<String, String> getHmapPersonFocusProductDistributionTarget() {
        return hmapPersonFocusProductDistributionTarget;
    }

    public void setHmapPersonFocusProductDistributionTarget(LinkedHashMap<String, String> hmapPersonFocusProductDistributionTarget) {
        this.hmapPersonFocusProductDistributionTarget = hmapPersonFocusProductDistributionTarget;
    }

    public LinkedHashMap<String, String> getHmapPersonFocusProductSalesTarget() {
        return hmapPersonFocusProductSalesTarget;
    }

    public void setHmapPersonFocusProductSalesTarget(LinkedHashMap<String, String> hmapPersonFocusProductSalesTarget) {
        this.hmapPersonFocusProductSalesTarget = hmapPersonFocusProductSalesTarget;
    }

    public LinkedHashMap<String, String> getHmapPersonDistributionTarget() {
        return hmapPersonDistributionTarget;
    }

    public void setHmapPersonDistributionTarget(LinkedHashMap<String, String> hmapPersonDistributionTarget) {
        this.hmapPersonDistributionTarget = hmapPersonDistributionTarget;
    }


    public void setPersonDistributionTarget(String personID, String tarvetVal) {
        hmapPersonDistributionTarget.put(personID, tarvetVal);
    }

    public void removePersonDistributionTarget(String personID) {
        if ((hmapPersonDistributionTarget != null)) {
            hmapPersonDistributionTarget.remove(personID);
        }
    }

    public void setPersonSalesTarget(String personID, String salesVal) {
        hmapPersonSalesTarget.put(personID, salesVal);
    }

    public void removePersonSalesTarget(String personID) {
        if ((hmapPersonSalesTarget != null)) {
            hmapPersonSalesTarget.remove(personID);
        }
    }

    public LinkedHashMap<String, String> getHmapPersonSalesTarget() {
        return hmapPersonSalesTarget;
    }

    public void setHmapPersonSalesTarget(LinkedHashMap<String, String> hmapPersonSalesTarget) {
        this.hmapPersonSalesTarget = hmapPersonSalesTarget;
    }





    public void setFocusedProductDistributionTarget(String ProductRealtedDetails, String tarvetVal) {
        hmapPersonFocusProductDistributionTarget.put(ProductRealtedDetails, tarvetVal);
    }

    public void removeFocusedProductDistributionTarget(String ProductRealtedDetails) {
        if ((hmapPersonFocusProductDistributionTarget != null)) {
            hmapPersonFocusProductDistributionTarget.remove(ProductRealtedDetails);
        }
    }

    public void setFocusedProductSalesTarget(String ProductRealtedDetails, String tarvetVal) {
        hmapPersonFocusProductSalesTarget.put(ProductRealtedDetails, tarvetVal);
    }

    public void removeFocusedProductSalesTarget(String ProductRealtedDetails) {
        if ((hmapPersonFocusProductSalesTarget != null)) {
            hmapPersonFocusProductSalesTarget.remove(ProductRealtedDetails);
        }
    }
}
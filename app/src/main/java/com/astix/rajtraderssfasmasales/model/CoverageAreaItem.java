package com.astix.rajtraderssfasmasales.model;

public class CoverageAreaItem {

    private String personNodeId;
    private String CoverageAreaName;
    private String personName;
    private String CoverageAreaNodeID;

    public String getCoverageAreaNodeID() {
        return CoverageAreaNodeID;
    }

    public void setCoverageAreaNodeID(String coverageAreaNodeID) {
        CoverageAreaNodeID = coverageAreaNodeID;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getPersonNodeId() {
        return personNodeId;
    }

    public void setPersonNodeId(String personNodeId) {
        this.personNodeId = personNodeId;
    }

    public String getCoverageAreaName() {
        return CoverageAreaName;
    }

    public void setCoverageAreaName(String coverageAreaName) {
        CoverageAreaName = coverageAreaName;
    }

   }


package com.astix.rajtraderssfasmasales.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TblGetPDAQuestGrpMapping {
    @SerializedName("flgDSMOverAllFeedback")
    private int flgDSMOverAllFeedback;

    @SerializedName("flgDSMVisitFeedback")
    private int flgDSMVisitFeedback;

    @SerializedName("flgNewStore")
    private int flgNewStore;

    @SerializedName("flgStoreValidation")
    private int flgStoreValidation;

    @SerializedName("flgStoreVisitFeedback")
    private int flgStoreVisitFeedback;

    @SerializedName("GrpQuestID")
    @Expose
    private Integer grpQuestID;
    @SerializedName("QuestID")
    @Expose
    private Integer questID;
    @SerializedName("GrpID")
    @Expose
    private Integer grpID;
    @SerializedName("GrpNodeID")
    @Expose
    private Integer grpNodeID;
    @SerializedName("GrpDesc")
    @Expose
    private String grpDesc;
    @SerializedName("SectionNo")
    @Expose
    private Integer sectionNo;
    @SerializedName("GrpCopyID")
    @Expose
    private Integer grpCopyID;
    @SerializedName("QuestCopyID")
    @Expose
    private Integer questCopyID;
    @SerializedName("Sequence")
    @Expose
    private Integer sequence;

    public int getFlgDSMOverAllFeedback() {
        return flgDSMOverAllFeedback;
    }

    public void setFlgDSMOverAllFeedback(int flgDSMOverAllFeedback) {
        this.flgDSMOverAllFeedback = flgDSMOverAllFeedback;
    }

    public int getFlgDSMVisitFeedback() {
        return flgDSMVisitFeedback;
    }

    public void setFlgDSMVisitFeedback(int flgDSMVisitFeedback) {
        this.flgDSMVisitFeedback = flgDSMVisitFeedback;
    }

    public int getFlgNewStore() {
        return flgNewStore;
    }

    public void setFlgNewStore(int flgNewStore) {
        this.flgNewStore = flgNewStore;
    }

    public int getFlgStoreValidation() {
        return flgStoreValidation;
    }

    public void setFlgStoreValidation(int flgStoreValidation) {
        this.flgStoreValidation = flgStoreValidation;
    }

    public int getFlgStoreVisitFeedback() {
        return flgStoreVisitFeedback;
    }

    public void setFlgStoreVisitFeedback(int flgStoreVisitFeedback) {
        this.flgStoreVisitFeedback = flgStoreVisitFeedback;
    }

    public Integer getGrpQuestID() {
        return grpQuestID;
    }

    public void setGrpQuestID(Integer grpQuestID) {
        this.grpQuestID = grpQuestID;
    }

    public Integer getQuestID() {
        return questID;
    }

    public void setQuestID(Integer questID) {
        this.questID = questID;
    }

    public Integer getGrpID() {
        return grpID;
    }

    public void setGrpID(Integer grpID) {
        this.grpID = grpID;
    }

    public Integer getGrpNodeID() {
        return grpNodeID;
    }

    public void setGrpNodeID(Integer grpNodeID) {
        this.grpNodeID = grpNodeID;
    }

    public String getGrpDesc() {
        return grpDesc;
    }

    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc;
    }

    public Integer getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(Integer sectionNo) {
        this.sectionNo = sectionNo;
    }

    public Integer getGrpCopyID() {
        return grpCopyID;
    }

    public void setGrpCopyID(Integer grpCopyID) {
        this.grpCopyID = grpCopyID;
    }

    public Integer getQuestCopyID() {
        return questCopyID;
    }

    public void setQuestCopyID(Integer questCopyID) {
        this.questCopyID = questCopyID;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

}

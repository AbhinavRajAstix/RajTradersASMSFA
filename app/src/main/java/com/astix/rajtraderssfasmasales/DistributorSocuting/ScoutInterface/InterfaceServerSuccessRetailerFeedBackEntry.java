package com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutInterface;

import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblServerEntryStatus;

import java.util.List;

public interface InterfaceServerSuccessRetailerFeedBackEntry {
    public void successRetailerAddressFeebackRecord(int flgCalledFrom, List<TblServerEntryStatus> tblServerEntryStatusList);
    public void failureRetailerAddressFeebackRecord(int flgCalledFrom);
}

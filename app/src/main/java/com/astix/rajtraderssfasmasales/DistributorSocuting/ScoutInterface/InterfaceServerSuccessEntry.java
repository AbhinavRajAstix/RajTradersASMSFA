package com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutInterface;

import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblServerEntryStatus;

import java.util.List;

public interface InterfaceServerSuccessEntry {
    public void successRecord(int flgCalledFrom, List<TblServerEntryStatus> tblServerEntryStatusList);
    public void failureRecord(int flgCalledFrom);
}

package com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutInterface;

import com.astix.rajtraderssfasmasales.DistributorSocuting.ScoutingModels.TblPotentialDistributor;


public interface InterfaceServerSuccessDistributorDeletion {
    public void successRecordDelete(TblPotentialDistributor tblPotentialDistributorForDelete);
    public void failureRecordDelete();
}

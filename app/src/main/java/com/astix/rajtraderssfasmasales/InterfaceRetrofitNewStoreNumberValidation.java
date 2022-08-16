package com.astix.rajtraderssfasmasales;

import com.astix.rajtraderssfasmasales.model.TblDuplicateContact;

public interface InterfaceRetrofitNewStoreNumberValidation {
    public void successNewStoreNumberValidation(TblDuplicateContact tblPDARouteChangeApproval,String Number);
    public void failureNewStoreNumberValidation();
}

package com.astix.rajtraderssfasmasales;

import com.astix.rajtraderssfasmasales.model.TblStoreProductMappingForDisplay;

import java.util.List;

public interface GetCartonPrdsInterface {
    public void getAllCartonPrds(List<TblStoreProductMappingForDisplay> tblStoreProductMappingForDisplaysCarton,ProductCartonFilledDataModel productCartonFilledDataModel);
}

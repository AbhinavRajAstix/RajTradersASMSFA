package com.astix.rajtraderssfasmasales;

import android.app.Dialog;

public interface CategoryCommunicator {
	
	public void selectedOption(String selectedCategory, Dialog dialog);

	public void selectedOption(String selectedCategory, Dialog dialog, int flgCompanyCompetitorProducts);

}

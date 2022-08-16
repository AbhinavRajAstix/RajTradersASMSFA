package com.astix.rajtraderssfasmasales;


import android.content.Context;

public class CommonClassForLocationUpdateInteface {



    public static void getClassForLocationUpdateInteface(Context context) {


      InterfaceContactUpdate   interfaceRetrofit = (InterfaceContactUpdate) context;
        interfaceRetrofit.fnCallUpdateLocation();

    }

}

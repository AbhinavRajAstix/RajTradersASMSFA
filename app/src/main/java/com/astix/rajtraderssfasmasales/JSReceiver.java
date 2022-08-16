package com.astix.rajtraderssfasmasales;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.astix.rajtraderssfasmasales.database.AppDataSource;


public class JSReceiver {

    private Context mContext;
    private AppDataSource mDataSource;
    private static final String TAG = JSReceiver.class.getName();
    private RouteUpdateListener mRouteUpdateListener;

    public JSReceiver(Context mContext, RouteUpdateListener updateListener) {
        this.mContext = mContext;
        mDataSource = new AppDataSource(mContext);
        this.mRouteUpdateListener = updateListener;
    }

    @JavascriptInterface
    public void handleDataFromJS(String nodeId, String nodeType) {
        Log.d(TAG, "handleDataFromJS:" + nodeId + " " + nodeType);
       /*  mDataSource.setActiveRouteId(nodeId);
       JointVisitFragment.routeId = nodeId;
        JointVisitFragment.routeType= nodeType;
*/
        mRouteUpdateListener.onUpdateRoute(nodeId,nodeType);
    }

    public interface RouteUpdateListener{
        void onUpdateRoute(String routeId, String routeType);
    }

}

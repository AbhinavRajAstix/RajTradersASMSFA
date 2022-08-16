package com.astix.rajtraderssfasmasales;

import android.app.ProgressDialog;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.astix.Common.CommonInfo;


public class MyBrowserRouteSummary extends WebViewClient {
    private ProgressDialog progressBar;
    private Integer RouteID = 0;
    private Integer RouteNodeType = 0;
    private Integer PersonNodeID = 0;
    private Integer PersonNodeType = 0;
    private String IMEI = "0";
    private String ReportViewDate = "0";
    private int CoverageAreaNodeID = 0;
    private int CoverageAreaNodeType = 0;

    public MyBrowserRouteSummary(ProgressDialog progressBar) {
        this.progressBar = progressBar;

        progressBar.show();
    }

    public MyBrowserRouteSummary(ProgressDialog progressBar, int RouteID, int RouteNodeType, int PersonNodeID, int PersonNodeType, String IMEI, String ReportViewDate,int CoverageAreaNodeID,int CoverageAreaNodeType) {
        this.progressBar = progressBar;
        this.RouteID = RouteID;
        this.RouteNodeType = RouteNodeType;
        this.PersonNodeID = PersonNodeID;
        this.PersonNodeType = PersonNodeType;
        this.CoverageAreaNodeID = CoverageAreaNodeID;
        this.CoverageAreaNodeType = CoverageAreaNodeType;
        this.IMEI = IMEI;
        this.ReportViewDate = ReportViewDate;

        progressBar.show();
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub
        view.loadUrl(url);
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript("fnGetfromPDA_New();", null);
        } else {
            view.loadUrl("javascript:fnGetfromPDA_New();");
        }*/
        return true;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub


        super.onPageFinished(view, url);

        //4,140,0,0,'0987654321','26-FEB-2019',1.4
       view.loadUrl("javascript:fnGetfromPDA('" + this.RouteID + "','" + this.RouteNodeType + "','" + PersonNodeID + "','" + PersonNodeType + "','" + this.IMEI + "','" + ReportViewDate + "','" + CommonInfo.DATABASE_VERSIONID + "','"+CoverageAreaNodeID+"','"+CoverageAreaNodeType+"')");

      /*  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript("fnGetfromPDA_New();", null);
        } else {*/
           // view.loadUrl("javascript:fnGetfromPDA_New();");
       // }
     //   view.loadUrl("javascript:fnGetfromPDA_New()");
     //   view.setVisibility(View.VISIBLE);
        if(progressBar!=null && progressBar.isShowing())
        progressBar.dismiss();
    }
}

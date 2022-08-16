package com.astix.rajtraderssfasmasales;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.astix.Common.CommonInfo;

import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.sync.DatabaseAssistant;
import com.astix.rajtraderssfasmasales.sync.SyncJobService;
import com.astix.rajtraderssfasmasales.utils.AppUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.regex.Pattern;


public class DynamicQuestionsFragment extends BaseActivity implements SearchListCommunicator, CategoryCommunicatorCityState {
    LinearLayout ll_overAllSignatureSection,ll_ASMSignatureSection,linearLayoutASMSign,ll_TSIignatureSection,linearLayoutTSISign,mContentASM,mContentTSI;
    RelativeLayout rl_ASMSign_layout,rl_TSISign_layout;
    Button clearASMSign,getsignASM,clearTSISign,getsignTSI,cancelASMSign,cancelTSISign;
    ImageView transparent_imageASM,transparent_imageTSI;
    File file;
    View view;
    View viewASM;
    View viewTSI;
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/" + CommonInfo.ImagesFolder + "/";
    String timeStamp = new SimpleDateFormat("yyyyMMMdd_HHmmss", Locale.ENGLISH).format(new Date());
    signatureASM mSignature;
    signatureTSI mSignatureTSI;
    Bitmap bitmap;
    Bitmap bitmapTSI;
    boolean signOrNotASM = false;
    boolean signOrNotTSI = false;
    String pic_nameASMSign="";
    String ImgPathASMSign="";
    String pic_nameTSISign="";
    String ImgPathTSISign="";

    NewStoreFormFeedBack newStore_Fragment;
    FragmentManager manager;
    FragmentTransaction fragTrans;
    TextView tv_feedbackheading;
    private static final String TAG = "LocationActivity";
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 1; // 1 second
    private static final long MIN_TIME_BW_UPDATESNew = 100 * 1; // 1 second
    public static String StoreNameFromBack = "";
    public static String currentBeatName = "All";
    public static String OwnerName, StoreContactNo, StoreCatType;
    public static int flgRuleTaxVal, flgTransType;
    public static String distID = "0";
    public static String channelOptId;
    public static String storeNameToShow = "";
    public static int ServiceWorkerDataComingFlag = 0;
    public static String ServiceWorkerStoreID = "";
    public static String ServiceWorkerResultSet = "";
    public static LinkedHashMap<String, ArrayList<String>> hmapSection_key = new LinkedHashMap<String, ArrayList<String>>();
    public static LinkedHashMap<String, String> hmapDpndtQustGrpId = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, String> hmapQuesIdandGetAnsCntrlType = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, String> hmapQuesIdValues = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, ArrayList<String>> hmapQuesGropKeySection = new LinkedHashMap<String, ArrayList<String>>();
    public static LinkedHashMap<String, String> hmapGroupId_Desc = new LinkedHashMap<String, String>();
    public static LinkedHashMap<String, ArrayList<String>> hmapSctnId_GrpId = new LinkedHashMap<String, ArrayList<String>>();
    public static String date_value = "";
    public static String pickerDate = "";
    public static String imei;
    public static String rID;
    public static String activityFrom = "";
    public static String VisitStartTS = "NA";
    public static String selStoreID = "";
    public static int mFeedbackType = 0;
    public static String StoreVisitCode = "NA";
    public static String JOINTVISITID = "NA";
    public static int VisitType = 0;
    private final long startTime = 15000;
    private final long interval = 200;
    public int flgCompetitorDataRequired = -1;
    public String flgGSTCapture = "1";
    public String flgGSTCompliance = "0";
    public String GSTNumber = "NA";
    public int flgGSTRecordFromServer = 0;
    public String newfullFileName;
    public String QuestIDForOutChannel = "0";
    public int flgHasQuote = 0;
    public int flgAllowQuotation = 1;
    public int flgSubmitFromQuotation = 0;
    public String OutletID = "NA";
    public String StoreName = "NA", fnlStoreType = "0", fnlOwnerName = "NA", fnlMobileNumber = "NA", fnlAddressName = "NA", fnSalesPersonName = "NA", fnSalesPersonContactNo = "NA", fnStoreCatType = "NA", fnStoreChainTypeID = "0";
    public String fncurrentOptSelectedForCompetitorStckAvailable, fncurrentOptSelectedForPTRDoneByDSR;
    public String StoreName_tag = "NA";
    public int checkSecondTaskStatus = 0;
    public Timer timer;
    public ProgressDialog pDialogGetStores;
    public int chkFlgForErrorToCloseApp = 0;
    public LocationManager locationManager;
    public float Current_acc;
    public Location location;
    public ProgressDialog pDialog2STANDBY;
    public double latitude; // latitude
    public double longitude; // longitude
    public boolean isGPSEnabled = false;
    public boolean isNetworkEnabled = false;
    public PowerManager pm;
    public PowerManager.WakeLock wl;
    public float acc = 0F;
    // public static LinkedHashMap<String, String> hmapOptionId_OptionValue=new LinkedHashMap<String, String>();
    public String accuracy = "0";
    public int sectionToShowHide = 1;
    int IsComposite = 0;
    int flgCheckNewOldStore = 0;
    String fusedData;
    RelativeLayout rl_sectionMap, rl_sectionQuest;
    String VisitEndTS;
    String CustomStoreID = "NA";
    Button btnSubmit;

    ImageView img_next;
    TextView txt_Next,txt_storeSummary;
    LinkedHashMap<String, String> hmapStoreQuestAnsNew = new LinkedHashMap<String, String>();
    LinkedHashMap<String, String> hmapStoreAddress = new LinkedHashMap<String, String>();
    LinearLayout ll_next, ll_back, ll_save_Exit;
    //    LinearLayout ll_map,ll_refresh;
    int refreshCount = 0;
    //    RadioGroup rg_yes_no;
//    RadioButton rb_yes,rb_no;
//    Button btn_refresh;
//    TextView txt_rfrshCmnt;
    ImageView img_exit,ivBack;
    AppDataSource dbengine;
    DatabaseAssistant DA;
    private boolean mIsServiceStarted = false;
    private Activity mActivity;

    private int flgPTRPTCMarked = 0;
    public static int flgJointVisit = 1;
    private ScrollView svItems;

  /*  public static DynamicQuestionsFragment newInstance(Bundle args) {

        DynamicQuestionsFragment fragment = new DynamicQuestionsFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newstoredynamicsectionwise_feedback);
        //view = inflater.inflate(R.layout.activity_newstoredynamicsectionwise, container, false);
        mActivity = this;
       // new AppUtils().callParentMethod(mActivity);
        dbengine = AppDataSource.getInstance(mActivity);
        DA = new DatabaseAssistant(mActivity);
        refreshCount = 0;
        channelOptId = "0";
        long syncTIMESTAMP1 = System.currentTimeMillis();
        Date dateobj1 = new Date(syncTIMESTAMP1);
        SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        VisitStartTS = df1.format(dateobj1);
        storeNameToShow = "";
        Bundle extras = getIntent().getExtras();
        JOINTVISITID = extras.getString(AppUtils.JOINTVISITID);//Fetch the Joint Visit Code
        activityFrom = extras.getString("activityFrom");
        mFeedbackType = extras.getInt(AppUtils.FeedbackType);
        flgJointVisit = extras.getInt(AppUtils.FLGJOINTVISIT);
        VisitType= extras.getInt(AppUtils.VisitType);

        currentBeatName = extras.getString("CurrntRouteName");

        flgCheckNewOldStore = 0;




        selStoreID = extras.getString(AppUtils.StoreId);
        StoreNameFromBack = extras.getString(AppUtils.StoreName);
        flgPTRPTCMarked = extras.getInt(AppUtils.PTRPTC_MARKED, 0);
        if (StoreNameFromBack.equals("NA") || StoreNameFromBack.equals("")) {
            StoreNameFromBack = "";
        }
        StoreVisitCode = dbengine.fnGetStoreVisitCodeInCaseOfIndrectSales(selStoreID);
        tv_feedbackheading=findViewById(R.id.tv_feedbackheading);
       /* if(mFeedbackType==3)
        {
            tv_feedbackheading.setText("TSO Overall Feedback");
        }
        if(mFeedbackType==2)
        {
            tv_feedbackheading.setText("Store Feedback");
        }

        if(mFeedbackType==1)
        {
            tv_feedbackheading.setText("TSO Store Feedback");
        }*/
        if (mFeedbackType == 2) {
            selStoreID = "0";
            StoreNameFromBack = "NA";
        }

        imei = AppUtils.getToken(mActivity);
        ll_back = (LinearLayout)findViewById(R.id.ll_back);
        ll_next = (LinearLayout) findViewById(R.id.ll_next);
        ll_save_Exit = (LinearLayout) findViewById(R.id.ll_save_Exit);
        img_next = (ImageView) findViewById(R.id.img_next);
        img_exit = (ImageView) findViewById(R.id.img_exit);
        ivBack = (ImageView) findViewById(R.id.ivBack);
        txt_storeSummary= (TextView) findViewById(R.id.txt_storeSummary);
        txt_Next = (TextView) findViewById(R.id.txt_Next);
        svItems=(ScrollView)findViewById(R.id.svItems);

        ll_overAllSignatureSection = (LinearLayout) findViewById(R.id.ll_overAllSignatureSection);
        ll_ASMSignatureSection = (LinearLayout) findViewById(R.id.ll_ASMSignatureSection);
        linearLayoutASMSign = (LinearLayout) findViewById(R.id.linearLayoutASMSign);
        ll_TSIignatureSection = (LinearLayout) findViewById(R.id.ll_TSIignatureSection);
        linearLayoutTSISign = (LinearLayout) findViewById(R.id.linearLayoutTSISign);

        rl_ASMSign_layout = (RelativeLayout) findViewById(R.id.rl_ASMSign_layout);
        rl_TSISign_layout = (RelativeLayout) findViewById(R.id.rl_TSISign_layout);


        pic_nameASMSign = "IMG_ASM_" + imei + timeStamp+ ".png";
        ImgPathASMSign = DIRECTORY + pic_nameASMSign ;

        pic_nameTSISign = "IMG_TSI_" + imei + timeStamp + ".png";
        ImgPathTSISign = DIRECTORY + pic_nameTSISign;


        ll_overAllSignatureSection.setVisibility(View.GONE);
        if(mFeedbackType==2)
        {
            ll_overAllSignatureSection.setVisibility(View.VISIBLE);
            SharedPreferences sharedPrefCove= getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
            if(sharedPrefCove!=null && sharedPrefCove.contains("JointVisitPersonName"))
                txt_storeSummary.setText(sharedPrefCove.getString("JointVisitPersonName","NA")+" Overall Feedback");
                else
            txt_storeSummary.setText("TSI Overall Feedback");
        }
       /* if(mFeedbackType==2)
        {
            txt_storeSummary.setText("Store Feedback");
        }*/
        if(mFeedbackType==0)
        {
            if(StoreNameFromBack!=null && !TextUtils.isEmpty(StoreNameFromBack))
            txt_storeSummary.setText(StoreNameFromBack +" Feedback");
            else
                txt_storeSummary.setText("Store Feedback");
        }
        if(mFeedbackType==1)
        {
            SharedPreferences sharedPrefCove= getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
            if(sharedPrefCove!=null && sharedPrefCove.contains("JointVisitPersonName"))
                txt_storeSummary.setText(sharedPrefCove.getString("JointVisitPersonName","NA")+" Store Feedback");
            else
            txt_storeSummary.setText("TSI Store Feedback");
        }
      /*  svItems = (ScrollView) findViewById(R.id.svItems);


        svItems.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                // Grab the last child placed in the ScrollView, we need it to determinate the bottom position.
                listenScrollView();
            }
        });
*/

        fillHmapData();
        addFragment();

        ivBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getSectionNextOrBack(6, sectionToShowHide);
                NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                StoreName = recFragment.currentStoreName;
            }
        });

        img_exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                getSectionNextOrBack(4, sectionToShowHide);
                NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                StoreName = recFragment.currentStoreName;

            }
        });

        ll_save_Exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //
                getSectionNextOrBack(2, sectionToShowHide);
                NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                StoreName = recFragment.currentStoreName;


            }
        });

        ll_next.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                hideSoftKeyboard(v);

                if (sectionToShowHide < hmapSctnId_GrpId.size() - 1) {
                    boolean isNextMoved = getSectionNextOrBack(0, sectionToShowHide);
                    if (isNextMoved) {
                        sectionToShowHide++;
                        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                        StoreName = recFragment.currentStoreName;
                        if (sectionToShowHide != 1) {
                            if (ll_back.getVisibility() == View.INVISIBLE) {
                                ll_back.setVisibility(View.VISIBLE);
                            }


                        }
                    }

                } else if (sectionToShowHide > hmapSctnId_GrpId.size() - 1) {

                    //NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack)getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                    NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                    if (recFragment != null) {
                        if (recFragment.validate())// && recFragment.validateNameFilled()
                        {
                            if(mFeedbackType==2) {
                                if (fnValidateSignatures()) {
                                    StoreName = recFragment.currentStoreName;
                                    showSubmitConfirm();
                                }
                            }
                            else
                            {
                                StoreName = recFragment.currentStoreName;
                                showSubmitConfirm();
                            }
                        }
                    }
                } else {
                    NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                    StoreName = recFragment.currentStoreName;
                    boolean isNextMoved = getSectionNextOrBack(0, sectionToShowHide);
                    if (isNextMoved) {
                        if (sectionToShowHide == hmapSctnId_GrpId.size() - 1) {
                            sectionToShowHide++;

                           /* if (mFeedbackType == 0 || mFeedbackType == 2) {
                                img_next.setImageResource(R.drawable.done);
                                txt_Next.setText(getText(R.string.txtSubmit));
                            } else {
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }*/

                            if (mFeedbackType ==3 || mFeedbackType ==2 || mFeedbackType ==1) {
                                img_next.setImageResource(R.drawable.done);
                                txt_Next.setText(getText(R.string.txtSubmit));
                            } /*else if (mFeedbackType ==2){
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }*/
                            else {
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }
                        }

                        if (ll_back.getVisibility() == View.INVISIBLE) {
                            ll_back.setVisibility(View.VISIBLE);
                        }


                    }
                }


            }
        });

      /*  if (mFeedbackType == 0 || mFeedbackType == 2) {
            img_next.setImageResource(R.drawable.done);
            txt_Next.setText(getText(R.string.txtSubmit));
        } else {
            img_next.setImageResource(R.drawable.next);
            txt_Next.setText(getText(R.string.txtSaveNext));
        }*/


        if (mFeedbackType ==3 || mFeedbackType ==2 || mFeedbackType ==1) {
            img_next.setImageResource(R.drawable.done);
            txt_Next.setText(getText(R.string.txtSubmit));
        } /*else if (mFeedbackType ==2){
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }*/
        else {
            img_next.setImageResource(R.drawable.next);
            txt_Next.setText(getText(R.string.txtSaveNext));
        }
        ll_back.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                sectionToShowHide--;
                getSectionNextOrBack(1, sectionToShowHide);
                NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                StoreName = recFragment.currentStoreName;

                if (sectionToShowHide == 1) {
                    if (ll_back.getVisibility() == View.VISIBLE) {
                        ll_back.setVisibility(View.INVISIBLE);

                    }

                }
                if (sectionToShowHide < hmapSctnId_GrpId.size()) {

                    /*if (mFeedbackType == 0 || mFeedbackType == 2) {
                        img_next.setImageResource(R.drawable.done);
                        txt_Next.setText(getText(R.string.txtSubmit));
                    } else {
                        img_next.setImageResource(R.drawable.next);
                        txt_Next.setText(getText(R.string.txtSaveNext));
                    }*/
                    if (mFeedbackType ==3 || mFeedbackType ==2 || mFeedbackType ==1) {
                        img_next.setImageResource(R.drawable.done);
                        txt_Next.setText(getText(R.string.txtSubmit));
                    } /*else if (mFeedbackType ==2){
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }*/
                    else {
                        img_next.setImageResource(R.drawable.next);
                        txt_Next.setText(getText(R.string.txtSaveNext));
                    }
                    /*img_next.setImageResource(R.drawable.next);
                    txt_Next.setText(getText(R.string.lastvisitdetails_next));*/
                }

            }
        });


        if (sectionToShowHide == hmapSctnId_GrpId.size()) {

           /* img_next.setImageResource(R.drawable.next);
            txt_Next.setText(getText(R.string.txtSaveNext));*/
            if (mFeedbackType ==3 || mFeedbackType ==2 || mFeedbackType ==1) {
                img_next.setImageResource(R.drawable.done);
                txt_Next.setText(getText(R.string.txtSubmit));
            } /*else if (mFeedbackType ==2){
                                img_next.setImageResource(R.drawable.next);
                                txt_Next.setText(getText(R.string.txtSaveNext));
                            }*/
            else {
                img_next.setImageResource(R.drawable.next);
                txt_Next.setText(getText(R.string.txtSaveNext));
            }
            sectionToShowHide++;

        }
        signatureAllCodeASM();
        signatureAllCodeTSI();
        //handleNextButtonVisibility(0);
        //return view;
    }

    public void scrollCheck() {
        View view = (View) svItems.getChildAt(svItems.getChildCount() - 1);
        if(svItems!=null) {
            int childHeight = view.getHeight();
            System.out.println("0000 Child height" + childHeight);
            System.out.println("0000 ScrollView Height" + svItems.getHeight());
            boolean isScrollable = svItems.getHeight() < (childHeight + svItems.getPaddingTop() + svItems.getPaddingBottom());
            if (isScrollable)
                handleNextButtonVisibility(0);
            else
                handleNextButtonVisibility(1);

        }
    }

    public void listenScrollView() {

        System.out.println("0000 CHild count here::" + svItems.getChildCount());
        View view = (View) svItems.getChildAt(svItems.getChildCount() - 1);

        System.out.println("0000 Bottom::" + view.getBottom());
        System.out.println("0000 Scroll Height::" + svItems.getHeight());
        System.out.println("0000 Scroll Y::" + svItems.getScrollY());
        // Calculate the scrolldiff
        int diff = (view.getBottom() - (svItems.getHeight() + svItems.getScrollY()));

        // if diff is zero, then the bottom has been reached
        if (diff == 0) {
            // notify that we have reached the bottom
            handleNextButtonVisibility(1);
        } else
            handleNextButtonVisibility(0);
    }


    public void handleNextButtonVisibility(int flgVisible) {

        if (flgVisible == 0)
            ll_next.setVisibility(View.INVISIBLE);
        else
            ll_next.setVisibility(View.VISIBLE);
    }

    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public boolean getSectionNextOrBack(int isNextPressed, int sectionToShowOrHide) {
        boolean isNextMoved = false;
        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
        if (null != recFragment) {
            isNextMoved = recFragment.nextOrBackSection(isNextPressed, sectionToShowOrHide);
        }
        return isNextMoved;
    }

    private void addFragment() {

        newStore_Fragment = new NewStoreFormFeedBack();
        fragTrans = getFragmentManager().beginTransaction();
        fragTrans.replace(R.id.fragmentForm, newStore_Fragment, "NewStoreFragmentFeedBack");

        fragTrans.commit();

    }

    private void fillHmapData() {
        //QuestID,QuestCode,QuestDesc,QuestType,AnsControlType,AnsControlInputTypeID,AnsControlInputTypeMaxLength,AnsMustRequiredFlg,QuestBundleFlg,ApplicationTypeID,Sequence,AnsControlInputTypeMinLength,AnsHint,QuestBundleGroupId
        //hmapQuesIdValues.put("1^2", "1^")
        SharedPreferences sPref = this.getSharedPreferences("LanguagePref", MODE_PRIVATE);

        hmapQuesIdValues = dbengine.fnGetQuestionMstr(sPref,mFeedbackType);
        hmapQuesGropKeySection = dbengine.fnGetQuestionMstrKey(mFeedbackType);
        hmapGroupId_Desc = dbengine.getGroupDescription(mFeedbackType);
        hmapSctnId_GrpId = dbengine.fnGetGroupIdMpdWdSectionId(mFeedbackType);
        hmapDpndtQustGrpId = dbengine.fnGetDependentQuestionMstr();
        hmapSection_key = dbengine.fnGetSection_Key(mFeedbackType);

        //helperDb.open();
        channelOptId = dbengine.getChannelGroupIdOptId();
        //helperDb.close();
        //   hmapOptionId_OptionValue=helperDb.fnGetOptionId_OptionValue();
        QuestIDForOutChannel = dbengine.fnGetQuestIDForOutChannelFromQuestionMstr(mFeedbackType);

    }

    public void showSubmitConfirm() {

        AlertDialog.Builder alertDialogSubmitConfirm = new AlertDialog.Builder(mActivity);
        alertDialogSubmitConfirm.setTitle(getText(R.string.genTermInformation));


        if (mFeedbackType == 0) {
            //alertDialogSubmitConfirm.setMessage(getText(R.string.SubmitStoreVisitFeedBack));
            alertDialogSubmitConfirm.setMessage("Save Data");
        }
        if (mFeedbackType == 1) {

            NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
            if (null != recFragment) {
                recFragment.saveDynamicQuesAns(true);
                String fncurrentOptSelectedForCompetitorStckAvailableNew = recFragment.hmapAnsValues.get(recFragment.grpQuestTagForCompetitorStckAvailable);
                String fncurrentOptSelectedForPTRDoneByDSRNew = recFragment.hmapAnsValues.get(recFragment.grpQuestTagForPTRDoneByDSR);
                if ((fncurrentOptSelectedForCompetitorStckAvailableNew != null && fncurrentOptSelectedForPTRDoneByDSRNew != null) && fncurrentOptSelectedForCompetitorStckAvailableNew.equals("212-0-0") && fncurrentOptSelectedForPTRDoneByDSRNew.equals("214-0-0")) {
                    alertDialogSubmitConfirm.setMessage(getText(R.string.SubmitAllData));
                } else {
                    alertDialogSubmitConfirm.setMessage(getText(R.string.SubmitDSRVisitFeedBack));
                }
            } else {
                alertDialogSubmitConfirm.setMessage(getText(R.string.SubmitDSRVisitFeedBack));
            }
        }
        if (mFeedbackType == 2) {
            alertDialogSubmitConfirm.setMessage(getText(R.string.SubmitDSROverAllFeedBack));
        }

        alertDialogSubmitConfirm.setCancelable(false);

        alertDialogSubmitConfirm.setNeutralButton(getText(R.string.AlertDialogYesButton), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                sectionToShowHide--;

                dialog.dismiss();


                NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
                if (null != recFragment) {
                    recFragment.saveDynamicQuesAns(true);
/*
                    hmapStoreQuestAnsNew = recFragment.hmapAnsValues;
                    hmapStoreAddress=recFragment.hmapAddress;
                    fnlStoreType=recFragment.currentStoreType;
                    fnlOwnerName=recFragment.currentOwnerName;
                    fnlMobileNumber=recFragment.currentMobileNumber;
                    fnlAddressName=recFragment.currentAddressName;
                    fnSalesPersonName=recFragment.currentSalesPersonName;
                    fnSalesPersonContactNo=recFragment.currentSalesPersonContactNo;
                    fnStoreCatType=recFragment.currentStoreCatType;
                    fnStoreChainTypeID=recFragment.currentStoreChainTypeID.split(Pattern.quote("-"))[1];

                    if(!TextUtils.isEmpty(recFragment.distBId))
                    {
                        distID=recFragment.distBId;
                    }*/

                }


                try {
                    if(mFeedbackType==2)
                    {
                        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        // showAlertForEveryOne("Everything is fine");
                        //saving sign to folder
                        viewASM.setDrawingCacheEnabled(true);
                        mSignature.save(viewASM, ImgPathASMSign);
                        viewTSI.setDrawingCacheEnabled(true);
                        mSignatureTSI.save(viewTSI, ImgPathTSISign);

                        mDataSource.endJointVisit(JOINTVISITID,DynamicQuestionsFragment.this);

                        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                    if (AppUtils.isOnline(mActivity)) {

                        addStoreOffline();
                    } else {

                        addStoreOffline();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("onGetStoresForDayCLICK: Exec(). EX: "+e);
                }
            }
        });

        alertDialogSubmitConfirm.setNegativeButton(getText(R.string.AlertDialogNoButton), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogSubmitConfirm.setIcon(R.drawable.info_ico);

        AlertDialog alert = alertDialogSubmitConfirm.create();

        alert.show();

    }


    public void addStoreOffline() {


        long syncTIMESTAMP = System.currentTimeMillis();
        Date datefromat = new Date(syncTIMESTAMP);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        SimpleDateFormat dfDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


        String VisitEndFinalTS = df.format(datefromat);
        String VisitDate = dfDate.format(datefromat);
        // int DatabaseVersion=CommonInfo.DATABASE_VERSIONID;
        int ApplicationID = CommonInfo.Application_TypeID;
        //getSectionNextOrBack(3,sectionToShowHide );


        String selectedBeatName = "";
        String slctdBeatId = "0";
        String slctdBeatNodeType = "0";
        LinkedHashMap<String, String> hmapStoreQuestAnsNew = new LinkedHashMap<String, String>();
        LinkedHashMap<String, String> hmapStoreAddress = new LinkedHashMap<String, String>();
        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
        if (null != recFragment) {

            recFragment.saveDynamicQuesAns(true);
            hmapStoreQuestAnsNew = recFragment.hmapAnsValues;
            hmapStoreAddress = recFragment.hmapAddress;
            selectedBeatName = recFragment.getSelectedBeatName();
            if (!selectedBeatName.equals("0") && (selectedBeatName.contains("-"))) {
                slctdBeatId = selectedBeatName.split(Pattern.quote("-"))[1];
                slctdBeatNodeType = selectedBeatName.split(Pattern.quote("-"))[2];
            }
        }

        dbengine.fnsaveOutletQuestAnsMstrSectionWise(hmapStoreQuestAnsNew, 0, selStoreID, JOINTVISITID, mFeedbackType);//, StoreVisitCode

        // Check Where to redirect the page
        if (mFeedbackType == 0) // STORE VISIT FEEDBACK
        {
//            Bundle args = new Bundle();
//            args.putString(AppUtils.StoreName, StoreNameFromBack);
//            args.putString(AppUtils.StoreId, selStoreID);
//            args.putString(AppUtils.JOINTVISITID, JOINTVISITID);
//            args.putString("activityFrom", activityFrom);
//            args.putString("CurrntRouteName", currentBeatName);
//            args.putInt(AppUtils.FeedbackType, 1);
//            args.putInt(AppUtils.IS_COMPETITOR_AVAILABLE, 0);
//            args.putInt(AppUtils.IS_STOCK_AVAILABLE, 0);
//            args.putInt(AppUtils.PTRPTC_MARKED, flgPTRPTCMarked);
//
//            ((MarketVisitActivity) mActivity).loadFragment(args, MarketVisitActivity.DYNAMICQUESTIONS);


           /* androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
            builder.setTitle(R.string.app_nameAlertInfo)
                    .setMessage("Visit Submitted successfully.")
                    .setCancelable(false)
                    .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dbengine.updateStoreStatFlag(selStoreID, 3);
                            FullSyncDataNow task = new FullSyncDataNow(DynamicQuestionsFragment.this);
                            task.execute();
                            // ((MarketVisitActivity)mActivity).finish();
                        }
                    });
            builder.show();*/
            /*androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
            builder.setTitle(R.string.app_nameAlertInfo)
                    .setMessage("Visit Data Saved successfully.")
                    .setCancelable(false)
                    .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dbengine.updateStoreStatFlag(selStoreID, 3);
                            FullSyncDataNow task = new FullSyncDataNow(DynamicQuestionsFragment.this);
                            task.execute();
                            // ((MarketVisitActivity)mActivity).finish();
                        }
                    });
            builder.show();*/

            Bundle args = new Bundle();
            args.putString(AppUtils.StoreName, StoreNameFromBack);
            args.putString(AppUtils.StoreId, selStoreID);
            args.putString(AppUtils.JOINTVISITID, StoreSelection.JointVisitId);
            args.putInt(AppUtils.FLGJOINTVISIT, flgJointVisit);
            args.putString("activityFrom", "STORELIST");
            args.putString("CurrntRouteName", "XYZ");
            args.putInt(AppUtils.FeedbackType, 1);
            args.putInt(AppUtils.IS_COMPETITOR_AVAILABLE, 0);
            args.putInt(AppUtils.IS_STOCK_AVAILABLE, 0);
            args.putInt(AppUtils.PTRPTC_MARKED, 0);
            args.putInt(AppUtils.VisitType, VisitType);

            Intent mainIntent = new Intent(DynamicQuestionsFragment.this,DynamicQuestionsFragment.class);
            mainIntent.putExtras(args);
            startActivity(mainIntent);
            finish();


        }
        if (mFeedbackType == 1)  // DSM VISIT FEEDBACK
        {
            //Check the Condition redirect further


            if (null != recFragment) {
                //flgStorePTCPTRMarked
               /* if (hmapStoreQuestAnsNew.get(recFragment.grpQuestTagForCompetitorStckAvailable) != null)
                    fncurrentOptSelectedForCompetitorStckAvailable = hmapStoreQuestAnsNew.get(recFragment.grpQuestTagForCompetitorStckAvailable);
                else
                    fncurrentOptSelectedForCompetitorStckAvailable = "0";
                fncurrentOptSelectedForPTRDoneByDSR = hmapStoreQuestAnsNew.get(recFragment.grpQuestTagForPTRDoneByDSR);

//                fncurrentOptSelectedForCompetitorStckAvailable = recFragment.currentOptSelectedForCompetitorStckAvailable;
//                fncurrentOptSelectedForPTRDoneByDSR = recFragment.currentOptSelectedForPTRDoneByDSR;
                int flgfncurrentOptSelectedForCompetitorStckAvailable = 0;
                int flgfncurrentOptSelectedForPTRDoneByDSR = 0;
                if (fncurrentOptSelectedForCompetitorStckAvailable.equals("212-0-0"))//Yes
                {
                    flgfncurrentOptSelectedForCompetitorStckAvailable = 1;
                }
                if (fncurrentOptSelectedForPTRDoneByDSR.equals("214-0-0"))//Yes
                {
                    flgfncurrentOptSelectedForPTRDoneByDSR = 1;
                }*/
                //Check the Condition redirect further

                flgCompetitorDataRequired = -1;

                // 0- Data Populated from DSR App
                // 1- Mandatory to fill PTR/PTC data by ASM
                // 2- Done here itself

             /*   if (flgPTRPTCMarked == 1) {
                    if (flgfncurrentOptSelectedForCompetitorStckAvailable == 1 && flgfncurrentOptSelectedForPTRDoneByDSR == 1) {
                        flgCompetitorDataRequired = 2;
                    } else if (flgfncurrentOptSelectedForCompetitorStckAvailable == 1 && flgfncurrentOptSelectedForPTRDoneByDSR == 0) {
                        flgCompetitorDataRequired = 1;
                    } else if (flgfncurrentOptSelectedForCompetitorStckAvailable == 0 && flgfncurrentOptSelectedForPTRDoneByDSR == 1) {
                        flgCompetitorDataRequired = 0;
                    } else
                        flgCompetitorDataRequired = 2;
                } else {
                    if (flgfncurrentOptSelectedForCompetitorStckAvailable == 1 && flgfncurrentOptSelectedForPTRDoneByDSR == 1) {
                        flgCompetitorDataRequired = 2;
                    } else if (flgfncurrentOptSelectedForCompetitorStckAvailable == 1 && flgfncurrentOptSelectedForPTRDoneByDSR == 0) {
                        flgCompetitorDataRequired = 1;
                    } else if (flgfncurrentOptSelectedForCompetitorStckAvailable == 0 && flgfncurrentOptSelectedForPTRDoneByDSR == 1) {
                        flgCompetitorDataRequired = 0;
                    } else
                        flgCompetitorDataRequired = 2;
                }*/

                flgCompetitorDataRequired = 2;
                if (flgCompetitorDataRequired == 2) {

                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
                    builder.setTitle(R.string.app_nameAlertInfo)
                            .setMessage("Visit Submitted successfully.")
                            .setCancelable(false)
                            .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dbengine.updateStoreStatFlag(selStoreID, 3);
                                    FullSyncDataNow task = new FullSyncDataNow(DynamicQuestionsFragment.this);
                                    task.execute();
                                    // ((MarketVisitActivity)mActivity).finish();
                                }
                            });
                    builder.show();


                } /*else {
                    Bundle args = new Bundle();
                    args.putString(AppUtils.StoreName, StoreNameFromBack);
                    args.putString(AppUtils.StoreId, selStoreID);
                    args.putString(AppUtils.JOINTVISITID, JOINTVISITID);
                    args.putString("activityFrom", activityFrom);
                    args.putString("CurrntRouteName", currentBeatName);
                    args.putInt(AppUtils.FeedbackType, 0);
                    args.putInt(AppUtils.IS_COMPETITOR_AVAILABLE, flgfncurrentOptSelectedForPTRDoneByDSR);
                    args.putInt(AppUtils.IS_STOCK_AVAILABLE, flgCompetitorDataRequired);
                    args.putInt(AppUtils.PTRPTC_MARKED, flgPTRPTCMarked);
                    args.putInt(AppUtils.FLGJOINTVISIT, flgJointVisit);

                    ((MarketVisitActivity) mActivity).loadFragment(args, MarketVisitActivity.FEEDBACKCOMPETITOR);
                }*/
            }

        }
        if (mFeedbackType == 2)  // DSM OVERALL FEEDBACK
        {
            //Redirect to Store Selection Fragment

            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(mActivity);
            builder.setTitle(R.string.app_nameAlertInfo)
                    .setMessage("Feedback saved successfully.")
                    .setCancelable(false)
                    .setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dbengine.endJointVisit(JOINTVISITID,DynamicQuestionsFragment.this);

                            FullSyncDataNow task = new FullSyncDataNow(DynamicQuestionsFragment.this);
                            task.execute();
                            //((MarketVisitActivity)mActivity).finish();
                        }
                    });
            builder.show();


//            Bundle args=new Bundle();
//            args.putString(AppUtils.StoreName,StoreNameFromBack);
//            args.putString(AppUtils.StoreId,selStoreID);
//            args.putString(AppUtils.JOINTVISITID,JOINTVISITID);
//            args.putString("activityFrom",activityFrom);
//            args.putString("CurrntRouteName",currentBeatName);
//            args.putInt(AppUtils.FeedbackType,mFeedbackType);
//            args.putInt(AppUtils.IS_COMPETITOR_AVAILABLE,0);
//            args.putInt(AppUtils.IS_STOCK_AVAILABLE,0);
//            args.putInt(AppUtils.PTRPTC_MARKED,flgPTRPTCMarked);
//
//            ((MarketVisitActivity)mActivity).loadFragment(args,MarketVisitActivity.DYNAMICQUESTIONS);
        }

    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                Log.i("isMyServiceRunning?", true + "");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", false + "");
        return false;
    }

    public void fetchAddress() {


        /*Intent intent = new Intent(this, GeocodeAddressIntentService.class);
        intent.putExtra("Reciever", mResultReceiver);
        Location location = new Location("");
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        intent.putExtra("Location", location);
        startService(intent);*/
    }

    @Override
    public void selectedOption(String optId, String optionVal,
                               EditText txtVw, ListView listViewOption, String tagVal,
                               Dialog dialog, TextView textView, ArrayList<String> listStoreIDOrigin) {

        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
        if (null != recFragment) {
            recFragment.selectedOption(optId, optionVal, txtVw, listViewOption, tagVal, dialog, textView, listStoreIDOrigin);
        }

    }

    @Override
    public void selectedStoreMultiple(String optId, String optionVal,
                                      EditText txtVw, ListView listViewOption, String tagVal,
                                      Dialog dialog, TextView textView, LinearLayout ll_SlctdOpt,
                                      ArrayList<String> listSelectedOpt,
                                      ArrayList<String> listSelectedStoreID, ArrayList<String> listSelectedStoreOrigin) {

        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
        if (null != recFragment) {
            recFragment.selectedStoreMultiple(optId, optionVal,
                    txtVw, listViewOption, tagVal,
                    dialog, textView, ll_SlctdOpt,
                    listSelectedOpt,
                    listSelectedStoreID, listSelectedStoreOrigin);
        }

    }

    public void fnGettingGSTOFflineVal() {
        // Start for getting GST Offline

        String OutletID = "0", QuestID = "0", AnswerType, AnswerValue = "";
        int sectionID = 0;
        int QuestionGroupID = 0;


        for (Entry<String, String> entry : hmapStoreQuestAnsNew.entrySet()) {
            String questId = entry.getKey().split(Pattern.quote("^"))[0].toString();
            AnswerType = entry.getKey().split(Pattern.quote("^"))[1].toString();
            QuestionGroupID = Integer.valueOf(entry.getKey().split(Pattern.quote("^"))[2].toString());
            AnswerValue = entry.getValue();

            String optionValue = "0";

            if (questId.equals("49")) {
                try {
                    //flgGSTCompliance=helperDb.fnGetGstOptionIDComplianceWhileAddingNewStore(""+AnswerValue);
                    String OptionDescr = dbengine.fnGetOptionDescrFromtblGetPDAQuestOptionMstr(questId, "" + AnswerValue);
                    if (OptionDescr.equals("Yes")) {
                        flgGSTCompliance = "1";
                    } else if (OptionDescr.equals("Not Required")) {
                        flgGSTCompliance = "0";
                    } else if (OptionDescr.equals("Pending")) {
                        flgGSTCompliance = "2";
                    }

                } catch (Exception e) {

                }
            }
            if (questId.equals("50")) {
                if (!AnswerValue.equals("")) {
                    GSTNumber = AnswerValue;
                }
            }


        }

        // End for gst getting
    }

    public String getAddressNewWay(String ZeroIndexAddress, String city, String State, String pincode, String country) {
        String editedAddress = ZeroIndexAddress;
        if (editedAddress.contains(city)) {
            editedAddress = editedAddress.replace(city, "");

        }
        if (editedAddress.contains(State)) {
            editedAddress = editedAddress.replace(State, "");

        }
        if (editedAddress.contains(pincode)) {
            editedAddress = editedAddress.replace(pincode, "");

        }
        if (editedAddress.contains(country)) {
            editedAddress = editedAddress.replace(country, "");

        }
        if (editedAddress.contains(",")) {
            editedAddress = editedAddress.replace(",", " ");

        }

        return editedAddress;
    }

    @Override
    public void selectedCityState(String selectedCategory, Dialog dialog, int flgCityState) {
        NewStoreFormFeedBack recFragment = (NewStoreFormFeedBack) getFragmentManager().findFragmentByTag("NewStoreFragmentFeedBack");
        if (null != recFragment) {
            recFragment.selectedCityState(selectedCategory, dialog, flgCityState);
        }
    }

    private class FullSyncDataNow extends AsyncTask<Void, Void, Void> {


        ProgressDialog pDialogGetStores;

        public FullSyncDataNow(DynamicQuestionsFragment activity) {
            pDialogGetStores = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


            pDialogGetStores.setTitle(getText(R.string.genTermPleaseWaitNew));
            pDialogGetStores.setMessage("Submitting Details");
            pDialogGetStores.setIndeterminate(false);
            pDialogGetStores.setCancelable(false);
            pDialogGetStores.setCanceledOnTouchOutside(false);
            pDialogGetStores.show();


        }

        @Override

        protected Void doInBackground(Void... params) {

            int Outstat = 1;

            dbengine.UpdateStoreVisitWiseTablesForDyanamicFeedback(selStoreID, 3, StoreVisitCode,"NA", JOINTVISITID, mFeedbackType);
            if(mFeedbackType==2)
            {

                long syncTIMESTAMP = System.currentTimeMillis();
                Date dateobj = new Date(syncTIMESTAMP);
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
                String clkdTime = df.format(dateobj);

                String SONodeIdAndNodeType = mDataSource.fnGetPersonNodeIDAndPersonNodeTypeForSO();

                int PersonNodeID = Integer.parseInt(SONodeIdAndNodeType.split(Pattern.quote("^"))[0]);
                int PersonNodeType = Integer.parseInt(SONodeIdAndNodeType.split(Pattern.quote("^"))[1]);
                dbengine.insertImagesAgainstStoretblVisitJointSignature("0",clkdTime,pic_nameASMSign,ImgPathASMSign,3,1,1,StoreVisitCode,JOINTVISITID,mFeedbackType,PersonNodeID,PersonNodeType);

                int PersonNodeIdSalesMan = 0;
                int PersonNodeTypeSalesMan = 0;
                SharedPreferences sharedPref = getSharedPreferences(CommonInfo.Preference, MODE_PRIVATE);
                if (sharedPref != null && sharedPref.contains("SalesmanNodeId")) {
                     PersonNodeIdSalesMan = sharedPref.getInt("SalesmanNodeId", 0);
                     PersonNodeTypeSalesMan = sharedPref.getInt("SalesmanNodeType", 0);
                }
                dbengine.insertImagesAgainstStoretblVisitJointSignature("0",clkdTime,pic_nameTSISign,ImgPathTSISign,3,2,2,StoreVisitCode,JOINTVISITID,mFeedbackType,PersonNodeIdSalesMan,PersonNodeTypeSalesMan);


            }

            long syncTIMESTAMP = System.currentTimeMillis();
            Date dateobj = new Date(syncTIMESTAMP);
            SimpleDateFormat df1 = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss", Locale.ENGLISH);

            newfullFileName = AppUtils.getToken(DynamicQuestionsFragment.this) + "." + df1.format(dateobj);


            try {


                File OrderXMLFolder = new File(Environment.getExternalStorageDirectory(), CommonInfo.OrderXMLFolder);

                if (!OrderXMLFolder.exists()) {
                    OrderXMLFolder.mkdirs();

                }




               // dbengine.UpdateStoreVisitWiseTables(selStoreID, 5, StoreVisitCode, JOINTVISITID, mFeedbackType);
            } catch (Exception e) {

                e.printStackTrace();
                if (pDialogGetStores.isShowing()) {
                    pDialogGetStores.dismiss();
                }
            }
            return null;
        }

        @Override
        protected void onCancelled() {

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialogGetStores.isShowing()) {
                pDialogGetStores.dismiss();
            }
            try {

                String presentRoute = mDataSource.GetActiveRouteID(CommonInfo.CoverageAreaNodeID, CommonInfo.CoverageAreaNodeType);


                Intent mMyServiceIntent = new Intent(DynamicQuestionsFragment.this, SyncJobService.class);  //is any of that needed?  idk.
                mMyServiceIntent.putExtra("whereTo", "Regular");//
                mMyServiceIntent.putExtra("storeID", selStoreID);
                mMyServiceIntent.putExtra("routeID", presentRoute);//
                mMyServiceIntent.putExtra("VisitJointID", JOINTVISITID);//

                SyncJobService.enqueueWork(DynamicQuestionsFragment.this, mMyServiceIntent);
                if (mFeedbackType == 0) {
                   /* // ((MarketVisitActivity) mActivity).clearFragmentStack(2);
                    ((MarketVisitActivity) mActivity).loadFragment(null, MarketVisitActivity.STOREVISIT);*/

                    /*Intent ide = new Intent(DynamicQuestionsFragment.this, StoreSelection.class);
                    ide.putExtra("userDate", date_value);
                    ide.putExtra("pickerDate", pickerDate);
                    ide.putExtra("imei", imei);
                    ide.putExtra("rID", rID);
                    ide.putExtra("JOINTVISITID", StoreSelection.JointVisitId);
                    ide.putExtra(AppUtils.FLGJOINTVISIT, flgJointVisit);
                    ide.putExtra(AppUtils.VisitType, VisitType);
                    startActivity(ide);
                    finish();*/

                    Bundle args = new Bundle();
                    args.putString(AppUtils.StoreName, StoreNameFromBack);
                    args.putString(AppUtils.StoreId, selStoreID);
                    args.putString(AppUtils.JOINTVISITID, StoreSelection.JointVisitId);
                    args.putInt(AppUtils.FLGJOINTVISIT, flgJointVisit);
                    args.putString("activityFrom", "STORELIST");
                    args.putString("CurrntRouteName", "XYZ");
                    args.putInt(AppUtils.FeedbackType, 1);
                    args.putInt(AppUtils.IS_COMPETITOR_AVAILABLE, 0);
                    args.putInt(AppUtils.IS_STOCK_AVAILABLE, 0);
                    args.putInt(AppUtils.PTRPTC_MARKED, 0);
                    args.putInt(AppUtils.VisitType, VisitType);

                    Intent mainIntent = new Intent(DynamicQuestionsFragment.this,DynamicQuestionsFragment.class);
                    mainIntent.putExtras(args);
                    startActivity(mainIntent);
                    finish();

                }
                if (mFeedbackType ==1) {  //&& flgCompetitorDataRequired == 2
                    //  ((MarketVisitActivity) mActivity).clearFragmentStack(2);
                   // ((MarketVisitActivity) mActivity).loadFragment(null, MarketVisitActivity.STOREVISIT);
                    Intent ide = new Intent(DynamicQuestionsFragment.this, StoreSelection.class);
                    ide.putExtra("userDate", date_value);
                    ide.putExtra("pickerDate", pickerDate);
                    ide.putExtra("imei", imei);
                    ide.putExtra("rID", rID);
                    ide.putExtra("JOINTVISITID", StoreSelection.JointVisitId);
                    ide.putExtra(AppUtils.FLGJOINTVISIT, flgJointVisit);
                    ide.putExtra(AppUtils.VisitType, VisitType);
                    startActivity(ide);
                    finish();

                }
                if (mFeedbackType == 2) {
                   /*finish();*/
                    Intent intent = new Intent(DynamicQuestionsFragment.this, AllButtonActivity.class);
                    startActivity(intent);
                    finish();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
    public void signatureAllCodeASM() {
        file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }
        mContentASM = (LinearLayout) findViewById(R.id.linearLayoutASMSign);
        mSignature = new signatureASM(getApplicationContext(), null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        mContentASM.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getsignASM = (Button) findViewById(R.id.getsignASM);
        //by default disable it when , person do sign it will enable
        getsignASM.setEnabled(false);
        clearASMSign = (Button) findViewById(R.id.clearASMSign);
        cancelASMSign = (Button) findViewById(R.id.cancelASMSign);
        viewASM = mContentASM;

        clearASMSign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Cleared");
                mSignature.clear();
                getsignASM.setEnabled(false);
                signOrNotASM = false;
            }
        });

        getsignASM.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Log.v("log_tag", "Panel Saved");
                viewASM.setDrawingCacheEnabled(true);
                mSignature.save(viewASM, ImgPathASMSign);

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.txtSuccessfullySaved), Toast.LENGTH_SHORT).show();
                // Calling the same class
                //  recreate();
            }
        });

        cancelASMSign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Canceled");

                // Calling the same class
                //   recreate();
            }
        });
        transparent_imageASM = (ImageView) findViewById(R.id.transparent_imageASM);
       // scrollViewParentOfMap = (ScrollView) findViewById(R.id.scrollViewParentOfMap);

        transparent_imageASM.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        svItems.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        svItems.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        svItems.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });
    }
    public void signatureAllCodeTSI() {
        file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }
        mContentTSI = (LinearLayout) findViewById(R.id.linearLayoutTSISign);
        mSignatureTSI = new signatureTSI(getApplicationContext(), null);
        mSignatureTSI.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        mContentTSI.addView(mSignatureTSI, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getsignTSI = (Button) findViewById(R.id.getsignTSI);
        //by default disable it when , person do sign it will enable
        getsignTSI.setEnabled(false);
        clearTSISign = (Button) findViewById(R.id.clearTSISign);
        cancelTSISign = (Button) findViewById(R.id.cancelTSISign);
        viewTSI = mContentTSI;

        clearTSISign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Cleared");
                mSignatureTSI.clear();
                getsignTSI.setEnabled(false);
                signOrNotTSI = false;
            }
        });

        getsignTSI.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Log.v("log_tag", "Panel Saved");
                viewTSI.setDrawingCacheEnabled(true);
                mSignatureTSI.save(viewTSI, ImgPathTSISign);

                Toast.makeText(getApplicationContext(), getResources().getString(R.string.txtSuccessfullySaved), Toast.LENGTH_SHORT).show();
                // Calling the same class
                //  recreate();
            }
        });

        cancelTSISign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("log_tag", "Panel Canceled");

                // Calling the same class
                //   recreate();
            }
        });
        transparent_imageTSI = (ImageView) findViewById(R.id.transparent_imageTSI);
        //scrollViewParentOfMap = (ScrollView) findViewById(R.id.scrollViewParentOfMap);

        transparent_imageTSI.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        svItems.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        svItems.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        svItems.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });
    }
    public class signatureASM extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signatureASM(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());

            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(mContentASM.getWidth(), mContentASM.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(StoredPath);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();

            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }

        }

        public void clear() {
            path.reset();
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            getsignASM.setEnabled(true);
            signOrNotASM = true;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    public class signatureTSI extends View {

        private static final float STROKE_WIDTH = 5f;
        private static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        private Paint paint = new Paint();
        private Path path = new Path();

        private float lastTouchX;
        private float lastTouchY;
        private final RectF dirtyRect = new RectF();

        public signatureTSI(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }

        public void save(View v, String StoredPath) {
            Log.v("log_tag", "Width: " + v.getWidth());
            Log.v("log_tag", "Height: " + v.getHeight());

            if (bitmapTSI == null) {
                bitmapTSI = Bitmap.createBitmap(mContentTSI.getWidth(), mContentTSI.getHeight(), Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmapTSI);
            try {
                // Output the file
                FileOutputStream mFileOutStream = new FileOutputStream(ImgPathTSISign);
                v.draw(canvas);

                // Convert the output file to Image such as .png
                bitmapTSI.compress(Bitmap.CompressFormat.PNG, 90, mFileOutStream);
                mFileOutStream.flush();
                mFileOutStream.close();

            } catch (Exception e) {
                Log.v("log_tag", e.toString());
            }

        }

        public void clear() {
            path.reset();
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawPath(path, paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            getsignTSI.setEnabled(true);
            signOrNotTSI = true;

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;

                case MotionEvent.ACTION_MOVE:

                case MotionEvent.ACTION_UP:

                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        expandDirtyRect(historicalX, historicalY);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;

                default:
                    debug("Ignored touch event: " + event.toString());
                    return false;
            }

            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));

            lastTouchX = eventX;
            lastTouchY = eventY;

            return true;
        }

        private void debug(String string) {

            Log.v("log_tag", string);

        }

        private void expandDirtyRect(float historicalX, float historicalY) {
            if (historicalX < dirtyRect.left) {
                dirtyRect.left = historicalX;
            } else if (historicalX > dirtyRect.right) {
                dirtyRect.right = historicalX;
            }

            if (historicalY < dirtyRect.top) {
                dirtyRect.top = historicalY;
            } else if (historicalY > dirtyRect.bottom) {
                dirtyRect.bottom = historicalY;
            }
        }

        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    public boolean fnValidateSignatures()
    {
        boolean flgSignaturePresent=true;

        if (!signOrNotASM) {
            showAlertForEveryOne(getResources().getString(R.string.txtValidateSignatureRequired) +" ASM");

            flgSignaturePresent= false;
        }
        if(flgSignaturePresent) {
            if (!signOrNotTSI) {
                showAlertForEveryOne(getResources().getString(R.string.txtValidateSignatureRequired) + " TSI");

                flgSignaturePresent = false;
            }
        }

        return flgSignaturePresent;
    }

    public void showAlertForEveryOne(String msg) {
        AlertDialog.Builder alertDialogNoConn = new AlertDialog.Builder(DynamicQuestionsFragment.this);
        alertDialogNoConn.setTitle(getResources().getString(R.string.genTermNoDataConnection));

        alertDialogNoConn.setMessage(msg);
        alertDialogNoConn.setCancelable(false);
        alertDialogNoConn.setNeutralButton(getResources().getString(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        alertDialogNoConn.setIcon(R.drawable.info_ico);
        AlertDialog alert = alertDialogNoConn.create();
        alert.show();
    }
}

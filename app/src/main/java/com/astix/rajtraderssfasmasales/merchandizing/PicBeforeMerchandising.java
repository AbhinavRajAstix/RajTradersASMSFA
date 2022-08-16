package com.astix.rajtraderssfasmasales.merchandizing;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.astix.Common.CommonInfo;
import com.astix.rajtraderssfasmasales.ActualVisitStock;
import com.astix.rajtraderssfasmasales.BaseActivity;
import com.astix.rajtraderssfasmasales.DeletePic;
import com.astix.rajtraderssfasmasales.ExpandableHeightGridView;

import com.astix.rajtraderssfasmasales.LastVisitDetails;
import com.astix.rajtraderssfasmasales.R;
import com.astix.rajtraderssfasmasales.StoreEditActivityContactLocation;
import com.astix.rajtraderssfasmasales.camera.CameraPreview;
import com.astix.rajtraderssfasmasales.database.AppDataSource;
import com.astix.rajtraderssfasmasales.location.LocationInterface;
import com.astix.rajtraderssfasmasales.location.LocationRetreivingGlobal;
import com.astix.rajtraderssfasmasales.sync.DatabaseAssistant;
import com.astix.rajtraderssfasmasales.utils.AppUtils;
import com.crashlytics.android.Crashlytics;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.astix.rajtraderssfasmasales.camera.CameraUtils.findFrontFacingCamera;
import static com.astix.rajtraderssfasmasales.camera.CameraUtils.hasCamera;


public class PicBeforeMerchandising extends BaseActivity implements LocationInterface, DeletePic {//, InterfaceContactUpdate
    View viewContactSection;
    LinearLayout ll_ContactUpdateSection;
    TextView tvContactNoCorrection;
    Button btnEditContact;
    ImageView ivBack;
    public int maxAllowedPhotos= CommonInfo.maxAllowedPhotos;
    int battLevel = 0;
    TextView txt_header;
    LocationRetreivingGlobal locationRetreivingGlobal;
    public String CustomerNodeType="180";
    String storeVisitCode = "";
   /* private TblSpokeProfile tblSpokeProfile;

    private TblLastCollections tblLastCollections;*/

    ProgressDialog pDialog2;
    public DatabaseAssistant DASFA = null;
    public int flgType = 0;
   public String storeID = "0";
    public String selStoreName;
    public String date;
    public String pickerDate;
    public String imei;

    public String clickedTagPhoto = "BeforeMerchandising";
    public String clickedTagSignPhoto = "SignBeforeMerchandising";
    public String StoreIDForImg = "0";
    public String selectedSpokeName = "NA";
public int flgStoreActivity=0;
    boolean flgListEmptyRack = false;
    boolean flgListEmptySign = false;
    String formattedDate = "";
    private int picAddPosition = 0;
    private int removePicPositin = 0;

    private float mDist = 0;
    private Activity mActivity;

public int cntFrontPhotoTaken=0;

    String LattitudeFromLauncher = "NA";
    String LongitudeFromLauncher = "NA";
    public String AccuracyFromLauncher = "NA";
    String FnlAddress = "NA", finalPinCode = "NA", finalCity = "NA", finalState = "NA", fnAccurateProvider = "NA", AllProvidersLocation = "NA", FusedAddress = "NA", GpsLat = "NA", GpsLong = "NA", GpsAccuracy = "NA", GpsAddress = "NA", NetwLat = "NA", NetwLong = "NA", NetwAccuracy = "NA", NetwAddress = "NA", FusedLat = "NA", FusedLong = "NA", FusedAccuracy = "NA", FusedLocationLatitudeWithFirstAttempt = "NA", FusedLocationLongitudeWithFirstAttempt = "NA", FusedLocationAccuracyWithFirstAttempt = "NA";
    int flgLocationServicesOnOff = 0, flgGPSOnOff = 0, flgNetworkOnOff = 0, flgFusedOnOff = 0, flgInternetOnOffWhileLocationTracking = 0, flgRestart = 0;


    private int flgIsPicsAllowed = 1;
    String storePics = "";
    @BindView(R.id.retailer_container)
    RelativeLayout retailer_container;

    @BindView(R.id.picsAllowedRG)
    RadioGroup picsAllowedRG;
    @BindView(R.id.commentsET)
    EditText commentsET;



    @BindView(R.id.bt_Next)
    Button bt_Next;
    @BindView(R.id.storeName_txt)
    TextView txt_storeName;
    @BindView(R.id.btn_clickPhoto)
    Button btn_clickPhoto;



    @BindView(R.id.btn_clickSignPhoto)
    Button btn_clickSignPhoto;

    @BindView(R.id.recycler_view_ClickedSignPhotos)
    ExpandableHeightGridView recycler_view_ClickedSignPhotos;

    @BindView(R.id.recycler_view_ClickedPhotos)
    ExpandableHeightGridView recycler_view_ClickedPhotos;

    ImageAdapter adapterImage = null;
    ImageAdapter adapterImage_sign = null;
    ArrayList<String> list_ImgName;
    ArrayList<String> list_SignImgName;
    AppDataSource mDataSource;

    private String imageName;
    private Uri uriSavedImage;
     private ImageView flashImage;
    private Button capture, cancelCam;
    private ArrayList<Object> arrImageData;
    private ArrayList<Object> arrSignImageData;

    private LinearLayout cameraPreview;
    private CameraPreview mPreview;
    private Camera mCamera;
    private Button switchCamera;
    private boolean isLighOn = false;
    private Camera.PictureCallback mPicture;
    private Dialog dialog;


    private final LinkedHashMap<String, Integer> hmapCtgry_Imageposition= new LinkedHashMap<>();
    private final LinkedHashMap<String, ArrayList<String>> hmapCtgryPhotoSection= new LinkedHashMap<>();
    private final LinkedHashMap<String, ImageAdapter> hmapImageAdapter = new LinkedHashMap<>();

    private final LinkedHashMap<String, Integer> hmapCtgrySign_Imageposition= new LinkedHashMap<>();
    private final LinkedHashMap<String, ArrayList<String>> hmapCtgrySignPhotoSection= new LinkedHashMap<>();
    private final LinkedHashMap<String, ImageAdapter> hmapSignImageAdapter = new LinkedHashMap<>();


    private final LinkedHashMap<String, Integer> hmapCtgryBeforeMerchan_Imageposition= new LinkedHashMap<>();
    private final LinkedHashMap<String, ArrayList<String>> hmapCtgryBeforeMerchanPhotoSection= new LinkedHashMap<>();
    private final LinkedHashMap<String, ImageAdapter> hmapBeforeMerchanImageAdapter = new LinkedHashMap<>();


    private final LinkedHashMap<String, String> hmapPhotoDetailsForSaving = new LinkedHashMap<>();


   /* List<TblCollectionListMstr> tblCollectionListMstrs;*/





    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mBatInfoReceiver);
        if (locationRetreivingGlobal != null) {
            locationRetreivingGlobal.stopAllProcesses();
        }
    }
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {

            battLevel = intent.getIntExtra("level", 0);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clck_pics);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        ButterKnife.bind(this);


        mDataSource = AppDataSource.getInstance(this);
        DASFA = new DatabaseAssistant(this);
        registerReceiver(this.mBatInfoReceiver,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        imei = AppUtils.getToken(this);
        mActivity = this;

        txt_header=findViewById(R.id.txt_header);
        txt_header.setText("Before Merchandising");

        ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fireBackDetPg=new Intent(PicBeforeMerchandising.this, LastVisitDetails.class);
                fireBackDetPg.putExtra("storeID", storeID);
                fireBackDetPg.putExtra("SN", selStoreName);
                fireBackDetPg.putExtra("bck", 1);
                fireBackDetPg.putExtra("imei", imei);
                fireBackDetPg.putExtra("userdate", date);
                fireBackDetPg.putExtra("pickerDate", pickerDate);
                fireBackDetPg.putExtra("flgOrderType", 1);
                //fireBackDetPg.putExtra("rID", routeID);
                startActivity(fireBackDetPg);
                finish();
            }
        });

        getIntentExtras();
        storeVisitCode = getStoreVisitCode();
        adapterImage = new ImageAdapter(this);

        adapterImage_sign = new ImageAdapter(this);

        arrImageData = new ArrayList<>();
        arrSignImageData = new ArrayList<>();
        storePics = mDataSource.fnGetStoreNoPicsFlag(storeVisitCode);

        recycler_view_ClickedPhotos.setAdapter(adapterImage);
        recycler_view_ClickedPhotos.setExpanded(true);
        hmapImageAdapter.put(clickedTagPhoto, adapterImage);


        recycler_view_ClickedSignPhotos.setAdapter(adapterImage_sign);
        recycler_view_ClickedSignPhotos.setExpanded(true);
        hmapSignImageAdapter.put(clickedTagSignPhoto,adapterImage_sign);


        initView();
        fetchPhotoDetails();
        clickImage();

        fetchSignPhotoDetails();
        clickSignImage();
        retailer_container.setVisibility(View.GONE);

     /*   tv_StoreLastVisitDate.setText(tblSpokeProfile.getLastVisitDate().trim());
        tv_Last_Order_Date.setText(tblSpokeProfile.getLastOrderDate().trim());

        tv_Last_Order_Value.setText(tblSpokeProfile.getLastOrderValue().trim());
        tblCollectionListMstrs= new ArrayList<>();
        tblCollectionListMstrs=mDataSource.fnGettblCollectionListMstrs();

        fnCreateCollectionList();*/
/*
        tv_StoreLastVisitDate.setText("25-Aug-2019");
        tv_Last_Order_Date.setText("25-Aug-2019");
        tv_Last_Order_Value.setText("50");*/
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent fireBackDetPg=new Intent(PicBeforeMerchandising.this, LastVisitDetails.class);
            fireBackDetPg.putExtra("storeID", storeID);
            fireBackDetPg.putExtra("SN", selStoreName);
            fireBackDetPg.putExtra("bck", 1);
            fireBackDetPg.putExtra("imei", imei);
            fireBackDetPg.putExtra("userdate", date);
            fireBackDetPg.putExtra("pickerDate", pickerDate);
            fireBackDetPg.putExtra("flgOrderType", 1);
            //fireBackDetPg.putExtra("rID", routeID);
            startActivity(fireBackDetPg);
            finish();
        }
        return true;
    }*/

    void clickImage() {
        /*String selectedTextID="00";

        selectedTextID=hmap_ReasonsDescID.get(drpdwn_closeReason.getText().toString());
        */
        if (list_ImgName == null || list_ImgName.size() == 0) {
            adapterImage = new ImageAdapter(PicBeforeMerchandising.this);
            recycler_view_ClickedPhotos.setAdapter(adapterImage);
            hmapImageAdapter.put(clickedTagPhoto, adapterImage);
        }
    }

    void clickSignImage() {
        /*String selectedTextID="00";

        selectedTextID=hmap_ReasonsDescID.get(drpdwn_closeReason.getText().toString());
        */
        if (list_SignImgName == null || list_SignImgName.size() == 0) {
            adapterImage_sign = new ImageAdapter(PicBeforeMerchandising.this);
            recycler_view_ClickedSignPhotos.setAdapter(adapterImage_sign);
            hmapSignImageAdapter.put(clickedTagSignPhoto, adapterImage_sign);
        }
    }



    void fetchPhotoDetails() {
        if (list_ImgName != null && list_ImgName.size() > 0) {
            adapterImage = new ImageAdapter(PicBeforeMerchandising.this);
           // clickedTagPhoto = "Rack";

            int picPosition = 0;
            for (int i = 0; i < list_ImgName.size(); i++) {
                String imgName = list_ImgName.get(i);
                String file_dj_path = Environment.getExternalStorageDirectory() + "/" + CommonInfo.ImagesFolder + "/" + imgName;
                File fImageShow = new File(file_dj_path);
                if (fImageShow.exists()) {
                    Bitmap bmp = decodeSampledBitmapFromFile(fImageShow.getAbsolutePath(), 80, 80);
                    adapterImage.add(picPosition, bmp, imgName + "^" + clickedTagPhoto);

                    hmapCtgry_Imageposition.put(clickedTagPhoto, picPosition);
                    cntFrontPhotoTaken=cntFrontPhotoTaken+1;

                    picPosition++;
                }
            }
            recycler_view_ClickedPhotos.setAdapter(adapterImage);

            hmapImageAdapter.put(clickedTagPhoto, adapterImage);


            hmapCtgryPhotoSection.put(clickedTagPhoto, list_ImgName);


        }



    }


    void fetchSignPhotoDetails() {
        if (list_SignImgName != null && list_SignImgName.size() > 0) {
            adapterImage_sign = new ImageAdapter(PicBeforeMerchandising.this);
            // clickedTagPhoto = "Rack";

            int picPosition = 0;
            for (int i = 0; i < list_SignImgName.size(); i++) {
                String imgName = list_SignImgName.get(i);
                String file_dj_path = Environment.getExternalStorageDirectory() + "/" + CommonInfo.ImagesFolder + "/" + imgName;
                File fImageShow = new File(file_dj_path);
                if (fImageShow.exists()) {
                    Bitmap bmp = decodeSampledBitmapFromFile(fImageShow.getAbsolutePath(), 80, 80);
                    adapterImage_sign.add(i, bmp, imgName + "^" + clickedTagSignPhoto);

                    hmapCtgrySign_Imageposition.put(clickedTagSignPhoto, picPosition);
                    picPosition++;
                }
            }
            recycler_view_ClickedSignPhotos.setAdapter(adapterImage_sign);

            hmapSignImageAdapter.put(clickedTagSignPhoto, adapterImage_sign);


            hmapCtgrySignPhotoSection.put(clickedTagSignPhoto, list_SignImgName);


        }



    }



    public void openCustomCamara(int flgWhichButtonCliked) {

        String section_name = "";
        int imageType = 0;
        int numImages =0 ;
        if(flgWhichButtonCliked == 1)
        {
            if(hmapCtgryPhotoSection!=null && hmapCtgryPhotoSection.size()!=0)
            {
                numImages = hmapCtgryPhotoSection.get(clickedTagPhoto).size();
                section_name = "Raj's shelves before merchandising Photos";
                imageType = 3;

            }
        }
       else if(flgWhichButtonCliked == 2)
        {
            if(hmapCtgrySignPhotoSection!=null && hmapCtgrySignPhotoSection.size()!=0)
            {
                numImages = hmapCtgrySignPhotoSection.get(clickedTagSignPhoto).size();
                section_name = "Sign Board Photos";
                imageType = 14;

            }
        }


       if(AppUtils.fnValidateMaxAllowedPhotosTakenCount(mActivity,imageType,flgWhichButtonCliked,numImages,maxAllowedPhotos)==true)
                        {
                            AppUtils.fnValidateMaxAllowedPhotosMsg(mActivity,section_name,imageType,maxAllowedPhotos);
                        }
                        else {
                            openCamera(flgWhichButtonCliked);//Second Parameter is CollectionID
                        }





    }
    private void releaseCameraAndPreview() {
        //cameraPreview = null;
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }
    private void openCamera(int flgWhichButtonCliked) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        if (flgWhichButtonCliked == 1) {//Rack
            arrImageData.clear();
        }

        else if(flgWhichButtonCliked == 2)
        {
            arrSignImageData.clear();
        }


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        dialog = new Dialog(PicBeforeMerchandising.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        //dialog.setTitle("Calculation");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_cameraview);
        WindowManager.LayoutParams parms = dialog.getWindow().getAttributes();

        parms.height = parms.MATCH_PARENT;
        parms.width = parms.MATCH_PARENT;
        cameraPreview = (LinearLayout) dialog.findViewById(R.id.camera_preview);
        switchCamera=(Button)dialog.findViewById(R.id.btSwitchCamera);
        if(mCamera!=null) {
            mPreview = new CameraPreview(PicBeforeMerchandising.this, mCamera);
            cameraPreview.addView(mPreview);
        }
        //onResume code
        if (!hasCamera(PicBeforeMerchandising.this)) {
            Toast toast = Toast.makeText(PicBeforeMerchandising.this, getText(R.string.txtNoCamera), Toast.LENGTH_LONG);
            toast.show();
        }
        if(mCamera!=null){
            mCamera.release();
            mCamera=null;
        }
        if (mCamera == null) {
            //if the front facing camera does not exist
            if (findFrontFacingCamera() < 0) {
                Toast.makeText(PicBeforeMerchandising.this, getText(R.string.txtNoFrontCamera), Toast.LENGTH_LONG).show();
                switchCamera.setVisibility(View.GONE);
            }

            //mCamera = Camera.open(findBackFacingCamera());


            mCamera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
			/*if(mCamera==null){
				mCamera=Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
			}*/

            mPreview = new CameraPreview(this, mCamera);
            cameraPreview.addView(mPreview);
            boolean isParameterSet = false;
            try {
                Camera.Parameters params = mCamera.getParameters();


                List<Camera.Size> sizes = params.getSupportedPictureSizes();
                Camera.Size size = sizes.get(0);
                //Camera.Size size1 = sizes.get(0);
                for (int i = 0; i < sizes.size(); i++) {

                    if (sizes.get(i).width > size.width)
                        size = sizes.get(i);
                }

                //System.out.println(size.width + "mm" + size.height);

                params.setPictureSize(size.width, size.height);
                params.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
                //	params.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
                params.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
                params.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);

                //	params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

                isLighOn = false;
                int minExpCom = params.getMinExposureCompensation();
                int maxExpCom = params.getMaxExposureCompensation();

                if (maxExpCom > 4 && minExpCom < 4) {
                    params.setExposureCompensation(4);
                } else {
                    params.setExposureCompensation(0);
                }

                params.setAutoExposureLock(false);
                params.setAutoWhiteBalanceLock(false);
                //String supportedIsoValues = params.get("iso-values");
                // String newVAlue = params.get("iso");
                //  params.set("iso","1600");
                params.setColorEffect("none");
                params.set("scene-mode", "auto");
                params.setPictureFormat(ImageFormat.JPEG);
                params.setJpegQuality(70);
                params.setRotation(90);

                mCamera.setParameters(params);
                isParameterSet = true;
            } catch (Exception e) {

            }
            if (!isParameterSet) {
                Camera.Parameters params2 = mCamera.getParameters();
                params2.setPictureFormat(ImageFormat.JPEG);
                params2.setJpegQuality(70);
                params2.setRotation(90);

                mCamera.setParameters(params2);
            }

            setCameraDisplayOrientation(PicBeforeMerchandising.this, Camera.CameraInfo.CAMERA_FACING_BACK, mCamera);
            mPicture = getPictureCallback(flgWhichButtonCliked);
            mPreview.refreshCamera(mCamera);
        }

        capture = (Button) dialog.findViewById(R.id.button_capture);

        flashImage = (ImageView) dialog.findViewById(R.id.flashImage);
        flashImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLighOn) {
                    // turn off flash
                    Camera.Parameters params = mCamera.getParameters();

                    if (mCamera == null || params == null) {
                        return;
                    }

                    params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(params);
                    flashImage.setImageResource(R.drawable.flash_off);
                    isLighOn = false;
                } else {
                    // turn on flash
                    Camera.Parameters params = mCamera.getParameters();

                    if (mCamera == null || params == null) {
                        return;
                    }

                    params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                    flashImage.setImageResource(R.drawable.flash_on);
                    mCamera.setParameters(params);

                    isLighOn = true;
                }
            }
        });

        final Button cancleCamera = (Button) dialog.findViewById(R.id.cancleCamera);
        cancelCam = cancleCamera;
        cancleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                v.setEnabled(false);
                capture.setEnabled(false);
                cameraPreview.setEnabled(false);
                flashImage.setEnabled(false);

                Camera.Parameters params = mCamera.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(params);
                isLighOn = false;
                releaseCameraAndPreview();
                dialog.dismiss();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        });

        capture.setOnClickListener(captrureListener);

        cameraPreview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Get the pointer ID
                Camera.Parameters params = mCamera.getParameters();
                int action = event.getAction();

                if (event.getPointerCount() > 1) {
                    // handle multi-touch events
                    if (action == MotionEvent.ACTION_POINTER_DOWN) {
                        mDist = getFingerSpacing(event);
                    } else if (action == MotionEvent.ACTION_MOVE
                            && params.isZoomSupported()) {
                        mCamera.cancelAutoFocus();
                        handleZoom(event, params);
                    }
                } else {
                    // handle single touch events
                    if (action == MotionEvent.ACTION_UP) {
                        handleFocus(event, params);
                    }
                }
                return true;
            }
        });

        dialog.show();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

    }

    private File getOutputMediaFile() {
        //make a new file directory inside the "sdcard" folder
        File mediaStorageDir = new File("/sdcard/", CommonInfo.ImagesFolder);

        //if this "JCGCamera folder does not exist
        if (!mediaStorageDir.exists()) {
            //if you cannot make this folder return
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }

        //take the current timeStamp
        String timeStamp = new SimpleDateFormat("yyyyMMMdd_HHmmss.SSS", Locale.ENGLISH).format(new Date());
        File mediaFile;
        //and make a media file:
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + AppUtils.getToken(PicBeforeMerchandising.this) + "IMG_" + timeStamp + ".jpg");

        return mediaFile;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float) height / (float) reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float) width / (float) reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }


    private Camera.PictureCallback getPictureCallback(final int flgWhichButtonCliked) {
        Camera.PictureCallback picture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                //make a new picture file
                File pictureFile = getOutputMediaFile();

                Camera.Parameters params = mCamera.getParameters();
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                mCamera.setParameters(params);
                isLighOn = false;

                if (pictureFile == null) {
                    return;
                }
                try {
                    //write the file
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                    if (flgWhichButtonCliked == 1) {
                        arrImageData.add(0, pictureFile);
                        arrImageData.add(1, pictureFile.getName());
                    }
                    else if(flgWhichButtonCliked == 2)
                    {
                        arrSignImageData.add(0, pictureFile);
                        arrSignImageData.add(1, pictureFile.getName());
                    }

                    dialog.dismiss();
                    if (pictureFile != null) {
                        File file = pictureFile;
                        System.out.println("File +++" + pictureFile);
                        imageName = pictureFile.getName();
                        Bitmap bmp = decodeSampledBitmapFromFile(file.getAbsolutePath(), 80, 80);

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        uriSavedImage = Uri.fromFile(pictureFile);
                        bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                        byte[] byteArray = stream.toByteArray();

                        // Convert ByteArray to Bitmap::\
                        //
                        long syncTIMESTAMP = System.currentTimeMillis();
                        Date dateobj = new Date(syncTIMESTAMP);
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
                        String clkdTime = df.format(dateobj);
                       if (flgWhichButtonCliked == 1) {
                            String valueOfKey = clickedTagPhoto + "~" + StoreIDForImg + "~" + uriSavedImage.toString() + "~" + clkdTime + "~" + "1";

                            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            setSavedImageToScrollView(bitmap, imageName, valueOfKey, clickedTagPhoto, flgWhichButtonCliked,3);
                            System.out.println("merch data..." + imageName + "~~" + valueOfKey + "~~" + clickedTagPhoto);
                        }

                        else if(flgWhichButtonCliked == 2)
                        {
                            String valueOfKey = clickedTagSignPhoto + "~" + StoreIDForImg + "~" + uriSavedImage.toString() + "~" + clkdTime + "~" + "1";

                            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            setSavedImageToScrollView(bitmap, imageName, valueOfKey, clickedTagSignPhoto, flgWhichButtonCliked,14);
                            System.out.println("merch data..." + imageName + "~~" + valueOfKey + "~~" + clickedTagSignPhoto);
                        }

                    }


                } catch (FileNotFoundException e) {
                    Crashlytics.logException(e);
                } catch (IOException e) {
                    e.printStackTrace();
                    Crashlytics.logException(e);
                }

                //refresh camera to continue preview--------------------------------------------------------------
                //	mPreview.refreshCamera(mCamera);
                //if want to release camera
                if (mCamera != null) {
                    mCamera.release();
                    mCamera = null;
                }
            }
        };
        return picture;
    }

    private void setSavedImageToScrollView(Bitmap bitmap, String imageName, String valueOfKey, String clickedTagPhotonew, int flgWhichButtonCliked, int imageType) {

        if (flgWhichButtonCliked == 1) {//Rack
            if (hmapCtgry_Imageposition != null && hmapCtgry_Imageposition.size() > 0) {
                if (hmapCtgry_Imageposition.containsKey(clickedTagPhotonew)) {
                    picAddPosition = hmapCtgry_Imageposition.get(clickedTagPhotonew);
                    if (picAddPosition == -1) {
                        flgListEmptyRack = false;
                        picAddPosition = 0;
                    }
                } else {
                    picAddPosition = 0;
                }
            } else {
                picAddPosition = 0;
            }

            removePicPositin = picAddPosition;
            ArrayList<String> listClkdPic = new ArrayList<String>();
            if (hmapCtgryPhotoSection != null && hmapCtgryPhotoSection.containsKey(clickedTagPhotonew)) {
                listClkdPic = hmapCtgryPhotoSection.get(clickedTagPhotonew);
            }

            listClkdPic.add(imageName);
            hmapCtgryPhotoSection.put(clickedTagPhotonew, listClkdPic);
            ImageAdapter adapterImage = hmapImageAdapter.get(clickedTagPhotonew);
            adapterImage.add(picAddPosition, bitmap, imageName + "^" + clickedTagPhotonew);
            System.out.println("REMOVE AND PIC ADD..." + removePicPositin + "__" + picAddPosition);
            System.out.println("Picture Adapter" + picAddPosition);
            picAddPosition++;
            hmapCtgry_Imageposition.put(clickedTagPhotonew, picAddPosition);

            String photoPath = valueOfKey.split(Pattern.quote("~"))[2];
            String clickedDateTime = valueOfKey.split(Pattern.quote("~"))[3];

            //key- imagName
            //value- businessId^CatID^TypeID^PhotoPath^ClikcedDatetime^PhotoTypeFlag^StackNo
            cntFrontPhotoTaken=cntFrontPhotoTaken+1;
            hmapPhotoDetailsForSaving.put(imageName, clickedTagPhotonew + "~" + photoPath + "~" + clickedDateTime + "~" + imageType);
            System.out.println("Hmap Photo..." + imageName + "^" + clickedTagPhotonew + "^" + photoPath + "^" + clickedDateTime);
        }

        else if(flgWhichButtonCliked==2)
        {
            if (hmapCtgrySign_Imageposition != null && hmapCtgrySign_Imageposition.size() > 0) {
                if (hmapCtgrySign_Imageposition.containsKey(clickedTagPhotonew)) {
                    picAddPosition = hmapCtgrySign_Imageposition.get(clickedTagPhotonew);
                    if (picAddPosition == -1) {
                        flgListEmptySign = false;
                        picAddPosition = 0;
                    }
                } else {
                    picAddPosition = 0;
                }
            } else {
                picAddPosition = 0;
            }

            removePicPositin = picAddPosition;
            ArrayList<String> listClkdPic = new ArrayList<String>();
            if (hmapCtgrySignPhotoSection != null && hmapCtgrySignPhotoSection.containsKey(clickedTagPhotonew)) {
                listClkdPic = hmapCtgrySignPhotoSection.get(clickedTagPhotonew);
            }

            listClkdPic.add(imageName);
            hmapCtgrySignPhotoSection.put(clickedTagPhotonew, listClkdPic);
            ImageAdapter adapterImage = hmapSignImageAdapter.get(clickedTagPhotonew);
            adapterImage.add(picAddPosition, bitmap, imageName + "^" + clickedTagPhotonew);
            System.out.println("REMOVE AND PIC ADD..." + removePicPositin + "__" + picAddPosition);
            System.out.println("Picture Adapter" + picAddPosition);
            picAddPosition++;
            hmapCtgrySign_Imageposition.put(clickedTagPhotonew, picAddPosition);

            String photoPath = valueOfKey.split(Pattern.quote("~"))[2];
            String clickedDateTime = valueOfKey.split(Pattern.quote("~"))[3];

            //key- imagName
            //value- businessId^CatID^TypeID^PhotoPath^ClikcedDatetime^PhotoTypeFlag^StackNo

            hmapPhotoDetailsForSaving.put(imageName, clickedTagPhotonew + "~" + photoPath + "~" + clickedDateTime + "~" + imageType);
            System.out.println("Hmap Photo..." + imageName + "^" + clickedTagPhotonew + "^" + photoPath + "^" + clickedDateTime);
        }

    }

    private void handleZoom(MotionEvent event, Camera.Parameters params) {
        int maxZoom = params.getMaxZoom();
        int zoom = params.getZoom();
        float newDist = getFingerSpacing(event);
        if (newDist > mDist) {
            // zoom in
            if (zoom < maxZoom)
                zoom++;
        } else if (newDist < mDist) {
            // zoom out
            if (zoom > 0)
                zoom--;
        }
        mDist = newDist;
        params.setZoom(zoom);
        mCamera.setParameters(params);
    }

    public void handleFocus(MotionEvent event, Camera.Parameters params) {
        int pointerId = event.getPointerId(0);
        int pointerIndex = event.findPointerIndex(pointerId);
        // Get the pointer's current position
        float x = event.getX(pointerIndex);
        float y = event.getY(pointerIndex);

        List<String> supportedFocusModes = params.getSupportedFocusModes();
        if (supportedFocusModes != null
                && supportedFocusModes
                .contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean b, Camera camera) {
                    // currently set to auto-focus on single touch
                }
            });
        }
    }

    View.OnClickListener captrureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setEnabled(false);
            mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            cancelCam.setEnabled(false);
            flashImage.setEnabled(false);
            if (cameraPreview != null) {
                cameraPreview.setEnabled(false);
            }

            if (mCamera != null) {
                mCamera.takePicture(null, null, mPicture);
            } else {
                dialog.dismiss();
            }
            mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    };

    private float getFingerSpacing(MotionEvent event) {
        // ...
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        Camera.CameraInfo info =
                new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }


    @OnClick(R.id.btn_clickPhoto)
    public void onCameraRackClick() {

        // clickedTagPhotoRack="Gowdown";
        /*if(hmapCtgryPhotoSectionRack.get("Store").size()<4)
            openCustomCamara(1);*/
        //if(hmapCtgryPhotoSection.size()==0)



            openCustomCamara(1);
       /* else if(  hmapCtgryPhotoSection.get(clickedTagPhoto).size()<2)
        {
            openCustomCamara(1);
        }
        else
        {
            AppUtils.fnValidateMaxAllowedPhotos(mActivity);
        }*/
        // openCustomCamara(1);
    }

    @OnClick(R.id.btn_clickSignPhoto)
    public void onCameraSignClick() {

        // clickedTagPhotoRack="Gowdown";
        /*if(hmapCtgryPhotoSectionRack.get("Store").size()<4)
            openCustomCamara(1);*/
       // if(hmapCtgrySignPhotoSection.size()==0)
            openCustomCamara(2);

        // openCustomCamara(1);
    }



    @Override
    protected void onResume() {
        super.onResume();
        if(StoreEditActivityContactLocation.flgCallActivityContactLocation==1)
        {
            inflateUpdateContactSection();
            StoreEditActivityContactLocation.flgCallActivityContactLocation=0;
        }
    }

    void inflateUpdateContactSectionLayout()
    {
        viewContactSection=getLayoutInflater().inflate(R.layout.layout_update_contact_frame, null);
        tvContactNoCorrection= (TextView) viewContactSection.findViewById(R.id.tvContactNoCorrection);
        btnEditContact= (Button) viewContactSection.findViewById(R.id.btnEditContact);
        ll_ContactUpdateSection.addView(viewContactSection);
    }
    void inflateUpdateContactSection()
    {
        if(mDataSource.fnCheckStoreIntblStoreListForUpdateMstr(storeID)==1)
        {
            viewContactSection.setVisibility(View.VISIBLE);
            if(mDataSource.fnCheckStoreMappedInStoreListForUpdateMstr(storeID)==1)
            {
                viewContactSection.setVisibility(View.GONE);
                tvContactNoCorrection.setText("Contact Number Verified");
                tvContactNoCorrection.setTextColor(Color.parseColor("#74C543"));
            }

            btnEditContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ready4GetLoc = new Intent(PicBeforeMerchandising.this, StoreEditActivityContactLocation.class);
                    ready4GetLoc.putExtra("storeID", storeID);
                    startActivity(ready4GetLoc);
                    //finish();
                }
            });
        }
        else
        {
            viewContactSection.setVisibility(View.GONE);
        }


    }


    public void initView() {

        ll_ContactUpdateSection=findViewById(R.id.ll_ContactUpdateSection);
        inflateUpdateContactSectionLayout();
        inflateUpdateContactSection();

        if (!storePics.split(Pattern.quote("^"))[1].equals("NA")) {
            commentsET.setText(storePics.split(Pattern.quote("^"))[1]);
        }

        flgIsPicsAllowed = Integer.parseInt(storePics.split(Pattern.quote("^"))[0]);
        if (flgIsPicsAllowed == 1) {
            picsAllowedRG.check(R.id.yesRB);
            commentsET.setVisibility(View.GONE);
            recycler_view_ClickedSignPhotos.setVisibility(View.VISIBLE);
            btn_clickPhoto.setEnabled(true);
            //makeViewsMandate();
        } else {
            picsAllowedRG.check(R.id.noRB);
            commentsET.setVisibility(View.VISIBLE);
            recycler_view_ClickedSignPhotos.setVisibility(View.GONE);
            btn_clickPhoto.setEnabled(false);
           // makeViewsMandate();
        }

        picsAllowedRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.yesRB) {
                    flgIsPicsAllowed = 1;
                    commentsET.setVisibility(View.GONE);
                    recycler_view_ClickedSignPhotos.setVisibility(View.VISIBLE);
                    btn_clickPhoto.setEnabled(true);
                   // makeViewsMandate();
                } else {
                    flgIsPicsAllowed = 0;
                    commentsET.setVisibility(View.VISIBLE);
                    recycler_view_ClickedSignPhotos.setVisibility(View.VISIBLE);
                    btn_clickPhoto.setEnabled(false);
                   // makeViewsMandate();
                }
            }
        });

        list_ImgName = mDataSource.getImagesAgainstStore(storeID,  flgType, 3);
        if (list_ImgName != null && list_ImgName.size() > 0) {
            adapterImage = new ImageAdapter(PicBeforeMerchandising.this);
            //expandableHeightGridView.setAdapter(adapterImage);
            clickedTagPhoto = "BeforeMerchandising";
            hmapImageAdapter.put(clickedTagPhoto, adapterImage);
            fetchPhotoDetails();
        }

        list_SignImgName = mDataSource.getImagesAgainstStore(storeID,  flgType, 14);
        if (list_SignImgName != null && list_SignImgName.size() > 0) {
            adapterImage_sign = new ImageAdapter(PicBeforeMerchandising.this);
            //expandableHeightGridView.setAdapter(adapterImage);
            clickedTagSignPhoto = "SignBeforeMerchandising";
            hmapSignImageAdapter.put(clickedTagSignPhoto, adapterImage_sign);
            fetchSignPhotoDetails();
        }
    }

    private void getIntentExtras() {
        Bundle bundle = getIntent().getExtras();


        selStoreName = bundle.getString("SN");
        storeID = bundle.getString("storeID");
        imei = bundle.getString("imei");
        date = bundle.getString("userdate");
        pickerDate= bundle.getString("pickerDate");
        txt_storeName.setText("Abhinav Sir");

            flgType=2;

    }



    public String getStoreVisitCode() {

        String IMEINo = AppUtils.getToken(this);

        storeVisitCode = mDataSource.fnGetStoreVisitCode( storeID);
        if (storeVisitCode.equals("NA")) {
            storeVisitCode = genSpokeVisitCode();
            String Comments = "NA";
           /* locationRetreivingGlobal = new LocationRetreivingGlobal();
            locationRetreivingGlobal.locationRetrievingAndDistanceCalculating(PicBeforeMerchandising.this, true, true, 20, 0);
*/

        }


        return storeVisitCode;
    }
    @Override
    public void onLocationRetrieved(String fnLati, String fnLongi, String finalAccuracy, String fnAccurateProvider,
                                    String GpsLat, String GpsLong, String GpsAccuracy, String NetwLat, String NetwLong,
                                    String NetwAccuracy, String FusedLat, String FusedLong, String FusedAccuracy,
                                    String AllProvidersLocation, String GpsAddress, String NetwAddress, String FusedAddress,
                                    String FusedLocationLatitudeWithFirstAttempt,
                                    String FusedLocationLongitudeWithFirstAttempt,
                                    String FusedLocationAccuracyWithFirstAttempt, int flgLocationServicesOnOff,
                                    int flgGPSOnOff, int flgNetworkOnOff, int flgFusedOnOff,
                                    int flgInternetOnOffWhileLocationTracking, String address, String pincode,
                                    String city, String state, String fnAddress) {
        LattitudeFromLauncher = String.valueOf(fnLati);
        LongitudeFromLauncher = String.valueOf(fnLongi);
        AccuracyFromLauncher = String.valueOf(finalAccuracy);

        this.FnlAddress = fnAddress;
        this.finalPinCode = pincode;
        this.finalCity = city;
        this.finalState = state;
        this.fnAccurateProvider = fnAccurateProvider;
        this.AllProvidersLocation = AllProvidersLocation;
        this.FusedAddress = FusedAddress;
        this.GpsLat = GpsLat;
        this.GpsLong = GpsLong;
        this.GpsAccuracy = GpsAccuracy;
        this.GpsAddress = GpsAddress;
        this.NetwLat = NetwLat;
        this.NetwLong = NetwLong;
        this.NetwAccuracy = NetwAccuracy;
        this.NetwAddress = NetwAddress;
        this.FusedLat = FusedLat;
        this.FusedLong = FusedLong;
        this.FusedAccuracy = FusedAccuracy;
        this.FusedLocationLatitudeWithFirstAttempt = FusedLocationLatitudeWithFirstAttempt;
        this.FusedLocationLongitudeWithFirstAttempt = FusedLocationLongitudeWithFirstAttempt;
        this.FusedLocationAccuracyWithFirstAttempt = FusedLocationAccuracyWithFirstAttempt;
        this.flgLocationServicesOnOff = flgLocationServicesOnOff;
        this.flgGPSOnOff = flgGPSOnOff;
        this.flgNetworkOnOff = flgNetworkOnOff;
        this.flgFusedOnOff = flgFusedOnOff;
        this.flgInternetOnOffWhileLocationTracking = flgInternetOnOffWhileLocationTracking;
        String Comments="NA";
     //   mDataSource.fnInsertOrUpdate_tblStoreVisitMstr(AppUtils.getIMEI(this), storeVisitCode, "" + Spoke_ID, 1, TimeUtils.getNetworkDate(this, TimeUtils.DATE_FORMAT), LattitudeFromLauncher, LongitudeFromLauncher, "NA", TimeUtils.getNetworkDateTime(this, "dd-MMM-yyyy HH:mm:ss.SSS"), flgLocationServicesOnOff, flgGPSOnOff, flgNetworkOnOff, flgFusedOnOff, flgInternetOnOffWhileLocationTracking, battLevel, CustomerNodeType, "NA", Comments);





        //fnCreateLastKnownFinalLocation(String.valueOf(fnLati), String.valueOf(fnLongi), String.valueOf(finalAccuracy));

//            storeVisitCode = getSpokeVisitCode();


    }
    public String genSpokeVisitCode() {
        long syncTIMESTAMP = System.currentTimeMillis();
        Date dateobj = new Date(syncTIMESTAMP);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
        String VisitStartTS = df.format(dateobj);
        String cxz;
        cxz = UUID.randomUUID().toString();


        StringTokenizer tokens = new StringTokenizer(String.valueOf(cxz), "-");

        String val1 = tokens.nextToken().trim();
        String val2 = tokens.nextToken().trim();
        String val3 = tokens.nextToken().trim();
        String val4 = tokens.nextToken().trim();
        cxz = tokens.nextToken().trim();

        //String IMEIid = CommonInfo.imei.substring(9);
        cxz = "StoreVisitCode" + "-" + cxz + "-" + VisitStartTS.replace(" ", "").replace(":", "").trim();


        return cxz;

    }


    public void fnCallSavingDatails() {
        // storeVisitCode=getStoreVisitCode();

        fnSaveImagesInDatabase();
        //   mDataSource.insertIntoTblCompetitorDetails(competitorData);


    }

    @OnClick(R.id.bt_Next)
    public void saveData(View view) {
     /*   if (flgIsPicsAllowed == 0 && TextUtils.isEmpty(commentsET.getText().toString().trim())){
            showAlertSingleButtonInfo("Please fill Comment");
            return;
    }

    else  */  if (fnValidatImageRackPhotoRakenorNot() == false) {
            showAlertSingleButtonInfo("Click the Before Merchandising Photo");
            return;
        }

      /*  Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);
        formattedDate=dateFormat.format(date);
        mDataSource.insertintotblLastPDAActivityDeatils("Store "+ "("+tblSpokeProfile.getSpokename()+ ")" +" " +" Visited at",formattedDate,"3",tblSpokeProfile.getSpokeNodeID());
*/
        fnCallSavingDatails();


    }


    @Override
    public void getProductPhotoDetail(String productIdTag) {

    }

    @Override
    public void delPic(Bitmap bmp, String imageNameToDel) {
        String imageNameToDelVal = imageNameToDel.split(Pattern.quote("^"))[0];
        String tagPhoto = imageNameToDel.split(Pattern.quote("^"))[1];
        if (hmapCtgry_Imageposition.containsKey(tagPhoto))
            picAddPosition = hmapCtgry_Imageposition.get(tagPhoto);
        else if(hmapCtgrySign_Imageposition.containsKey(tagPhoto))
            picAddPosition = hmapCtgrySign_Imageposition.get(tagPhoto);


        if (picAddPosition > 1) {
            removePicPositin = picAddPosition - 1;
        } else {
            removePicPositin = picAddPosition;
        }

        removePicPositin = removePicPositin - 1;
        picAddPosition = picAddPosition - 1;
        System.out.println("REMOVE AND PIC ADD DEL..." + removePicPositin + "__" + picAddPosition);

        if (hmapCtgry_Imageposition.containsKey(tagPhoto))
            hmapCtgry_Imageposition.put(tagPhoto, picAddPosition);

      else  if (hmapCtgrySign_Imageposition.containsKey(tagPhoto))
            hmapCtgrySign_Imageposition.put(tagPhoto, picAddPosition);


        ArrayList<String> listClkdPic = new ArrayList<String>();
        if (hmapCtgryPhotoSection != null && hmapCtgryPhotoSection.containsKey(tagPhoto)) {
            listClkdPic = hmapCtgryPhotoSection.get(tagPhoto);
            if (listClkdPic.contains(imageNameToDelVal)) {
                listClkdPic.remove(imageNameToDelVal);
                ImageAdapter adapterImage = hmapImageAdapter.get(tagPhoto);
                adapterImage.remove(bmp);
                hmapCtgryPhotoSection.put(tagPhoto, listClkdPic);
                if (listClkdPic.size() < 1) {
                    hmapCtgryPhotoSection.remove(tagPhoto);
                }
            }
            if(cntFrontPhotoTaken>0)
                cntFrontPhotoTaken=cntFrontPhotoTaken-1;
            if (listClkdPic.size() == 0) {
                flgListEmptyRack = true;
            }
        }

       else if (hmapCtgrySignPhotoSection != null && hmapCtgrySignPhotoSection.containsKey(tagPhoto)) {
            listClkdPic = hmapCtgrySignPhotoSection.get(tagPhoto);
            if (listClkdPic.contains(imageNameToDelVal)) {
                listClkdPic.remove(imageNameToDelVal);
                ImageAdapter adapterImage = hmapSignImageAdapter.get(tagPhoto);
                adapterImage.remove(bmp);
                hmapCtgrySignPhotoSection.put(tagPhoto, listClkdPic);
                if (listClkdPic.size() < 1) {
                    hmapCtgrySignPhotoSection.remove(tagPhoto);
                }
            }
            if (listClkdPic.size() == 0) {
                flgListEmptySign = true;
            }
        }




        if (hmapPhotoDetailsForSaving.containsKey(imageNameToDelVal)) {
            hmapPhotoDetailsForSaving.remove(imageNameToDelVal);
        }


        //  String file_dj_path = Environment.getExternalStorageDirectory() + "/RSPLSFAImages/"+imageNameToDel;
        String file_dj_path = Environment.getExternalStorageDirectory() + "/" + CommonInfo.ImagesFolder + "/" + imageNameToDelVal;
        mDataSource.validateAndDelPic(1, imageNameToDelVal, "0", StoreIDForImg);
        File fdelete = new File(file_dj_path);
        if (fdelete.exists()) {
            if (fdelete.delete()) {
                callBroadCast();
            } else {
            }
        }
    }

    private void callBroadCast() {
        if (Build.VERSION.SDK_INT >= 14) {
            Log.e("-->", " >= 14");
            MediaScannerConnection.scanFile(this, new String[]{Environment.getExternalStorageDirectory().toString()}, null, new MediaScannerConnection.OnScanCompletedListener() {

                public void onScanCompleted(String path, Uri uri) {

                }
            });
        } else {
            Log.e("-->", " < 14");
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        }
    }


    public void showAlertSingleButtonInfoSubmissionSuceesful(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.AlertDialogHeaderMsg))
                .setMessage(msg)
                .setCancelable(false)
                .setIcon(R.drawable.info_ico)
                .setPositiveButton(getResources().getString(R.string.AlertDialogOkButton), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                       /* Intent intent = new Intent(PicBefor2eMerchandising.this, StoreVisitDetails.class);
                        intent.putExtra(AppUtils.SPOKE_ID_INTENT, Spoke_ID);
                        intent.putExtra(AppUtils.FLG_STORE_ACTIVITY_INTENT, flgStoreActivity);
                        startActivity(intent);*/
                        //Direct To Next Page
                    }
                }).create().show();
    }

    public void fnSaveImagesInDatabase() {
        if (hmapPhotoDetailsForSaving != null && hmapPhotoDetailsForSaving.size() > 0) {
            for (Map.Entry<String, String> entry : hmapPhotoDetailsForSaving.entrySet()) {//clickedTagPhoto+"~"+photoPath+"~"+clickedDateTime+"~"+"1"
                String photoName = entry.getKey();
                String photoPath = entry.getValue().split(Pattern.quote("~"))[1];
                String clickedDateTime = entry.getValue().split(Pattern.quote("~"))[2];
                int ImageType = Integer.parseInt(entry.getValue().split(Pattern.quote("~"))[3]);

                mDataSource.insertImagesAgainstStore(storeID, clickedDateTime, photoName, photoPath, 1, ImageType, flgType,storeVisitCode);
                // System.out.println("SAVING..."+storeID+"-"+clickedDateTime+"-"+photoName+"-"+photoPath);
            }
        }
        mDataSource.fnUpdate_tblStoreVisitMaster(storeVisitCode, commentsET.getText().toString().trim(), flgIsPicsAllowed);
        Intent nxtP4 = new Intent(PicBeforeMerchandising.this, ActualVisitStock.class);
        nxtP4.putExtra("storeID", storeID);
        nxtP4.putExtra("SN", selStoreName);
        nxtP4.putExtra("imei", imei);
        nxtP4.putExtra("StoreVisitCode", storeVisitCode);
        nxtP4.putExtra("token", imei);
        nxtP4.putExtra("userdate", date);
        nxtP4.putExtra("pickerDate", pickerDate);
        nxtP4.putExtra("flgOrderType", 1);
        startActivity(nxtP4);
        finish();
        //showAlertSingleButtonInfoSubmissionSuceesful("Data Submitted successfully.");
   /*     Intent intent = new Intent(PicBeforeMerchandising.this, StoreVisitDetails.class);
        intent.putExtra(AppUtils.SPOKE_ID_INTENT, Spoke_ID);
        intent.putExtra(AppUtils.FLG_STORE_ACTIVITY_INTENT, flgStoreActivity);
        startActivity(intent);*/
    }

    public Boolean fnValidatImageRackPhotoRakenorNot() {
        Boolean flgImageRackPhotoTakeOrNot = true;
        cntFrontPhotoTaken=0;
        int i = 1;
        int flgcolImageValidation = 0;
        String colNameToValidate = "";
        if (flgIsPicsAllowed == 0 && TextUtils.isEmpty(commentsET.getText().toString().trim())) {
            flgcolImageValidation = 1;
            if (colNameToValidate.equals(""))
                colNameToValidate = (i++) + ". Please enter reason.";
            else
                colNameToValidate = colNameToValidate + "\n" + (i++) + ". Please enter reason.";

//            showAlertSingleButtonInfo("Please enter reason.");
            flgImageRackPhotoTakeOrNot = true; //for making it madatory make it true and then write the code for validation
        }

        if(flgImageRackPhotoTakeOrNot==true) {
            if (flgIsPicsAllowed == 1 && hmapCtgryPhotoSection != null && (hmapCtgryPhotoSection.size() > 0)) {
                flgImageRackPhotoTakeOrNot = true;
                cntFrontPhotoTaken = 1;
            }
            else if (flgIsPicsAllowed == 0)
            {
                flgImageRackPhotoTakeOrNot = true;
                cntFrontPhotoTaken = 0;
            }
            else {
                flgImageRackPhotoTakeOrNot = false;
                cntFrontPhotoTaken = 0;
            }
        }
        return flgImageRackPhotoTakeOrNot;
    }


  /*  @Override
    public void fnCallUpdateLocation() {
        inflateUpdateContactSection();
    }*/
}

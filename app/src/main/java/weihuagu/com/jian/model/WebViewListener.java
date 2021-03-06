/*
 * Created By WeihuaGu (email:weihuagu_work@163.com)
 * Copyright (c) 2017
 * All right reserved.
 */

package weihuagu.com.jian.model;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import weihuagu.com.jian.BrowserActivity;
import weihuagu.com.jian.R;
import weihuagu.com.jian.ui.view.ItemLongClickedPopWindow;

/**
 * Created by root on 17-3-23.
 */
public class WebViewListener implements View.OnTouchListener,View.OnLongClickListener {
    int downX;
    int downY;
    ItemLongClickedPopWindow itemLongClickedPopWindow;
    String saveImgUrl;
    String openUrl;
    private Context context=null;
    AttributeSet attrs=null;
    WebViewFactory webviewfactory=null;
    private static final String ACTION_OPENURLINBACK = "com.weihuagu.jian.action.OPENURLINBACK";
    private static final String ACTION_OPENURLINNEWTAB = "com.weihuagu.jian.action.OPENURLINNEWTAB";
    private static final String EXTRA_URL = "com.weihuagu.jian.extra.url";
    public WebViewListener(Context context) {
        this.context = context;
    }
    public WebViewListener(Context context,AttributeSet attrs) {
        this.context = context;this.attrs=attrs;
    }


    public void hindleIMAGE_TYPE(String ImgUrl,View v){
        // 获取图片的路径
        saveImgUrl =ImgUrl;
        //通过GestureDetector获取按下的位置，来定位PopWindow显示的位置

        itemLongClickedPopWindow = new ItemLongClickedPopWindow(context,
                ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW,
                300,200);

        itemLongClickedPopWindow.showAtLocation(v, Gravity.TOP|Gravity.LEFT, downX, downY + 10);

        itemLongClickedPopWindow.getView(R.id.item_longclicked_save)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(saveImgUrl!=null|saveImgUrl.equals("")){
                            saveImage();
                            itemLongClickedPopWindow.dismiss();
                        }
                    }
                });

    }

    public void hindleSRC_IMAGE_ANCHOR_TYPE(String ImgUrl,View v){
        saveImgUrl =ImgUrl;
        //通过GestureDetector获取按下的位置，来定位PopWindow显示的位置

        itemLongClickedPopWindow = new ItemLongClickedPopWindow(context,
                ItemLongClickedPopWindow.IMAGE_VIEW_POPUPWINDOW,
                300,200);

        itemLongClickedPopWindow.showAtLocation(v, Gravity.TOP|Gravity.LEFT, downX, downY + 10);

        itemLongClickedPopWindow.getView(R.id.item_longclicked_save)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(saveImgUrl!=null|saveImgUrl.equals("")){
                            saveImage();
                            itemLongClickedPopWindow.dismiss();
                        }
                    }
                });


    }

    public void hindleSRC_ANCHOR_TYPE(String url,View v){
        openUrl=url;
        itemLongClickedPopWindow = new ItemLongClickedPopWindow(context,
                ItemLongClickedPopWindow.SRC_ANCHOR_TYPE,
                500,400);

        itemLongClickedPopWindow.showAtLocation(v, Gravity.TOP|Gravity.LEFT, downX, downY + 10);

        itemLongClickedPopWindow.getView(R.id.item_longclicked_openlinkinback)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("popwindow","link open in back");
                        openUrlInBack(v,openUrl);
                        itemLongClickedPopWindow.dismiss();

                    }
                });

        itemLongClickedPopWindow.getView(R.id.item_longclicked_openlinkinnewtab)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.v("popwindow","link open in new tab");
                        openUrlInNewTab(v,openUrl);
                        itemLongClickedPopWindow.dismiss();

                    }
                });

    }

    public void openUrlInBack(View v,String url){
        Log.i("Qrcode","open url");
        webviewfactory=new WebViewFactory(context,attrs);
        webviewfactory.createWebView(url);

    }

    public void openUrlInNewTab(View v,String url){
        Log.i("Qrcode","open url");
        if(!url.equals("")){
            Log.i("Qrcode",url);
            Intent send=new Intent(context, BrowserActivity.class);
            send.setAction(ACTION_OPENURLINNEWTAB);
            send.putExtra(EXTRA_URL,url);
            context.startActivity(send);
        }

    }

    public void saveImage(){
        DownloadManager downloadManager;
        downloadManager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadItem request=new DownloadItem(saveImgUrl);
        downloadManager.enqueue(request);

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        downX = (int) motionEvent.getX();
        downY = (int) motionEvent.getY();
        return false;
    }




    @Override
    public boolean onLongClick(View v) {
        WebView.HitTestResult result = ((WebView)v).getHitTestResult();
        Log.v("longclick type",Integer.toString(result.getType()));

        if (null == result)
            return false;

        int type = result.getType();

        if (type == WebView.HitTestResult.UNKNOWN_TYPE)
            return false;
        if (type == WebView.HitTestResult.EDIT_TEXT_TYPE) {

        }
        // 相应长按事件弹出菜单

        // 这里可以拦截很多类型，我们只处理图片类型就可以了
        switch (type) {
            case WebView.HitTestResult.PHONE_TYPE: // 处理拨号
                break;
            case WebView.HitTestResult.EMAIL_TYPE: // 处理Email
                break;
            case WebView.HitTestResult.GEO_TYPE: // TODO
                break;
            case WebView.HitTestResult.SRC_ANCHOR_TYPE: // 超链接
                Log.v("longpree","link"+type+":"+result.getExtra());
                this.hindleSRC_ANCHOR_TYPE(result.getExtra(),v);
                break;
            case WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE:
                Log.v("longpress","srcimage"+type+":"+result.getExtra());
                this.hindleSRC_IMAGE_ANCHOR_TYPE(result.getExtra(),v);
                break;
            case WebView.HitTestResult.IMAGE_TYPE: // 处理长按图片的菜单项
                Log.v("longpress","image"+type);
                this.hindleIMAGE_TYPE(result.getExtra(),v);
                break;
            default:
                break;
        }

        return false;

    }
}

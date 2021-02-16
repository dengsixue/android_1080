package com.example.android_1080.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

public class DonwloadImgUtil {
    private static Context mContext;
    private static String filePath;
    private static String mSaveMessage = "失败";
    private static Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //mSaveDialog.dismiss();
            Toast.makeText(mContext, mSaveMessage, Toast.LENGTH_SHORT).show();
        }
    };
//    private Runnable saveImgRunnable = new Runnable() {
//        @Override
//        public void run() {
//
//        }
//    };
    public static void stratDownloadImg(Context context,String path) {
        mContext=context;
        filePath = path;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(filePath);
                    InputStream is = url.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    saveImg(bitmap);
                    mSaveMessage = "图片保存成功！";
                } catch (Exception e) {
                    e.printStackTrace();
                    mSaveMessage = "图片保存失败！";
                }
                messageHandler.sendMessage(messageHandler.obtainMessage());
            }
        }).start();
    }



    private static void saveImg(Bitmap bitmap) throws IOException {
        File dirFile = new File(Environment.getExternalStorageDirectory().getPath());
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File myCaptureFile = new File(Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/" + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        //把图片保存后声明这个广播事件通知系统相册有新图片到来
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(myCaptureFile);
        intent.setData(uri);
        mContext.sendBroadcast(intent);

    }

}

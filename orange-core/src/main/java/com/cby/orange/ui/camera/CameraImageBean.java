package com.cby.orange.ui.camera;

import android.net.Uri;

/**
 * 存储一些中间值
 * Created by baiyanfang on 2018/1/9.
 */

public final class CameraImageBean {

    private Uri mPath = null;

    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}

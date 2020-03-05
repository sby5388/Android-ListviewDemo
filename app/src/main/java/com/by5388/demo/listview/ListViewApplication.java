package com.by5388.demo.listview;

import android.app.Application;
import android.widget.Toast;

/**
 * @author Administrator  on 2020/3/5.
 */
public class ListViewApplication extends Application {
    private Toast mToast;

    private static ListViewApplication sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }



    private void toastShort(String s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void toastLong(String s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        mToast.show();
    }

    private void toastShort(int s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void toastLong(int s) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, s, Toast.LENGTH_LONG);
        mToast.show();
    }

    public static void toast(String s) {
        sApplication.toastShort(s);
    }

}

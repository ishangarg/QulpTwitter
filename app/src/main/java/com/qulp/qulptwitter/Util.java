package com.qulp.qulptwitter;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ishan-3306 on 7/6/2016.
 */
public class Util{

    public static ProgressDialog progress;

        public static void showLoading(Context mContext, String msg){
            progress=new ProgressDialog(mContext);
            progress.setMessage(msg);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
        }

        public static void hideLoading(){
            progress.hide();
        }

}

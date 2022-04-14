package com.example.login;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class ToastUtils {

    private static Toast mToast,mToastNormal;
    private static View view;

    /**
     * 普通的toast提示
     * */
    public static void showNOrmalToast(Context mContext,String message){

        ToastUtils.cancel();

        if(mToastNormal == null){
            mToastNormal = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        }

        mToastNormal.show();

    }
    /**
     *toast取消
     */
    public static void cancel(){

        if(mToast != null){
            mToast.cancel();
            mToast = null;
        }

    }

}

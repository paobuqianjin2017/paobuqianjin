package com.paobuqianjin.pbq.step.view.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.paobuqianjin.pbq.step.utils.LocalLog;

import butterknife.ButterKnife;


/**
 * Created by pbq on 2017/11/29.
 */

public class BaseActivity extends FragmentActivity {
    private final static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //resetDensity();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        initView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();

    }

    protected void initView() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        if (bundle != null) {
            intent.putExtra(getPackageName(), bundle);
        }
        startActivity(intent);
        if (finish)
            finish();
    }

    public void startActivity(Class<? extends Activity> target, Bundle bundle, boolean finish, String action) {
        Intent intent = new Intent();
        intent.setClass(this, target);
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtra(getPackageName(), bundle);
        }
        startActivity(intent);
        if (finish)
            finish();
    }

    public Bundle getBundle() {
        if (getIntent() != null && getIntent().hasExtra(getPackageName()))
            return getIntent().getBundleExtra(getPackageName());
        else
            return null;
    }


    /*隐藏软键盘
    *@function hideSoftInputView
    *@param
    *@return
    */
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /*隐藏软键盘-一般是EditText.getWindowToken()
    *@function hideSoftInput
    *@param
    *@return
    */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /*
    *@function pt适配
    *@param
    *@return
    */
    protected void resetDensity() {
        Log.d(TAG, "resetDensity() enter");
        Point size = new Point();
        ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        Log.d(TAG, "WindowSize x= " + size.x + "  y = " + size.y);
        LocalLog.d(TAG, "xdpi = " + getResources().getDisplayMetrics().xdpi);
        getResources().getDisplayMetrics().xdpi = size.x / 750 * 72;
        LocalLog.d(TAG, "xdpi = " + getResources().getDisplayMetrics().xdpi);
    }


}

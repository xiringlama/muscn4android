package com.awecode.muscn.views.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.awecode.muscn.MyApplication;
import com.awecode.muscn.R;
import com.awecode.muscn.util.Util;
import com.awecode.muscn.util.retrofit.MuscnApiInterface;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by surensth on 5/23/17.
 */

public abstract class AppCompatBaseActivity extends AppCompatActivity implements Validator.ValidationListener {
    protected MuscnApiInterface mApiInterface = null;

    protected Context mContext;
    protected Activity mActivity;
    ProgressDialog progressDialog;
    Validator mValidator;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
        mContext = this;
        mActivity = this;
        mApiInterface = ((MyApplication) getApplication()).getApiInterface();
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    protected int getDimen(int id) {
        return (int) mContext.getResources().getDimension(id);
    }

    public void toast(String message) {
        Util.toast(mContext, message);
    }

    protected abstract int getLayoutResourceId();

    public void openFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.replace(R.id.container,
                fragment);
        ft.addToBackStack(null);
        ft.commitAllowingStateLoss();
    }

    public void openFragmentNoHistory(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.replace(R.id.container,
                fragment, tag);
        ft.commitAllowingStateLoss();
    }

    public void openFragmentNoHistory(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager()
                .beginTransaction();
        ft.replace(R.id.container,
                fragment);
        ft.commitAllowingStateLoss();
    }


    /**
     * dialog to show message
     *
     * @param context context of current view
     * @param message message to show in dialog
     */
    public void showDialog(final Context context, String message) {
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void showDialog(String message) {
        new AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }


    public void successDialogAndCloseActivity(final Context context, String message) {
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void successDialogAndOpen(final Context context, String message, final Fragment mFragment) {
        new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        openFragmentNoHistory(mFragment);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    public void noInternetConnectionDialog(Context mContext) {
        showDialog(mContext, getString(R.string.no_internet_message));
    }

    public void showProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public void closeProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText)
                ((EditText) view).setError(message);
            else
                toast(message);

        }
    }

    public void validateForm() {
        if (mValidator == null) {
            mValidator = new Validator(this);
            mValidator.setValidationListener(this);
        }
        mValidator.validate();
    }

}

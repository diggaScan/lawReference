package com.sunland.lawreference;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

public class Frg_dialog extends DialogFragment {

    private Context context;
    private OnAlertDialogClickedListener onAlertDialogClickedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void setOnAlertDialogClickedListener(OnAlertDialogClickedListener onAlertDialogClickedListener) {
        this.onAlertDialogClickedListener = onAlertDialogClickedListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle("提示")
                .setMessage("是否删除全部浏览记录")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (onAlertDialogClickedListener != null) {
                            onAlertDialogClickedListener.onPositiveButtonClicked(dialog, which);
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (onAlertDialogClickedListener != null) {
                            onAlertDialogClickedListener.onNegativeButtonClicked(dialog, which);
                        }
                    }
                }).create();
        return dialog;
    }

    public interface OnAlertDialogClickedListener {
        void onPositiveButtonClicked(DialogInterface dialog, int which);

        void onNegativeButtonClicked(DialogInterface dialog, int which);
    }
}

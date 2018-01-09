package com.cby.orange.ui.date;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class DateDialogUtil {
    public interface IDateListener{
        void onDateChange(String date);
    }

    private IDateListener mIDateListener= null;

    public void setIDateListener(IDateListener listener){
        this.mIDateListener = listener;
    }

    public void showDialog(Context context){
        final LinearLayout ll = new LinearLayout(context);
        final DatePicker datePicker = new DatePicker(context);
        final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        datePicker.setLayoutParams(lp);
        datePicker.init(1990, 1, 1, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                final Calendar calendar = Calendar.getInstance();
                calendar.set(i,i1,i2);
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
                final String data = df.format(calendar.getTime());
                if (mIDateListener != null){
                    mIDateListener.onDateChange(data);
                }
            }
        });
        ll.addView(datePicker);

        new AlertDialog.Builder(context)
                .setTitle("选择日期")
                .setView(ll)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
        .show();
    }
}

package com.cby.orange.ec.main.personal.profile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.cby.orange.ui.date.DateDialogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final OrangeDelegate DELEGATE;
    private final String[] genders = new String[]{"男","女","保密"};

    public UserProfileClickListener(OrangeDelegate delegate) {
        this.DELEGATE = delegate;
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, final View view, int i) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(i);
        final int id = bean.getmId();
        switch (id) {
            case 1:
                //照相机或选择图片

                break;
            case 2:
                final OrangeDelegate nameDelegate = bean.getmDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(genders[i]);
                        dialogInterface.cancel();
                    }
                });
                break;
            case 4:
                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setIDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;

        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(DELEGATE.getContext());
        dialog.setSingleChoiceItems(genders,0,listener);
        dialog.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {

    }
}

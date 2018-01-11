package com.cby.orange.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.main.personal.list.ListBean;
import com.cby.orange.net.RestClient;
import com.cby.orange.net.callback.ISuccess;
import com.cby.orange.ui.camera.OrangeCamera;
import com.cby.orange.ui.date.DateDialogUtil;
import com.cby.orange.utils.callback.CallbackManager;
import com.cby.orange.utils.callback.CallbackType;
import com.cby.orange.utils.callback.IGlobalCallback;
import com.cby.orange.utils.log.OrangeLogger;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;

/**
 *
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
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        OrangeLogger.d("file",args);
                        ImageView imageView = view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(DELEGATE.getContext())
                                .load(args)
                                .into(imageView);
                        //上传头像
                        RestClient.builder()
                                .url("upload_image.php")
                                .loader(DELEGATE.getContext())
                                .file(args.getPath())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        OrangeLogger.d("uoload",response);
                                        final String path = JSON.parseObject(response).getJSONObject("result")
                                                .getString("path");
                                        //上传成功后通知服务器更新信息
                                        RestClient.builder()
                                                .url("user_profile.php")
                                                .params("avatar", path)
                                                .loader(DELEGATE.getContext())
                                                .success(new ISuccess() {
                                                    @Override
                                                    public void onSuccess(String response) {
                                                        //获取更新后的用户信息，然后更新本地数据库

                                                        //没有本地数据的APP，每次打开APP都请求API，获取信息

                                                    }
                                                })
                                                .build()
                                                .post();
                                    }
                                })
                                .build()
                                .upLoad();

                    }
                });
                DELEGATE.startCameraWithCheck();
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

package com.cby.orange.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.cby.orange.delegate.OrangeDelegate;
import com.cby.orange.ec.R;
import com.cby.orange.ec.main.sort.SortDelegate;
import com.cby.orange.ec.main.sort.content.ContentDelegate;
import com.cby.orange.ui.recycler.ItemType;
import com.cby.orange.ui.recycler.MultipleItemEntity;
import com.cby.orange.ui.recycler.MultipleRecyclerAdapter;
import com.cby.orange.ui.recycler.MultipleViewHolder;
import com.cby.orange.ui.recycler.MultipleteFields;
import com.cby.orange.utils.log.OrangeLogger;

import java.util.List;

/**
 * Created by baiyanfang on 2017/12/26.
 */

public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private  int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data,SortDelegate sortDelegate) {
        super(data);
        this.DELEGATE = sortDelegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST,R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleteFields.TEXT);

                final boolean isClicked = entity.getField(MultipleteFields.TAG);


                final AppCompatTextView name = holder.getView(R.id.tv_vertical_item_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition){

                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleteFields.TAG,false);
                            notifyItemChanged(mPrePosition);

                            //更新当前的item
                            entity.setField(MultipleteFields.TAG,true);
                            notifyItemChanged(currentPosition);

                            mPrePosition = currentPosition;

                            final int contentId = getData().get(currentPosition).getField(MultipleteFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.wechat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_backgrount));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);

    }

    private void switchContent(ContentDelegate delegate){
        final OrangeDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null){
            contentDelegate.replaceFragment(delegate,false);
        }
    }
}

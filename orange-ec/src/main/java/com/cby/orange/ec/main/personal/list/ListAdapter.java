package com.cby.orange.ec.main.personal.list;

import com.cby.orange.ec.R;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by baiyanfang on 2018/1/8.
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {

    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListType.ITEMNORMAL, R.layout.arrow_item_layout);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ListBean listBean) {
        switch (baseViewHolder.getItemViewType()) {
            case ListType.ITEMNORMAL:
                baseViewHolder.setText(R.id.tv_arrow_text, listBean.getmText());
                baseViewHolder.setText(R.id.tv_arrow_value, listBean.getmValue());

                break;
            default:
                break;
        }
    }
}

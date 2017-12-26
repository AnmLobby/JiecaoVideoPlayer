package com.example.jiecaovideoplayer;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by 鱼握拳 on 2017/12/21.
 */

public class TypeAdapter extends RecyclerArrayAdapter<CategoryTab>{
    public TypeAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypeViewHolder(parent);
    }
}

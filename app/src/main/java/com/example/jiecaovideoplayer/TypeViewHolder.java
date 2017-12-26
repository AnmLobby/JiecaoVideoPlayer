package com.example.jiecaovideoplayer;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by 鱼握拳 on 2017/12/21.
 */

public class TypeViewHolder extends BaseViewHolder<CategoryTab>{

    private ImageView imageView;
    private TextView textView;
    public TypeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item);
        imageView=$(R.id.icon);
        textView=$(R.id.type);
    }

    @Override
    public void setData(CategoryTab data) {
        super.setData(data);
        Glide.with(getContext()).load(data.getTypeIcon()).into(imageView);
        textView.setText(data.getType());
    }
}

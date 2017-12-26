package com.example.jiecaovideoplayer;

import cn.bmob.v3.BmobObject;

/**
 * Created by 鱼握拳 on 2017/12/21.
 */

public class CategoryTab  extends BmobObject{
    private  String id;
    private String type;
    private String typeIcon;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }


}

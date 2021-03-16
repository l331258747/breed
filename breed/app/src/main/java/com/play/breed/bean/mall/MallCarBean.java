package com.play.breed.bean.mall;

public class MallCarBean {

    boolean isTitle;

    MallCarChildBean item;

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public MallCarChildBean getItem() {
        return item;
    }

    public void setItem(MallCarChildBean item) {
        this.item = item;
    }
}
